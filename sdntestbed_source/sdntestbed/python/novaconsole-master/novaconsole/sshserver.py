# -*- coding: utf-8 -*-
"""
Created on Sat Aug 27 11:29:52 2016

@author: Heyang
"""

# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
from BaseHTTPServer import HTTPServer, BaseHTTPRequestHandler
from argparse import ArgumentParser
import threading
import json
import logging as log
import commands
import paramiko
import time

import main
from novaconsole.exc import *

LOG = log.getLogger('novaconsole')

class ERROR_CODE:
    PARSE_ERROR = -32700          # Invalid JSON was received by the server.
    INVALID_REQ = -32600          # The JSON sent is not a valid Request object.
    METHOD_NOT_FOUND = -32601     # The method does not exist / is not available.
    INVALID_PARAMS = -32602       # Invalid method parameter(s).
    INTERNAL_ERROR = -32603          # Internal JSON-RPC error.


class vnetLabRpcHandler(BaseHTTPRequestHandler):
    """Implementation of JSON-RPC API, defines all API handler methods."""

    def _buildResponse(self, json_id, result=None, error=None):
        """Returns JSON 2.0 compliant response."""
        res = {}
        res['jsonrpc'] = '2.0'
        # result and error are mutually exclusive
        if result is not None:
            res['result'] = result
        elif error is not None:
            res['error'] = error
        res['id'] = json_id
        return res

    def _buildError(self, code, message, data=None):
        """Returns JSON RPC 2.0 error object."""
        res = {}
        res['code'] = code
        res['message'] = message
        if data:
            res['data'] = data
        return res

    def do_POST(self):
        """Handle HTTP POST calls."""
        def reply(response):
            response = json.dumps(response) + '\n'
            self.send_response(200, "OK")
            self.send_header("Content-Type", "application/json")
            self.send_header("Content-Length", len(response))
            self.end_headers()
            self.wfile.write(response)

        # Put JSON message in data dict
        l = self.headers.get("Content-Length", "")
        data = ''
        if l == "":
            data = self.rfile.read()
        else:
            data = self.rfile.read(int(l))
        try:
            data = json.loads(data)
        except:
            msg = "Error parsing JSON request"
            log.error(msg)
            err = self._buildError(ERROR_CODE.PARSE_ERROR, msg)
            result = self._buildResponse(None, error=err)
        # Check if JSONRPC 2.0 compliant (correct version and json_id given)
        json_id = data.get('id', None)
        # Setup method to call
        try:
            methodName = "_exec_" + data.get('method')
            method = getattr(self, methodName)
            log.info(methodName)
        except:
            msg = "Method not found"
            log.info(msg)
            err = self._buildError(ERROR_CODE.METHOD_NOT_FOUND, msg)
            result = self._buildResponse(json_id, error=err)
        # Get method parameters
        params = data.get('params', {})
        # Call method
        result = method(json_id, params)
        reply(result)

    def _exec_cmd(self, json_id, params):
        """Handler for client requests."""
        log.info("Receive cmd request")
        cmd_str = params.get('cmd')
        #status_output: (status, output)
        status_output = commands.getstatusoutput(cmd_str)
        response = self._buildResponse(json_id, result={'status': status_output[0], 'output': status_output[1]})
        return response

    def _exec_send(self, json_id, params):   
        log.info("Send ssh request")
        log.info('str: %s', params.get('cmd'))
        arr = params.get('cmd').split('~')
        # arr[0] = url       //websocket serial console url
        # arr[1] = action    //ip addr
        # arr[2] = type      //instance image: ubuntu, cirros, floodlight, tty, hadoop, rhel, ovs
        # arr[3] = ins_id    //instance_id in mysql                
        # arr[4] = config    //1 or -1
        # arr[5] = config_ip
        # arr[6] = floating_ip  Not available
        #print arr
        if len(arr) == 6:   
            send = main.Send(json_id, arr[0], arr[1], arr[2], arr[3], arr[4], arr[5])  
            log.info('Ip:'+arr[1])
            log.info('Configip:'+arr[5])
            sshclient = SSHClient(json_id, False, send, len(main.thread_list))
            main.thread_list.append(sshclient)
            sshclient.start()           
            response = self._buildResponse(json_id, result={'status': 200, 'output': 'Message: ' + json_id + ' Sent Successfully'})
        else:
            response = self._buildResponse(json_id, result={'status': 404, 'output': 'No Action'})
        return response

    def _exec_recv(self, json_id, params):
        log.info("Build ssh response:")
        all_status = 'READY'
        for x in range(0, len(main.thread_list)):
            if main.thread_list[x].thread_id == json_id:
                    if main.thread_list[x].ip_isready is False:
                       # log.info("False [%d]",x)
                        all_status = 'NULL'
        if all_status == 'READY':
            main.thread_list = filter(lambda t: t.thread_id != json_id, main.thread_list)
        log.info('###### thread_list: %d', len(main.thread_list))
        response = self._buildResponse(json_id, result={'status': 200, 'output': all_status})
        return response
        
class vnetLabRpcServer(HTTPServer):
    def __init__(self, opts):
        HTTPServer.__init__(self, (opts['host'], opts['port']), vnetLabRpcHandler)

class RpcServer(threading.Thread):
    """JSON RPC 2.0 Server."""
    def __init__(self, opts):
        threading.Thread.__init__(self)
        self.httpd = vnetLabRpcServer(opts)
        #self.setDaemon(True)

    # Multi-threaded webserver
    def run(self):
        """Main function run by thread."""
        log.info("JSON RPC server starting")
        try:
            self.httpd.serve_forever()
        finally:
            self.httpd.server_close()


class SSHClient(threading.Thread):
    def __init__(self, thread_id, ip_isready, send, index):
        threading.Thread.__init__(self)
        self.index = index
        self.thread_id = thread_id
        self.ip_isready = ip_isready
        self.send = send
        self.start_loop = True
        self.username = 'root'             #Default account and password
        self.password = 'Passw0rd'
        self.ip_1_satus = False
        self.ip_2_satus = False


    def run(self):
        """ create ssh connection """
        if 'cirros' in self.send.type :
            self.username = 'cirros'
            self.password = 'cubswin:)'
        elif 'onos' in self.send.type :
            self.username = 'onos'
            self.password = 'onos'       
        elif self.send.type == 'rhel':
            pass            
       
        self.send.config=int(self.send.config)
        if self.send.config == 1:
            log.info('There is 3 cmd in CMDlist')
            CMDlist=['dhclient eth0','dhclient eth1','hostname -I']
        else:
            log.info('There is 1 cmd in CMDlist')
            CMDlist=['hostname -I']
       
        try:
            self.connect(self.thread_id,self.send.action,self.username,self.password,CMDlist,self.send.config,self.send.config_ip) ##correct parameters

        except NovaConsoleException as e:
            LOG.error(e)

    def connect(self, json_id, ip, username, passwd, cmd, config, config_ip):    #test SSH
        n = 0      
        while True:
            try:                
                log.info(ip +' is connecting') 
                ssh = paramiko.SSHClient()
                ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
                try:                   
                    ssh.connect(ip,22,username,passwd,timeout=5)		    
                except: 
		            ssh.connect(config_ip,22,username,passwd,timeout=5)                
                log.info(ip + ' is connected')
                if username =='cirros':
                    self.ip_isready = True
                    return 'ok'
                for m in cmd:
                    #log.info(ip+' is acting')
                    if m == 'dhclient eth1':                        
                        stdin, stdout, stderr = ssh.exec_command(m)
                        time.sleep(1)
                        ssh = paramiko.SSHClient()
                        ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
                    try:
                        ssh.connect(config_ip,22,username,passwd,timeout=5)
                    except:
			            ssh.connect(ip,22,username,passwd,timeout=5)                                                 
                    stdin, stdout, stderr = ssh.exec_command(m)
                    log.info(ip+' finished')
                    out = stdout.readlines()  
                    if config == 1:
                        for w in out:           
                            if ip in w:                         
                                self.ip_1_satus = True   
                            if config_ip in w:
                                self.ip_2_satus = True
                        if self.ip_1_satus == True and self.ip_2_satus == True:
                            """ DHCP service completed """
                            log.info('###%d, dhcp service completed_configip', self.index)
                            self.ip_isready = True
                    else:
                        for w in out:
                            if ip in w:
                                """ DHCP service completed """
                                log.info('###%d, dhcp service completed_ip', self.index)
                                self.ip_isready = True
                                print self.ip_isready
                print '%s\tOK\n'%(ip)
                ssh.close()
                return 'ok' 
            except :                
                if n > 10:
                    return 'error'
                print '%s\tWaiting for init\n'%(ip)
                #print 'floating_ip:%s'%(floating_ip)
                time.sleep(2)
                n += 1


if __name__ == '__main__':
    parser = ArgumentParser(description="vnetLab rpc client.")
    parser.add_argument('--host', default='localhost', help='vnetLab rpc client host (default="localhost")')
    parser.add_argument('--port', default=56789, type=int, help='vnetLab rpc client port (default=12345)')
    parser.add_argument('--loglevel', default='INFO', help='log level (default="INFO")')
    parser.add_argument('--version', action='version', version='%(prog)s 0.1')
    args = parser.parse_args()
    opts = vars(args)
    log.basicConfig(format='%(asctime)s %(message)s', level=getattr(log, opts['loglevel'].upper()))
    rpcserver = RpcServer(opts)
    rpcserver.run()
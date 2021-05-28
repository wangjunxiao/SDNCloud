'''
Created on Jan 22, 2016

@author: Bluesy Wang
'''

from BaseHTTPServer import HTTPServer, BaseHTTPRequestHandler
from argparse import ArgumentParser
import threading
import json
import logging as log
import commands

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
        response = self._buildResponse(json_id, result={ 'status': status_output[0], 'output': status_output[1] })
        return response


class vnetLabRpcServer(HTTPServer):
    def __init__(self, opts):
        HTTPServer.__init__(self, (opts['host'], opts['port']), vnetLabRpcHandler)


class RpcServer(threading.Thread):
    """JSON RPC 2.0 Server."""
    def __init__(self, opts):
        threading.Thread.__init__(self)
        self.httpd = vnetLabRpcServer(opts)
        self.setDaemon(True)
    
    # Multi-threaded webserver
    def run(self):
        """Main function run by thread."""
        log.info("JSON RPC server starting")
        try:
            self.httpd.serve_forever()
        finally:
            self.httpd.server_close()


if __name__ == '__main__':
    parser = ArgumentParser(description="vnetLab rpc client.")
    parser.add_argument('--host', default='localhost', help='vnetLab rpc client host (default="localhost")')
    parser.add_argument('--port', default=12345, type=int, help='vnetLab rpc client port (default=12345)')
    parser.add_argument('--loglevel', default='INFO', help='log level (default="INFO")')
    parser.add_argument('--version', action='version', version='%(prog)s 0.1')
    args = parser.parse_args()
    opts = vars(args)
    log.basicConfig(format='%(asctime)s %(message)s', level=getattr(log, opts['loglevel'].upper()))
    rpcserver = RpcServer(opts)
    rpcserver.run()
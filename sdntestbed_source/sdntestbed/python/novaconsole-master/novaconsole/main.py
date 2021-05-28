from __future__ import absolute_import


class Send:
    def __init__(self, id, url, action, type, ins_id, config,config_ip):       
        self.id = id
        self.url = url
        self.action = action
        self.type = type
        self.ins_id = ins_id
        self.config = config
        self.config_ip = config_ip


class Recv:
    def __init__(self, json_id, ins_id, content):
        self.json_id = json_id 
        self.ins_id = ins_id
        self.content = content

send_list = []
recv_list = []
thread_list = []

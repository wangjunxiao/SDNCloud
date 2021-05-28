from __future__ import absolute_import


class Send:
    def __init__(self, url, id, action, type, ins_id, config,floating_ip):
        self.url = url
        self.id = id
        self.action = action
        self.type = type
        self.ins_id = ins_id
        self.config = config
        self.floating_ip = floating_ip


class Recv:
    def __init__(self, json_id, ins_id, content):
        self.json_id = json_id 
        self.ins_id = ins_id
        self.content = content

send_list = []
recv_list = []
thread_list = []

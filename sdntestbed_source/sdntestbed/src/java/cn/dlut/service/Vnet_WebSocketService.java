package cn.dlut.service;

import cn.dlut.entity.Vnet_WSRequest;


public interface Vnet_WebSocketService {

	String create_Websocket(Vnet_WSRequest req, String method, String target, String type, String action,int config,String floating_ip);
	
}
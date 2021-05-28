package cn.dlut.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.entity.Vnet_WSRequest;
import cn.dlut.entity.Vnet_instance_host;
import cn.dlut.entity.Vnet_osport;
import cn.dlut.entity.Vnet_userip;
import cn.dlut.entity.Vnet_script;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.entity.Vnet_usersubnet_userip;
import cn.dlut.entity.Vnet_vnet_userscript;
import cn.dlut.entity.Vnet_vnet_usersubnet;
import cn.dlut.service.Vnet_UserResourceService;
import cn.dlut.service.Vnet_WebSocketService;

import cn.dlut.dao.Vnet_instance_hostDao;
import cn.dlut.dao.Vnet_instance_osportDao;
import cn.dlut.dao.Vnet_osportDao;
import cn.dlut.dao.Vnet_useripDao;
import cn.dlut.dao.Vnet_scriptDao;
import cn.dlut.dao.Vnet_subnetDao;
import cn.dlut.dao.Vnet_usersubnet_useripDao;
import cn.dlut.dao.Vnet_vnet_userscriptDao;
import cn.dlut.dao.Vnet_vnet_usersubnetDao;



@Service("Vnet_UserResourceService")
public class Vnet_UserResourceServiceImpl implements Vnet_UserResourceService{

	@Autowired
	private Vnet_subnetDao user_subnetdao;
	
	@Autowired
	private Vnet_useripDao user_ipDao;
	
	@Autowired
	private Vnet_usersubnet_useripDao subnet_ipdao;
	
	@Autowired
	private Vnet_scriptDao scriptDao;
	
	@Autowired
	private Vnet_vnet_userscriptDao vnet_scriptdao;
	
	@Autowired
	private Vnet_vnet_usersubnetDao vnet_subnetdao;
	
	@Autowired
	private Vnet_instance_hostDao instance_hostdao;
	
	@Autowired
	private Vnet_instance_osportDao instance_osportdao;
	
	@Autowired
	private Vnet_osportDao osportdao;
	
	@Autowired
	private Vnet_WebSocketService websocket;
	

	@Override
	public int usersubnet_FlexToJava(Vnet_subnet item) {
		user_subnetdao.insertUsersubnet(item);
		return 0;
	}

	@Override
	public int userscript_FlexToJava(Vnet_script item) {
		scriptDao.insertUserscript(item);
		return 0;
	}

	@Override
	public int userip_FlexToJava(Vnet_userip item) {
		//check if item have existed
		List<Vnet_userip> userip_list = user_ipDao.getAll();
		for (int i = 0; i < userip_list.size(); i++) {
			if(userip_list.get(i).getUserip_addr().equals(item.getUserip_addr())){
				return 1;
			}
		}
		user_ipDao.insertUserip(item);
		return 0;
//		//enable ip
//		//instance_host, instance_osport, osport, ip
//		Vnet_instance_host instance_host;
//		do {
//			instance_host = instance_hostdao.getByHost_id(item.getHost_id());
//		} while (instance_host == null);
//		
//		String osport_id;
//		do {
//			osport_id = instance_osportdao.getOsport_id(instance_host.getInstance_id());
//		} while (osport_id == null);
//		
//		Vnet_osport osport;
//		do {
//			osport = osportdao.getById(osport_id);
//		} while (osport == null);
//
//		//update userip database table, insert osport_id
//		item.setOsport_id(osport.getOsport_id());
//		user_ipDao.updateOsport_id(item);
//		
//		//setup WebSocket connection with instance, send ifconfig command, and attach into the tail of rc.local as start up job
//		Vnet_WSRequest req1 = new Vnet_WSRequest(1, "ifconfig", null, null, item.getUserip_addr(), item.getUserip_mask(), "");
//		Vnet_WSRequest req2 = new Vnet_WSRequest(2, "rc.local", null, null, item.getUserip_addr(), item.getUserip_mask(), "");
//		System.out.println("####instance_id#### " + instance_host.getInstance_id());
//		
//		websocket.create_Websocket(req1, "send", instance_host.getInstance_id());
////		websocket.create_Websocket(req2, "send", instance_host.getInstance_id());
	}

	@Override
	public int usersubnet_ipFlexToJava(Vnet_usersubnet_userip item) {
		subnet_ipdao.insertUsersubnet_userip(item);
		return 0;
	}

	@Override
	public int vnet_subnet_FlexToJava(Vnet_vnet_usersubnet item) {
		vnet_subnetdao.insertVnet_usersubnet(item);
		return 0;
	}

	@Override
	public int vnet_script_FlexToJava(Vnet_vnet_userscript item) {
		vnet_scriptdao.insertVnet_userscript(item);
		return 0;
	}

	@Override
	public int usersubnet_Update(Vnet_subnet item) {
		user_subnetdao.updateSubnet(item);
		return 0;
	}
	
	@Override
	public int usersubnet_Delete(String item) {
		user_subnetdao.delById(item);
		return 0;
	}
	
}
package cn.dlut.service;

import java.util.List;
import java.util.Map;

import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_executor;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_flavor_instance;
import cn.dlut.entity.Vnet_image;
import cn.dlut.entity.Vnet_image_instance;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.entity.Vnet_instance_controller;
import cn.dlut.entity.Vnet_instance_host;
import cn.dlut.entity.Vnet_ofport;
import cn.dlut.entity.Vnet_switch;
import cn.dlut.entity.Vnet_link;
import cn.dlut.entity.Vnet_host;
import cn.dlut.entity.Vnet_oflink;
import cn.dlut.entity.Vnet_switch_ofport;
import cn.dlut.entity.Vnet_userip;
import cn.dlut.entity.Vnet_script;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.entity.Vnet_usersubnet_userip;
import cn.dlut.entity.Vnet_vnet;

public interface Vnet_JavaToFlexService {
	
	public List<Vnet_instance> instanceJavaToFlex(Vnet_executor exe);	
	
	public List<Vnet_controller> controllerJavaToFlex(Vnet_executor exe);	
	
	public List<Vnet_switch> switchJavaToFlex(Vnet_executor exe);
	
	public List<Vnet_host> hostJavaToFlex(Vnet_executor exe);
		
	public List<Vnet_oflink> oflinkJavaToFlex(Vnet_executor exe);
	
	public List<Vnet_link> linkJavaToFlex(Vnet_executor exe);
	
	public Vnet_vnet e_vJavaToFlex(Vnet_executor exe);

	public List<Vnet_ofport> ofportJavaToFlex(Vnet_executor exe);
	
	public List<Vnet_switch_ofport> switch_ofportJavaToFlex(Vnet_executor exe);
	
	public List<Vnet_image> imageJavaToFlex();
	
	public List<Vnet_flavor> flavorJavaToFlex();
	
	public List<Vnet_image_instance> image_instanceJavaToFlex(Vnet_executor exe);
	
	public List<Vnet_flavor_instance> flavor_instanceJavaToFlex(Vnet_executor exe);
	
	public List<Vnet_instance_host> instance_hostJavaToFlex(Vnet_executor exe);
	
	public List<Vnet_instance_controller> instance_controller(Vnet_executor exe);
	
	public List<Vnet_subnet> usersubnet_JavaToFlex();
	
	public List<Vnet_userip> userip_JavaToFlex();
	
	public List<Vnet_usersubnet_userip> subnet_ipJavaToFlex();
	
	public List<Vnet_script> script_JavaToFlex();
	
	public List<Map<String, String>> instanceIP_JavaToFlex(int exe_id);
	
	public Boolean queryVnet(Vnet_executor exe) throws InterruptedException;
	
}
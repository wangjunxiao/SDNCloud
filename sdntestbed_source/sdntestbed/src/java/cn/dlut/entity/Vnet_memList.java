package cn.dlut.entity;

import java.util.ArrayList;
import java.util.List;

public class Vnet_memList {

	public   List<Vnet_ofport> ofportlist=new ArrayList<Vnet_ofport>();
	public   List<Vnet_controller> controllerlist=new ArrayList<Vnet_controller>();
	public   List<Vnet_switch> switchlist=new ArrayList<Vnet_switch>();
	public   List<Vnet_host> hostlist=new ArrayList<Vnet_host>();
	public   List<Vnet_oflink> oflinklist=new ArrayList<Vnet_oflink>();
	public   List<Vnet_link> linklist=new ArrayList<Vnet_link>();
	public   List<Vnet_switch_ofport> switch_ofportlist=new ArrayList<Vnet_switch_ofport>();
	public   Vnet_vnet myvnet = new Vnet_vnet();
	public   Vnet_executor_vnet executor_vnet = new Vnet_executor_vnet();
	public   List<Vnet_vnet_resource> vnet_resourcelist=new ArrayList<Vnet_vnet_resource>();
	
	public   List<Vnet_request> requestlist=new ArrayList<Vnet_request>();
	public   List<Vnet_instance> instancelist=new ArrayList<Vnet_instance>();
	
	public   List<Vnet_flavor_instance> flavor_instancelist=new ArrayList<Vnet_flavor_instance>();
	public   List<Vnet_image_instance> image_instancelist=new ArrayList<Vnet_image_instance>();
	public   List<Vnet_instance_controller> instance_controllerlist=new ArrayList<Vnet_instance_controller>();
	public   List<Vnet_osport> osportlist=new ArrayList<Vnet_osport>();
	public   List<Vnet_instance_osport> instance_osportlist=new ArrayList<Vnet_instance_osport>();
	public   List<Vnet_instance_host> instance_hostlist=new ArrayList<Vnet_instance_host>();
}

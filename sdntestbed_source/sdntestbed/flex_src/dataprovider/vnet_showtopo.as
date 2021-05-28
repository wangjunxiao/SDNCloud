// ActionScript file
//get database info after user login

import Components.Controller;
import Components.Host;
import Components.Item_image;
import Components.Link;
import Components.Switch;

import flashx.textLayout.formats.WhiteSpaceCollapse;

import mx.collections.ArrayCollection;
import mx.collections.ArrayList;
import mx.controls.Alert;
import mx.core.UIComponent;
import mx.messaging.AbstractConsumer;
import mx.rpc.events.ResultEvent;

import vo.Map;
import vo.Vnet_controller;
import vo.Vnet_executor_vnet;
import vo.Vnet_flavor_instance;
import vo.Vnet_host;
import vo.Vnet_image_instance;
import vo.Vnet_instance;
import vo.Vnet_instance_controller;
import vo.Vnet_instance_host;
import vo.Vnet_link;
import vo.Vnet_oflink;
import vo.Vnet_ofport;
import vo.Vnet_switch;
import vo.Vnet_usersubnet;
import vo.Vnet_vnet;


public var is_instance:Boolean = false;
public var is_instance_host:Boolean = false;
public var is_instance_con:Boolean = false;
public var instanceIP:ArrayCollection = new ArrayCollection();

private function vnet_add_controller(c_vnet:Vnet_controller,cN:int):Controller
{
	var c_item:Controller = new Controller();
	var temp:Array = c_vnet.controller_id.split("~");
	var vnet_title:String = temp[0] + temp[1];
	config(c_item,temp[4],temp[5],vnet_title);
	c_item.setCon_id(c_vnet.controller_id);
	c_item.setCon_name(c_vnet.controller_name);
	c_item.setCon_type(c_vnet.controller_type);
	for(var i:int=0; i<vnet_instance_controller.length; i++){
		if(c_vnet.controller_id == vnet_instance_controller[i].controller_id){
			for(var j:int=0; j<vnet_image_instance.length; j++){
				if(vnet_instance_controller[i].instance_id == vnet_image_instance[j].instance_id){
					c_item.setimage(vnet_image_instance[j].image_id);
					break;
				}
			}
			for(var k:int=0; k<vnet_flavor_instance.length; k++){
				if(vnet_instance_controller[i].instance_id == vnet_flavor_instance[k].instance_id){
					c_item.setflavor(vnet_flavor_instance[k].flavor_id);
					break;
				}
			}
//			for(var m:int=0; m<vnet_console_url.length; m++){
//				if(Object(vnet_console_url[m]).instance_id == vnet_instance_controller[i].instance_id){
//					c_item.console_url = Object(vnet_console_url[m]).console_url;
//					break;
//				}
//			}
			break;
		}
	}
	c_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
	c_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
	c_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
	network_group.addElement(c_item);
	C_obj.addItem(c_item);
	return c_item;
}


private function vnet_add_switch(s_vnet:Vnet_switch,sN:int):Switch
{	
	var s_item:Switch = new Switch();
	var temp:Array = s_vnet.switch_id.split("~");
	var vnet_title:String = temp[0] + temp[1];
	config(s_item,temp[4],temp[5],vnet_title);
	s_item.setSwitch_id(s_vnet.switch_id);
	s_item.setSwitch_name(s_vnet.switch_name);
	s_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
	s_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
	s_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
	network_group.addElement(s_item);
	S_obj.addItem(s_item);
	return s_item;
}


private function vnet_add_host(h_vnet:Vnet_host,hN:int):Host
{
	var h_item:Host = new Host();
	var temp:Array = h_vnet.host_id.split("~");
	var vnet_title:String = temp[0] + temp[1];
	config(h_item,temp[4],temp[5],vnet_title);
	h_item.setHost_ID(h_vnet.host_id);
	h_item.setHost_Name(h_vnet.host_name);
	for(var i:int=0; i<vnet_instance_host.length; i++){
		if(h_vnet.host_id == vnet_instance_host[i].host_id){
			for(var j:int=0; j<vnet_image_instance.length; j++){
				if(vnet_instance_host[i].instance_id == vnet_image_instance[j].instance_id){
					h_item.setimage(vnet_image_instance[j].image_id);
					break;
				}
			}
			for(var k:int=0; k<vnet_flavor_instance.length; k++){
				if(vnet_instance_host[i].instance_id == vnet_flavor_instance[k].instance_id){
					h_item.setflavor(vnet_flavor_instance[k].flavor_id);
					break;
				}
			}
			for(var l:int=0;l<vnet_hosts.length;l++){
				if(vnet_instance_host[i].host_id == vnet_hosts[l].host_id){
//					Alert.show("aa"+vnet_hosts[l].host_config);
//					Alert.show("len:" + vnet_hosts.length);
					h_item.setHost_Config(vnet_hosts[l].host_config);
					break;
				}
			}
//			for(var m:int=0; m<vnet_console_url.length; m++){
//				if(Object(vnet_console_url[m]).instance_id == vnet_instance_host[i].instance_id){
//					h_item.console_url = Object(vnet_console_url[m]).console_url;
//					break;
//				}
//			}
			break;
		}
	}
	h_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
	h_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
	h_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
	network_group.addElement(h_item);
	H_obj.addItem(h_item);
	return h_item;
}

private function vnet_add_link(F_obj:Item_image,T_obj:Item_image,id:String):void
{
	var uiObj:UIComponent = new UIComponent(); 
	var obj:ArrayCollection = new ArrayCollection();
	obj.addItem(F_obj);
	obj.addItem(T_obj);
	obj.addItem(id);
	obj.addItem("tmp");
	var link:Link = new Link(obj);
	var temp:Array = id.split("~");
	link.time = temp[2];
	
	var childrenLinks:ArrayCollection =F_obj.getChildrenLinks();
	childrenLinks.addItem(link);					
	var parentLink:ArrayCollection = T_obj.getParentLink();
	parentLink.addItem(link); 
	
	var adjlinks_from:ArrayCollection =F_obj.getadjLinks();
	adjlinks_from.addItem(link);
	var adjlinks_to:ArrayCollection =T_obj.getadjLinks();
	adjlinks_to.addItem(link);
	
	uiObj.addChild(link);
	network_group.addElement(uiObj);
}


private function vnet_show_topoHandler():void{
	
	var con_item:ArrayCollection = new ArrayCollection();
	var swi_item:ArrayCollection = new ArrayCollection();
	var hos_item:ArrayCollection = new ArrayCollection();
	
	//show controller
	for(var c_num:int = 0;c_num<vnet_controllers.length;c_num++){
		con_item.addItem(vnet_add_controller(vnet_controllers[c_num],c_num));
	}
	
	
	//show switch
	for(var s_num:int = 0;s_num<vnet_switches.length;s_num++){
		swi_item.addItem(vnet_add_switch(vnet_switches[s_num],s_num));
	}
	
	//show host
	for(var h_num:int = 0; h_num<vnet_hosts.length; h_num++){
		hos_item.addItem(vnet_add_host(vnet_hosts[h_num],h_num));
	
	}
	//show oflink
	var tmp:Array;
	var title:String;
	for(var ofl_num:int = 0; ofl_num<vnet_oflinks.length; ofl_num++){
		for(var ci_num:int = 0;ci_num<con_item.length; ci_num++){
			tmp = Vnet_oflink(vnet_oflinks[ofl_num]).controller_id.split("~");
			title = tmp[0] + tmp[1];
			if(title == Controller(con_item[ci_num]).title){
				for(var si_num:int = 0;si_num<swi_item.length;si_num++){
					tmp = Vnet_oflink(vnet_oflinks[ofl_num]).switch_id.split("~");
					title = tmp[0] + tmp[1];
					if(title == Switch(swi_item[si_num]).title){
						vnet_add_link(con_item[ci_num],swi_item[si_num],vnet_oflinks[ofl_num].oflink_id);
					}
				}
			}
		}
	}
	
	//show link
	var vnet_src:String;
	var vnet_dst:String;
	var src_num:int;
	var dst_num:int;
	for(var lin_num:int = 0;lin_num<vnet_links.length;lin_num++){
		var tmp1:Array = Vnet_link(vnet_links[lin_num]).link_src_id.split("~");
		vnet_src = tmp1[0] + tmp1[1];
		var tmp2:Array = Vnet_link(vnet_links[lin_num]).link_dst_id.split("~");
		vnet_dst = tmp2[0] + tmp2[1];
		if(tmp1[0] == "s"){          //S to ?
			for(var si:int = 0; si<swi_item.length; si++){
				if(Switch(swi_item[si]).title == vnet_src){
					src_num = si;
					break;
				}
			}
			if(tmp2[0] == "s"){     //S to S
				for(var si2:int = 0; si2<swi_item.length; si2++){
					if(Switch(swi_item[si2]).title == vnet_dst){
						dst_num = si2;
						break;
					}
				}
				vnet_add_link(swi_item[src_num],swi_item[dst_num],vnet_links[lin_num].link_id);
			}
			if(tmp2[0] == "h"){     //S to H
				for(var hi:int = 0;hi<hos_item.length;hi++){
					if(Host(hos_item[hi]).title == vnet_dst){
						dst_num = hi;
						break;
					}
				}
				vnet_add_link(swi_item[src_num],hos_item[dst_num],vnet_links[lin_num].link_id);
			}
		}  //end S to ?
		if(tmp1[0] == "h"){   //H to S
			for(var hi2:int = 0;hi2<hos_item.length;hi2++){
				if(Host(hos_item[hi2]).title == vnet_src){
					src_num = hi2;
					break;
				}
			}
			if(tmp2[0] == "s"){
				for(var si3:int = 0; si3<swi_item.length; si3++){
					if(Switch(swi_item[si3]).title == vnet_dst){
						dst_num = si3;
						break;
					}
				}
				vnet_add_link(hos_item[src_num],swi_item[dst_num],vnet_links[lin_num].link_id);
			}
		}
	}
	
	//assign ip
	for(var m:int=0; m<instanceIP.length; m++){
		for(var k:int=0; k<vnet_instance_controller.length; k++){
			if(Object(instanceIP[m]).instance_id == vnet_instance_controller[k].instance_id){
				for(var n:int=0; n<C_obj.length; n++){
					if(C_obj[n].getCon_id() == vnet_instance_controller[k].controller_id){
						Controller(C_obj[n]).controller_ip = Object(instanceIP[m]).ip_addr;
						break;
					}
				}
				break;
			}
		}
		for(var h:int=0; h<vnet_instance_host.length; h++){
			if(Object(instanceIP[m]).instance_id == vnet_instance_host[h].instance_id){
				for(var n2:int=0; n2<H_obj.length; n2++){
					if((H_obj[n2]).getHost_ID() == vnet_instance_host[h].host_id){
						Host(H_obj[n2]).host_IP = Object(instanceIP[m]).ip_addr;
						break;
					}
				}
				break;
			}
		}
	}
}

protected function instanceJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_instances = event.result as ArrayCollection;
	//set counter
	for(var ec_num:int = 0;ec_num<vnet_instances.length;ec_num++){
		var ec_tmp:Array = Vnet_instance(vnet_instances[ec_num]).instance_id.split("~");
		if(int(ec_tmp[1]) > vnet_counter_instance){
			vnet_counter_instance = int(ec_tmp[1]);
		}
	}
	is_instance = true;
	if(is_instance && is_instance_host){
		is_instance = false;
		for(var ins:int=0; ins<vnet_instances.length; ins++){
			for(var ins_h:int=0; ins_h<vnet_instance_host.length; ins_h++){
				if(vnet_instances[ins].instance_id == vnet_instance_host[ins_h].instance_id){
					var h:Vnet_host = new Vnet_host();
					h.host_id = vnet_instance_host[ins_h].host_id;
					vnet_hosts.addItem(h);
				}
			}
		}
	}
	if(is_instance && is_instance_con){
		is_instance = false;	
		for(var ins2:int=0; ins2<vnet_instances.length; ins2++){
			for(var ins_c:int=0; ins_c<vnet_instance_controller.length; ins_c++){
				if(vnet_instances[ins2].instance_id == vnet_instance_controller[ins_c].instance_id){
					var c:Vnet_controller = new Vnet_controller();
					c.controller_id = vnet_instance_controller[ins_c].controller_id;
					vnet_controllers.addItem(c);
				}
			}
		}
	}
	counter++;  
	if(counter==16){
		initComplete();
	}	
}
protected function switchJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_switches = event.result as ArrayCollection;
	//set counter
	for(var es_num:int = 0;es_num<vnet_switches.length;es_num++){
		var es_tmp:Array = Vnet_switch(vnet_switches[es_num]).switch_id.split("~");
		if(int(es_tmp[1]) > vnet_counter){
			vnet_counter = int(es_tmp[1]);
		}
	}
	counter++;
	if(counter==16){
		initComplete();
	}
}

protected function oflinkJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_oflinks = event.result as ArrayCollection;
	//set counter
	for(var eol_num:int = 0;eol_num<vnet_oflinks.length;eol_num++){
		var eol_tmp:Array = Vnet_oflink(vnet_oflinks[eol_num]).oflink_id.split("~");
		if(int(eol_tmp[1]) > link_num){
			link_num = int(eol_tmp[1]);
		}
	}
	counter++;  //1
	if(counter==16){
		initComplete();
	}
}

protected function linkJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_links = event.result as ArrayCollection;
	//set counter
	for(var el_num:int = 0;el_num<vnet_links.length;el_num++){
		var el_tmp:Array = Vnet_link(vnet_links[el_num]).link_id.split("~");
		if(int(el_tmp[1]) > link_num){
			link_num = int(el_tmp[1]);
		}
	}
	counter++;  //1
	if(counter==16){
		initComplete();
	}
}

protected function e_vJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_vnet = event.result as Vnet_vnet;
	counter++;
	if(vnet_vnet != null){
		vnet_counter_vnet = 1;
	}
	if(counter==16){
		initComplete();
	}
}

protected function ofportJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_ofports = event.result as ArrayCollection;
	vnet_counter_ofport = 0;
	//set counter
	for(var i:int = 0;i<vnet_ofports.length;i++){
		var tmp:Array = Vnet_ofport(vnet_ofports[i]).ofport_id.split("~");
		if(int(tmp[1]) > vnet_counter_ofport){
			vnet_counter_ofport = int(tmp[1]);
		}
	}
	counter++;
	if(counter==16){
		initComplete();
	}
}

protected function switch_ofportJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_switch_ofports = event.result as ArrayCollection;
	counter++;
	if(counter==16){
		initComplete();
	}
}

protected function imageJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_images = event.result as ArrayCollection;
	counter++;  //1
	if(counter==16){
		initComplete();
	}
}

protected function hostJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_hosts = event.result as ArrayCollection;
	counter++;  //1
	if(counter==16){
		initComplete();
	}
}

protected function controllerJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_controllers = event.result as ArrayCollection;
	counter++;  //1
	if(counter==16){
		initComplete();
	}
}


protected function flavorJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_flavors = event.result as ArrayCollection;
	counter++;  //1
	if(counter==16){
		initComplete();
	}
}

protected function image_instanceJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_image_instance = event.result as ArrayCollection;
	counter++;
	if(counter==16){
		initComplete();
	}
}

protected function flavor_instanceJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_flavor_instance = event.result as ArrayCollection;
	counter++;  //1
	if(counter==16){
		initComplete();
	}
}

protected function instance_hostJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_instance_host = event.result as ArrayCollection;
	//set counter
	for(var i:int = 0;i<vnet_instance_host.length;i++){
		var tmp:Array = Vnet_instance_host(vnet_instance_host[i]).host_id.split("~");
		if(int(tmp[1]) > vnet_counter){
			vnet_counter = int(tmp[1]);
		}
	}
	is_instance_host = true;
	if(is_instance && is_instance_host){
		is_instance_host = false;
		for(var ins:int=0; ins<vnet_instances.length; ins++){
			for(var ins_h:int=0; ins_h<vnet_instance_host.length; ins_h++){
				if(vnet_instances[ins].instance_id == vnet_instance_host[ins_h].instance_id){
					var h:Vnet_host = new Vnet_host();
					h.host_id = vnet_instance_host[ins_h].host_id;
					vnet_hosts.addItem(h);
				}
			}
		}
	}
	counter++;
	if(counter==16){
		initComplete();
	}
}

protected function instance_controllerJavaToFlex_resultHandler(event:ResultEvent):void{
	vnet_instance_controller = event.result as ArrayCollection;
	//set counter
	for(var i:int = 0;i<vnet_instance_controller.length;i++){
		var tmp:Array = Vnet_instance_controller(vnet_instance_controller[i]).controller_id.split("~");
		if(int(tmp[1]) > vnet_counter){
			vnet_counter = int(tmp[1]);
		}
	}
	is_instance_con = true;
	if(is_instance && is_instance_con){
		is_instance_con = false;	
		for(var ins2:int=0; ins2<vnet_instances.length; ins2++){
			for(var ins_c:int=0; ins_c<vnet_instance_controller.length; ins_c++){
				if(vnet_instances[ins2].instance_id == vnet_instance_controller[ins_c].instance_id){
					var c:Vnet_controller = new Vnet_controller();
					c.controller_id = vnet_instance_controller[ins_c].controller_id;
					vnet_controllers.addItem(c);
				}
			}
		}
	}
	counter++;
	if(counter==16){
		initComplete();
	}
}

private function userscript_JavaToFlexResultHandler(event:ResultEvent):void{
	vnet_scripts = event.result as ArrayCollection;
	Alert.show(vnet_scripts.length + "");
	//get user's resource
//	var len:int = list_ac.length;
//	for(var i:int = 0;i<len;i++){
//		var tmp:Array = Vnet_userscript(list_ac[i]).userscript_id.split("~");
//		if(FlexGlobals.topLevelApplication.executor_name != tmp[3]){
//			list_ac.removeItemAt(i);
//			i--;
//			len--;
//		}
//	}
	//				FlexGlobals.topLevelApplication.vnet_counter_sc = list_ac.length;
//	listDG.dataProvider(list_ac);
}

private function instanceIP_JavaToFlexResultHandler(event:ResultEvent):void{
	instanceIP = event.result as ArrayCollection;
	counter++;
	if(counter==16){
		initComplete();
	}
}

private function initComplete():void{
	counter--;
	if(vnet_counter > 0){
		vnet_counter++;
	}
	if(vnet_counter_instance > 0){
		vnet_counter_instance++;
	}
	if(vnet_counter_ofport > 0){
		vnet_counter_ofport++;
	}
	if(link_num > 0){
		link_num++;
	}
	
	//find out yhe maximum value of the length from vnet_switches, vnet_instance_controller, vnet_instance_host
	var n1:int = 0;
	if(vnet_instance_controller.length > vnet_switches.length){
		n1 = vnet_instance_controller.length;
	}
	else{
		n1 = vnet_switches.length;
	}
	if(n1 < vnet_instance_host.length){
		n1 = vnet_instance_host.length;
	}
	
	//find out yhe maximum value of the length from vnet_oflinks, vnet_links
	var n2:int = 0;
	if(vnet_oflinks.length > vnet_links.length){
		n2 = vnet_oflinks.length;
	}
	else{
		n2 = vnet_links.length;
	}
	
	//when counter = 0 and the length of the actual resource array is greater than 0, set counter
	if(vnet_counter < n1){
		vnet_counter = n1;
	}
	if(link_num < n2){
		link_num = n2;
	}
	if(vnet_counter_instance < vnet_instances.length){
		vnet_counter_instance = vnet_instances.length;
	}
	if(vnet_counter_ofport < vnet_ofports.length){
		vnet_counter_ofport = vnet_ofports.length;
	}
	
	topo_iscomplete=true;
	networkPanel_creationCompleteHandler();
	PopUpManager.removePopUp(img);
	Alert.show("Enjoy your Vnet journey!","Welcome " + executor_name);
}
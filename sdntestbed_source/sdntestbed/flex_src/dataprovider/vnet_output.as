// ActionScript file
//user's first submission of vnet

import Components.Controller;
import Components.Host;
import Components.Item_image;
import Components.Link;
import Components.Switch;

import flash.events.MouseEvent;
import flash.profiler.showRedrawRegions;
import flash.utils.Timer;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.core.FlexGlobals;
import mx.events.MenuEvent;

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
import vo.Vnet_request;
import vo.Vnet_switch;
import vo.Vnet_switch_ofport;
import vo.Vnet_vnet;
import vo.Vnet_vnet_resource;

private var req_ok:Boolean = false;
private var submit:Boolean = false;

//input: canvas's controller set C_obj:ArrayCollection
//output: backend's Controller entity set vnet_controllers:ArrayCollection
//output: backend's instance entity set vnet_instances:ArrayCollection
//output: backend's resource entity set vnet_resources:ArrayCollection
//output: backend's flavor entity set
//output: backend's image entity set
protected function  construct_controller(C:ArrayCollection):ArrayCollection
{
	
	var controllers:ArrayCollection = new ArrayCollection();
	
	for(var i:int = 0;i<C.length;i++){
		if(C[i].source){
			var con:Vnet_controller = new Vnet_controller();
			con.controller_id = Controller(C[i]).getCon_id();
			con.controller_type = C[i].getCon_type();
			controllers.addItem(con);
			
			var ins:Vnet_instance = new Vnet_instance();
			ins.instance_id = "instance~" + vnet_counter_instance +"~"+ Controller(C[i]).getTime() +"~"+ executor_name;
			ins.instance_status = "wait";
			ins.instance_type = "Controller";
			vnet_instances.addItem(ins);
			vnet_counter_instance++;
			
			var flavor_ins:Vnet_flavor_instance = new Vnet_flavor_instance();
			flavor_ins.instance_id = ins.instance_id;
			flavor_ins.flavor_id = Controller(C[i]).getflavor();
			if(flavor_ins.flavor_id == -1){
				for(var j:int=0; j<vnet_flavors.length; j++){
					if(vnet_flavors.getItemAt(j).flavor_name == "m1.small"){
						flavor_ins.flavor_id = vnet_flavors.getItemAt(j).flavor_id;
						Controller(C[i]).setflavor(vnet_flavors.getItemAt(j).flavor_id);
						break;
					}
				}
			}
			vnet_flavor_instance.addItem(flavor_ins);
			
			var image_ins:Vnet_image_instance = new Vnet_image_instance();
			image_ins.instance_id = ins.instance_id;
			image_ins.image_id = Controller(C[i]).getimage();
			if(image_ins.image_id == -1){
				for(var k:int=0; k<vnet_images.length; k++){
					if(vnet_images.getItemAt(k).image_name == "floodlight"){
						image_ins.image_id = vnet_images.getItemAt(k).image_id;
						Controller(C[i]).setimage(vnet_images.getItemAt(k).image_id);
						break;
					}
				}
			}
			vnet_image_instance.addItem(image_ins);
			
			var ins_con:Vnet_instance_controller = new Vnet_instance_controller();
			ins_con.controller_id = con.controller_id;
			ins_con.instance_id = ins.instance_id;
			vnet_instance_controller.addItem(ins_con);
			
			var v_res:Vnet_vnet_resource = new Vnet_vnet_resource();
			v_res.resource_id = con.controller_id;
			v_res.vnet_id = vnet_vnet.vnet_id;
			v_res.resource_type = "Controller";
			v_res.resource_status = "wait";
			var temp:Array = v_res.resource_id.split("~");
			v_res.resource_timestamp = temp[2];
			vnet_resources.addItem(v_res);
		}
	}
	
	return controllers;
	
}

//input: canvas's host set H_obj:ArrayCollection
//output: backend's host entity set vnet_controllers:ArrayCollection
//output: backend's instance entity set vnet_instances:ArrayCollection
//output: backend's resource entity set vnet_resources:ArrayCollection
//output: backend's instance_host entity set 
//output: backend's flavor entity set 
//output: backend's image entity set 
protected function construct_host(H:ArrayCollection):ArrayCollection
{
	
	var hosts:ArrayCollection = new ArrayCollection();
	
	for(var i:int = 0;i<H.length;i++){
		if(H[i].source){
			var hos:Vnet_host = new Vnet_host();
			hos.host_id = Host(H[i]).getHost_ID();
			hos.host_config = Host(H[i]).getHost_Config();
			hosts.addItem(hos);
			
			var ins:Vnet_instance = new Vnet_instance();
			ins.instance_id = "instance~" + vnet_counter_instance +"~"+ Host(H[i]).getTime() +"~"+ executor_name;
			ins.instance_status = "wait";
			ins.instance_type = "Host";
			vnet_instances.addItem(ins);
			vnet_counter_instance++;
			
			var flavor_ins:Vnet_flavor_instance = new Vnet_flavor_instance();
			flavor_ins.instance_id = ins.instance_id;
			flavor_ins.flavor_id = Host(H[i]).getflavor();
			if(flavor_ins.flavor_id == -1){
				for(var j:int=0; j<vnet_flavors.length; j++){
					if(vnet_flavors.getItemAt(j).flavor_name == "m1.nano"){
						flavor_ins.flavor_id = vnet_flavors.getItemAt(j).flavor_id;
						Host(H[i]).setflavor(vnet_flavors.getItemAt(j).flavor_id);
						break;
					}
				}
			}
			vnet_flavor_instance.addItem(flavor_ins);
			
			var image_ins:Vnet_image_instance = new Vnet_image_instance();
			image_ins.instance_id = ins.instance_id;
			image_ins.image_id = Host(H[i]).getimage();
			if(image_ins.image_id == -1){
				for(var k:int=0; k<vnet_images.length; k++){
					if(vnet_images.getItemAt(k).image_name == "cirros-0.3.4-x86_64"){
						image_ins.image_id = vnet_images.getItemAt(k).image_id;
						Host(H[i]).setimage(vnet_images.getItemAt(k).image_id);
						break;
					}
				}
			}
			vnet_image_instance.addItem(image_ins);
			
			var ins_host:Vnet_instance_host = new Vnet_instance_host();
			ins_host.host_id = hos.host_id;
			ins_host.instance_id = ins.instance_id;
			vnet_instance_host.addItem(ins_host);
			
			var v_res:Vnet_vnet_resource = new Vnet_vnet_resource();
			v_res.resource_id = hos.host_id;
			v_res.vnet_id = vnet_vnet.vnet_id;
			v_res.resource_type = "Host";
			v_res.resource_status = "wait";
			var temp:Array = v_res.resource_id.split("~");
			v_res.resource_timestamp = temp[2];
			vnet_resources.addItem(v_res);
		}
	}
	
	return hosts;
	
}

//input: canvas's switch set S_obj:ArrayCollection
//output: backend's switch entity set 
//output: backend's resource entity set
protected function construct_switch(S:ArrayCollection):ArrayCollection{
	
	var switches:ArrayCollection = new ArrayCollection();
	
	for(var i:int = 0;i<S.length;i++){
		if(S[i].source){
			var swi:Vnet_switch = new Vnet_switch();
			swi.switch_id = Switch(S[i]).getSwitch_id();
			switches.addItem(swi);
			
			var v_res:Vnet_vnet_resource = new Vnet_vnet_resource();
			v_res.resource_id = swi.switch_id;
			v_res.vnet_id = vnet_vnet.vnet_id;
			v_res.resource_type = "Switch";
			v_res.resource_status = "wait";
			var temp:Array = v_res.resource_id.split("~");
			v_res.resource_timestamp = temp[2];
			vnet_resources.addItem(v_res);
		}
	}
	
	return switches;
	
}

//input: canvas's switch set S_obj:ArrayCollection
//output: backend's ofport entity set
protected function construct_ofport(S:ArrayCollection):ArrayCollection{
	
	var ofports:ArrayCollection = new ArrayCollection();
	var n:int;
	
	for(var i:int = 0;i<S.length;i++){
		n=1;
		for(var j:int = 0;j<S[i].getadjLinks().length;j++){
			if((S[i].getadjLinks()[j].toObj.className != "Controller") &&
				(S[i].getadjLinks()[j].fromObj.className != "Controller")){
				var ofp:Vnet_ofport = new Vnet_ofport();
				ofp.ofport_id = "ofport~" + vnet_counter_ofport.toString() + "~"
					+ Link(S[i].getadjLinks()[j]).time+ "~" + executor_name;
				vnet_counter_ofport++;
				ofp.ofport_status = "wait";
				ofp.ofport_portnum = n;
				n++;	
				ofports.addItem(ofp);
			}
		}
	}
	
	return ofports;
	
}

//input: canvas's controller set C_obj:ArrayCollection
//output: backend's oflink entity set 
//output: backend's resource entity set
protected function construct_oflink(c_ac:ArrayCollection):ArrayCollection {
	
	var link_of:ArrayCollection = new ArrayCollection();
	
	for(var i:int = 0;i<c_ac.length;i++){
		for(var j:int = 0;j<c_ac[i].getadjLinks().length;j++){
			var lin1:Vnet_oflink = new Vnet_oflink();
			lin1.oflink_id = Link(c_ac[i].getadjLinks()[j]).link_id;
			lin1.controller_id = (Controller(c_ac[i])).getCon_id();
			lin1.oflink_status = "wait";
			if((c_ac[i].getadjLinks()[j].toObj.className == "Switch") &&
				(c_ac[i].getadjLinks()[j].fromObj.className == "Controller")){   //C to S
				lin1.switch_id = (Switch(c_ac[i].getadjLinks()[j].toObj)).getSwitch_id();
				
			}
			else if(c_ac[i].getadjLinks()[j].fromObj.className == "Switch"){   //S to C
				lin1.switch_id = (Switch(c_ac[i].getadjLinks()[j].fromObj)).getSwitch_id();
			}
			link_of.addItem(lin1);
		}
	}
	
	for(var r_num:int = 0;r_num<link_of.length;r_num++){
		var v_res:Vnet_vnet_resource = new Vnet_vnet_resource();
		v_res.resource_id = Vnet_oflink(link_of[r_num]).oflink_id;
		v_res.vnet_id = vnet_vnet.vnet_id;
		v_res.resource_type = "oflink";
		v_res.resource_status = "wait";
		var temp:Array = v_res.resource_id.split("~");
		v_res.resource_timestamp = temp[2];
		vnet_resources.addItem(v_res);
	}
	
	return link_of;
	
}

//input: canvas's switch set S_obj:ArrayCollection, switch_ofports entity set 
//output: backend's link entity set 
//output: backend's resource entity set
protected function construct_link(S:ArrayCollection,switch_ofports:ArrayCollection):ArrayCollection {
	
	var link_others:ArrayCollection = new ArrayCollection();
	
	for(var i:int = 0;i<S.length;i++){
		for(var j:int = 0;j<S[i].getadjLinks().length;j++){
			if((S[i].getadjLinks()[j].toObj.className != "Controller") &&   //delete the edge connnecting S[i] with controller
				(S[i].getadjLinks()[j].fromObj.className != "Controller")){
				var lin:Vnet_link = new Vnet_link();    //new the inserted link
				lin.link_id = Link(S[i].getadjLinks()[j]).link_id;
				var len:int = switch_ofports.length;
				if(Item_image(S[i]).gettitle() == Item_image(S[i].getadjLinks()[j].toObj).gettitle()){
					//the adjLink is S[i]'s ParentLink
					lin.link_dst_id = (Switch(S[i])).getSwitch_id();
					lin.link_dst_type = "Switch";
					lin.link_bandwidth = 0;
					lin.link_status = "wait";
					if(S[i].getadjLinks()[j].fromObj.className == "Host"){
						lin.link_src_id = (Host(S[i].getadjLinks()[j].fromObj)).getHost_ID();
						lin.link_src_type = "Host";
					}
					else if(S[i].getadjLinks()[j].fromObj.className == "Switch"){
						lin.link_src_id = (Switch(S[i].getadjLinks()[j].fromObj)).getSwitch_id();					
						lin.link_src_type = "Host";
						lin.link_src_type = "Switch";
					}
					
					var isok:Boolean = true;
					for(var m1:int = 0;m1<link_others.length;m1++){
						if((Vnet_link(link_others[m1]).link_src_id == lin.link_src_id) &&
							(Vnet_link(link_others[m1]).link_dst_id == lin.link_dst_id)){
							isok = false;
						}
					}
					
					if(isok){
						if(S[i].getadjLinks()[j].fromObj.className == "Host"){
							for(var k1:int = 0;k1<len;k1++){
								if(Vnet_switch_ofport(switch_ofports[k1]).switch_id == lin.link_dst_id){
									lin.link_ofport_dst = (Vnet_switch_ofport(switch_ofports[k1])).ofport_id;
									switch_ofports.removeItemAt(k1);
									len--;
									break;
								}
							}
							link_others.addItem(lin);
						}
						else if(S[i].getadjLinks()[j].fromObj.className == "Switch"){
							for(var k2:int = 0;k2<len;k2++){
								if(Vnet_switch_ofport(switch_ofports[k2]).switch_id == lin.link_dst_id){
									lin.link_ofport_dst = (Vnet_switch_ofport(switch_ofports[k2])).ofport_id;
									switch_ofports.removeItemAt(k2);
									len--;
									break;
								}
							}
							for(var k3:int = 0;k3<len;k3++){
								if(Vnet_switch_ofport(switch_ofports[k3]).switch_id == lin.link_src_id){
									lin.link_ofport_src = (Vnet_switch_ofport(switch_ofports[k3])).ofport_id;
									switch_ofports.removeItemAt(k3);
									len--;
									break;
								}
							}
							link_others.addItem(lin);
						}
					}
				}
					
				else if(Item_image(S[i]).gettitle() == Item_image(S[i].getadjLinks()[j].fromObj).gettitle()){
					//the adjLink is S[i]'s ChildLink
					lin.link_src_id = (Switch(S[i])).getSwitch_id();
					lin.link_src_type = "Switch";
					lin.link_bandwidth = 0;
					lin.link_status = "wait";
					if(S[i].getadjLinks()[j].toObj.className == "Host"){
						lin.link_dst_id = (Host(S[i].getadjLinks()[j].toObj)).getHost_ID();
						lin.link_dst_type = "Host";
					}
					else if(S[i].getadjLinks()[j].toObj.className == "Switch"){
						lin.link_dst_id = (Switch(S[i].getadjLinks()[j].toObj)).getSwitch_id();
						lin.link_dst_type = "Switch";
					}
					
					var isok2:Boolean = true;
					for(var m2:int = 0;m2<link_others.length;m2++){
						if((Vnet_link(link_others[m2]).link_src_id == lin.link_src_id) &&
							(Vnet_link(link_others[m2]).link_dst_id == lin.link_dst_id)){
							isok2 = false;
						}
					}
					
					if(isok2){
						if(S[i].getadjLinks()[j].toObj.className == "Host"){
							for(var k4:int = 0;k4<len;k4++){
								if(Vnet_switch_ofport(switch_ofports[k4]).switch_id == lin.link_src_id){
									lin.link_ofport_src = (Vnet_switch_ofport(switch_ofports[k4])).ofport_id;
									switch_ofports.removeItemAt(k4);
									len--;
									break;
								}
							}
							link_others.addItem(lin);
						}
						else if(S[i].getadjLinks()[j].toObj.className == "Switch"){
							for(var k5:int = 0;k5<len;k5++){
								if(Vnet_switch_ofport(switch_ofports[k5]).switch_id == lin.link_src_id){
									lin.link_ofport_src = (Vnet_switch_ofport(switch_ofports[k5])).ofport_id;
									switch_ofports.removeItemAt(k5);
									len--;
									break;
								}
							}
							for(var k6:int = 0;k6<len;k6++){
								if(Vnet_switch_ofport(switch_ofports[k6]).switch_id == lin.link_dst_id){
									lin.link_ofport_dst = (Vnet_switch_ofport(switch_ofports[k6])).ofport_id;
									switch_ofports.removeItemAt(k6);
									len--;
									break;
								}
							}
							link_others.addItem(lin);
						}
					}
				}
			}
		}
	}
	
	for(var r_num:int = 0;r_num<link_others.length;r_num++){
		var v_res2:Vnet_vnet_resource = new Vnet_vnet_resource();
		v_res2.resource_id = Vnet_link(link_others[r_num]).link_id;
		v_res2.vnet_id = vnet_vnet.vnet_id;
		v_res2.resource_type = "link";
		v_res2.resource_status = "wait";
		var temp2:Array = v_res2.resource_id.split("~");
		v_res2.resource_timestamp = temp2[2];
		vnet_resources.addItem(v_res2);
	}
	
	return link_others;
	
}

//input: canvas's switch set S_obj, ofport entity set 
//output: switch_ofport entity set 
protected function construct_switch_ofport(S:ArrayCollection,OFP_vnet:ArrayCollection):ArrayCollection{
	
	var switch_ofports:ArrayCollection = new ArrayCollection();
	var n:int = 0;
	
	for(var i:int = 0;i<S.length;i++){
		for(var j:int = 0;j<S[i].getadjLinks().length;j++){
			if((S[i].getadjLinks()[j].toObj.className != "Controller") &&
				(S[i].getadjLinks()[j].fromObj.className != "Controller")){
				var s_ofp:Vnet_switch_ofport = new Vnet_switch_ofport();
				s_ofp.switch_id = Switch(S[i]).getSwitch_id();
				s_ofp.ofport_id = Vnet_ofport(OFP_vnet[n]).ofport_id;
				n++;
				switch_ofports.addItem(s_ofp);
			}
		}
	}
	
	return switch_ofports;
	
}

//output: created vnet entity
protected function construct_vnet():Vnet_vnet{
	var myvnet:Vnet_vnet = new Vnet_vnet();
	var date:Date = new Date();
	myvnet.vnet_id = "vnet~" + vnet_counter_vnet.toString() + "~" + date.toString() + "~" + executor_name;
	myvnet.vnet_name = "vnet~" + vnet_counter_vnet.toString();
	vnet_counter_vnet++;
	myvnet.vnet_status = "wait";
	return myvnet;
}

//output：executor_vnet entity
protected function construct_executor_vnet(myvnet:Vnet_vnet):Vnet_executor_vnet{
	var e_v:Vnet_executor_vnet = new Vnet_executor_vnet();
	e_v.executor_id = executor_id;
	e_v.vnet_id = myvnet.vnet_id;
	return e_v;
}


//submission button is reponsed for the first time
protected function vnet_submit_topoHandler(event:MouseEvent,C:ArrayCollection,S:ArrayCollection,H:ArrayCollection):void
{
	//the parameters sent into backend
	vnet_vnet = construct_vnet();
	vnet_controllers = construct_controller(C);
	vnet_switches = construct_switch(S);
	vnet_hosts = construct_host(H);
	vnet_oflinks = construct_oflink(C);
	vnet_ofports = construct_ofport(S);
	var tmpswitch_ofports:ArrayCollection = construct_switch_ofport(S,vnet_ofports);
	for(var i:int = 0;i<tmpswitch_ofports.length;i++){
		vnet_switch_ofports.addItem(tmpswitch_ofports[i]);
	}
	vnet_links = construct_link(S,tmpswitch_ofports);
	
	var vnet_executor_vnet:Vnet_executor_vnet = construct_executor_vnet(vnet_vnet);
	var date:Date = new Date();
	var req:Vnet_request = new Vnet_request("Create Vnet","1",null,vnet_vnet.vnet_id,executor_name+"~"+date.toString());
	var vnet_requests:ArrayCollection = new ArrayCollection();
	vnet_requests.addItem(req);
	var vnet_test1:ArrayCollection = new ArrayCollection();
	
	
	//send into backend
	Vnet_FlexToJavaService.FlexToJava(vnet_ofports,vnet_controllers,vnet_switches,
		vnet_hosts,vnet_oflinks,vnet_links,vnet_switch_ofports,
		vnet_vnet,vnet_executor_vnet,vnet_resources,
		vnet_requests,vnet_instances,vnet_flavor_instance,vnet_image_instance,
		vnet_instance_controller,vnet_instance_host,
		vnet_test1,vnet_vnet.vnet_id);
	
	//Progress bar
//	vnet_proBar(C_obj.length+H_obj.length);
	var output_timer:Timer = new Timer(1000*10,4);
	output_timer.addEventListener(TimerEvent.TIMER,output_onTime);
	loading("images/loading.gif");
	output_timer.start();	
}

//deal with response timeout
private function output_onTime(event:TimerEvent):void{
//	if(timer.currentCount==2){
//		if(!req_ok){
//			Alert.show("20s");
//		}
//	}
//	if(timer.currentCount==3){
//		if(!req_ok){
//			Alert.show("30s");
//		}
//	}
//	if(timer.currentCount==4){
//		if(!req_ok){
//			Alert.show("40s");
//		}
//	}
}

//deal with returned value
private function FlexToJava_resultHandler(event:ResultEvent):void{
	var res:ArrayCollection = event.result as ArrayCollection;
	req_ok = true;
	//add from_portnum and to_portnum into link
	for(var i:int=0; i<S_obj.length; i++){
		for(var j:int=0; j<Switch(S_obj[i]).getadjLinks().length; j++){
			if(S_obj[i].getadjLinks()[j].toObj.className != "Controller" && S_obj[i].getadjLinks()[j].fromObj.className != "Controller"){
				Link(S_obj[i].getadjLinks()[j]).setSrcDstPort();
			}
		}
	}
	//add host ip, controller ip
	for(var m:int=0; m<res.length; m++){
		for(var k:int=0; k<vnet_instance_controller.length; k++){
			if(Object(res[m]).instance_id == vnet_instance_controller[k].instance_id){
				for(var n:int=0; n<C_obj.length; n++){
					if(C_obj[n].getCon_id() == vnet_instance_controller[k].controller_id){
						Controller(C_obj[n]).controller_ip = Object(res[m]).ip_addr;
						break;
					}
				}
				break;
			}
		}
		for(var h:int=0; h<vnet_instance_host.length; h++){
			if(Object(res[m]).instance_id == vnet_instance_host[h].instance_id){
				for(var n2:int=0; n2<H_obj.length; n2++){
					if((H_obj[n2]).getHost_ID() == vnet_instance_host[h].host_id){
						Host(H_obj[n2]).host_IP = Object(res[m]).ip_addr;
						if(Host(H_obj[n2]).host_config == 1){
							Host(H_obj[n2]).floating_ip = Object(res[m]).floating_IP;
						}
						break;
					}
				}
				break;
			}
		}
	}
	PopUpManager.removePopUp(img);
	Alert.show("submission success");			
}

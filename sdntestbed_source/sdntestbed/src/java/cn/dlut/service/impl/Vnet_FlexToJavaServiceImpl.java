package cn.dlut.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.fluent.Request;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.accessibility.internal.resources.accessibility;

import cn.dlut.dao.Vnet_controllerDao;

import cn.dlut.entity.Vnet_controller;

import cn.dlut.dao.Vnet_hostDao;

import cn.dlut.entity.Vnet_host;

import cn.dlut.entity.Vnet_executor;
import cn.dlut.entity.Vnet_compute;
import cn.dlut.entity.Vnet_WSRequest;
import cn.dlut.entity.Vnet_compute_controller;
import cn.dlut.entity.Vnet_compute_host;
import cn.dlut.entity.Vnet_compute_switch;
import cn.dlut.entity.Vnet_executor_vnet;
import cn.dlut.entity.Vnet_flavor_instance;
import cn.dlut.entity.Vnet_image;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_image_instance;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.entity.Vnet_instance_controller;
import cn.dlut.entity.Vnet_instance_host;
import cn.dlut.entity.Vnet_instance_osport;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_link;
import cn.dlut.entity.Vnet_oflink;
import cn.dlut.entity.Vnet_ofport;
import cn.dlut.entity.Vnet_osport;
import cn.dlut.entity.Vnet_request;
import cn.dlut.entity.Vnet_stack;
import cn.dlut.entity.Vnet_stack_instance;
import cn.dlut.entity.Vnet_switch_ofport;
import cn.dlut.entity.Vnet_tun;
import cn.dlut.entity.Vnet_tunofport;
import cn.dlut.entity.Vnet_vnet;
import cn.dlut.entity.Vnet_vnet_resource;
import cn.dlut.service.Vnet_FlexToJavaService;
import cn.dlut.service.Vnet_OVSService;
import cn.dlut.service.Vnet_WebSocketService;
import edu.emory.mathcs.backport.java.util.Collections;
import flex.messaging.io.ArrayCollection;

import cn.dlut.entity.Vnet_switch;

import cn.dlut.dao.Vnet_computeDao;
import cn.dlut.dao.Vnet_compute_controllerDao;
import cn.dlut.dao.Vnet_compute_hostDao;
import cn.dlut.dao.Vnet_compute_switchDao;
import cn.dlut.dao.Vnet_compute_tunofportDao;
import cn.dlut.dao.Vnet_executor_vnetDao;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_flavor_instanceDao;
import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_imageDao;
import cn.dlut.dao.Vnet_image_instanceDao;
import cn.dlut.dao.Vnet_instance_controllerDao;
import cn.dlut.dao.Vnet_instance_hostDao;
import cn.dlut.dao.Vnet_instance_osportDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_linkDao;
import cn.dlut.dao.Vnet_oflinkDao;
import cn.dlut.dao.Vnet_osportDao;
import cn.dlut.dao.Vnet_requestDao;
import cn.dlut.dao.Vnet_stackDao;
import cn.dlut.dao.Vnet_stack_instanceDao;
import cn.dlut.dao.Vnet_switchDao;
import cn.dlut.dao.Vnet_ofportDao;
import cn.dlut.dao.Vnet_switch_ofportDao;
import cn.dlut.dao.Vnet_tunDao;
import cn.dlut.dao.Vnet_tunofportDao;
import cn.dlut.dao.Vnet_vnetDao;
import cn.dlut.dao.Vnet_vnet_resourceDao;
import cn.dlut.service.impl.Vnet_OVSServiceImpl;

import cn.dlut.dao.Vnet_instanceDao;
import cn.dlut.dao.Vnet_ofportDao;

import cn.dlut.util.Vnet_ofportComparator;

import cn.dlut.entity.Vnet_memList;
import cn.dlut.service.Vnet_OpsHeatService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_requestComparator;


@Service("Vnet_FlexToJavaService")
public class Vnet_FlexToJavaServiceImpl extends AbstractBaseService  implements Vnet_FlexToJavaService{

	// The signal of access to floatingip database, 
	//	1: have access, 0: have no access
	private static int floatingip_flag=1;
	
	@Autowired
	private Vnet_controllerDao controllerdao;
	
	@Autowired
	private Vnet_compute_controllerDao compute_controllerdao;
	
	@Autowired
	private Vnet_compute_tunofportDao compute_tunofportdao;
	
	@Autowired
	private Vnet_compute_hostDao compute_hostdao;
	
	@Autowired
	private Vnet_compute_switchDao compute_switchdao;
	
	@Autowired
	private Vnet_computeDao computedao;
	
	@Autowired
	private Vnet_OpsHeatService opsheatservice;
	
	@Autowired
	private Vnet_switchDao switchdao;
	
	@Autowired
	private Vnet_tunDao tundao;

	@Autowired
	private Vnet_ofportDao vnet_ofportdao;
	
	@Autowired
	private Vnet_stack_instanceDao stack_instancedao;
	
	@Autowired
	private Vnet_hostDao hostdao;
	
	@Autowired
	private Vnet_oflinkDao oflinkdao;
	
	@Autowired
	private Vnet_linkDao linkdao;
	
	@Autowired
	private Vnet_stackDao stackdao;
	
	@Autowired
	private Vnet_switch_ofportDao switch_ofportdao;
	
	@Autowired
	private Vnet_tunofportDao tunofportdao;
	
	
	@Autowired
	private Vnet_vnetDao vnetdao;
	
	@Autowired
	private Vnet_ipDao ipdao;
	
	@Autowired
	private Vnet_executor_vnetDao executor_vnetdao;
	
	@Autowired
	private Vnet_imageDao imagedao;
	
	@Autowired
	private Vnet_vnet_resourceDao vnet_resourcedao;
	
	@Autowired
	private Vnet_requestDao requestdao;
	
	@Autowired
	private Vnet_instanceDao instancedao;
	
	@Autowired
	private Vnet_flavorDao flavordao;
	
	@Autowired
	private Vnet_flavor_instanceDao flavor_instancedao;
	
	@Autowired
	private Vnet_image_instanceDao image_instancedao;
	
	@Autowired
	private Vnet_instance_controllerDao instance_controllerdao;
	
	@Autowired
	private Vnet_osportDao osportdao;
	
	@Autowired
	private Vnet_instance_osportDao instance_osportdao;
	
	@Autowired
	private Vnet_instance_hostDao instance_hostdao;
	
	@Autowired
	private Vnet_floatingipDao floatingipdao;
	
	@Autowired
	private Vnet_OVSService ovsserviceimpl;
	
	@Autowired
	private Vnet_ofportDao ofportdao;
	
	@Autowired
	private Vnet_WebSocketService websocket;
	
	public Map<String,Vnet_memList> map_memlist=new HashMap<String,Vnet_memList>();
	
	//public static List<Vnet_map_memlist.get(tag)> list=new ArrayList<Vnet_map_memlist.get(tag)>();
	//public static int index=0;
	
	@Override
	public void createOflink(String controller_id,String switch_id)
	{
		
		String temp_port=AppProperties.Vnet_floodlight_openflow_port();
		
		// find out controller's floating ip
//		int temp_floatingip_id=controllerdao.getById(temp_oflink.getController_id()).getFloatingip_id();
//		String temp_loatingip=floatingipdao.getById(temp_floatingip_id).getFloatingip_addr();
		String temp_floatingip_addr=floatingipdao.getbyControllerid(controller_id).getFloatingip_addr();
		
		// find out dst switch's compute node url
//		Vnet_switch temp_switch=switchdao.getById(temp_oflink.getSwitch_id());
//		String temp_switch_name=temp_switch.getSwitch_name();
		String temp_switch_name=switchdao.getById(switch_id).getSwitch_name();
//		String temp_switch_id=temp_switch.getSwitch_id();
//		Integer temp_compute_id=compute_switchdao.getBySwitchId(temp_switch_id).getCompute_id();
//		String temp_compute_url=computedao.getById(temp_compute_id).getCompute_addr();
		Vnet_compute temp_compute=computedao.getBySwitchId(switch_id);
		
		ovsserviceimpl.set_controller(temp_compute.getCompute_addr(), temp_switch_name, temp_floatingip_addr, temp_port);
		
	}
	
	@Override 
	public void deleteOflink(String content_id)
	{
		//String temp_port=AppProperties.Vnet_floodlight_openflow_port();
		
		//find out floting_ip
		Vnet_oflink temp_oflink=oflinkdao.getById(content_id);
//		int temp_floatingip_id=controllerdao.getById(temp_oflink.getController_id()).getFloatingip_id();
//		String temp_floatingip=floatingipdao.getById(temp_floatingip_id).getFloatingip_addr();
		String temp_floatingip_addr=floatingipdao.getbyControllerid(temp_oflink.getController_id()).getFloatingip_addr() ;
		
		//find out dst switch's compute node url
		Vnet_switch temp_switch=switchdao.getById(temp_oflink.getSwitch_id());
		String temp_switch_name=temp_switch.getSwitch_name();
		String temp_switch_id=temp_switch.getSwitch_id();
		Integer temp_compute_id=compute_switchdao.getBySwitchId(temp_switch_id).getCompute_id();
		String temp_compute_url=computedao.getById(temp_compute_id).getCompute_addr();
		
		ovsserviceimpl.del_controller(temp_compute_url, temp_switch_name, temp_floatingip_addr);
		
		//update oflink database table
		temp_oflink.setOflink_status("DELETED");
		oflinkdao.updateOflink(temp_oflink);
		
		vnet_resourcedao.setDeleted(temp_oflink.getOflink_id());
		
	}
	
	@Override 
	public int deleteSwitch(String content_id)
	{
		Vnet_switch temp_switch= switchdao.getById( content_id);
		int compute_zone_id=compute_switchdao.getBySwitchId(content_id).getCompute_id();
		String compute_url=computedao.getById(compute_zone_id).getCompute_addr();
		ovsserviceimpl.del_br(compute_url, temp_switch.getSwitch_name());
		// update switch database table
		temp_switch.setSwitch_status("DELETED");
		switchdao.updateSwitch(temp_switch);
		vnet_resourcedao.setDeleted(temp_switch.getSwitch_id());
		
		return 0;
	}
	
	@Override
	public int deleteLink(String content_id)
	{

		
		Vnet_link v_link=linkdao.getById(content_id);
		System.out.println("found out the link to be deleted");
		String id_from="";
		String id_to="";
		String name_from="";
		String name_to="";
		String type_from="";
		String type_to="";
		int tunofport_from=0;
		int tunofport_to=0;
		
		if(v_link.getLink_src_type().equals("Host"))
		{
			id_from=v_link.getLink_dst_id();
			id_to=v_link.getLink_src_id();
			type_from=v_link.getLink_dst_type();
			type_to=v_link.getLink_src_type();
			
			if(v_link.getLink_iscross()==1)
			{
				//if is across two compute node, set tunofport
				tunofport_from=v_link.getLink_tunofport_dst();
				tunofport_to=v_link.getLink_tunofport_src();
			}

		}
		else
		{
			id_from=v_link.getLink_src_id();
			id_to=v_link.getLink_dst_id();
			type_to=v_link.getLink_dst_type();
			type_from=v_link.getLink_src_type();
			
			if(v_link.getLink_iscross()==1)
			{
				//if is across two compute node, set tunofport
				tunofport_from=v_link.getLink_tunofport_src();
				tunofport_to=v_link.getLink_tunofport_dst();
				
			}

		}
		
		if(type_to.equals("Host"))
		{
			// if is switch connecting a host
			name_from= switchdao.getById(id_from).getSwitch_name();
			
			// find out osport's osid by host_id
//			String thisInstanceId=instance_hostdao.getInstance_id(id_to);
//			String thisOsportId=instance_osportdao.getOsport_id(thisInstanceId);
//			Vnet_osport thisOsport=osportdao.getById(thisOsportId);
//			String thisOsportOsid=thisOsport.getOsport_osid();
			
			String thisOsportOsid=osportdao.getOsportOsid_by_host_id(id_to);
			Vnet_osport thisOsport=osportdao.getbyOsid(thisOsportOsid);
			
			//Then, it is divided into two cases
			//1: Both ends are at the same compute node; 2:at the different compute nodes
			
			int from_compute_id=compute_switchdao.getBySwitchId(id_from).getCompute_id();
			int to_compute_id=compute_hostdao.getByHostId(id_to).getCompute_id();
			
			if(from_compute_id==to_compute_id)
			{
				
				String compute_url=computedao.getById(from_compute_id).getCompute_addr();
				ovsserviceimpl.del_vmlink(compute_url, name_from, thisOsportOsid);
				
			}
			else
			{
				
				//(1) delete the switch connected with vnet-br and corresponding port
				String from_compute_url=computedao.getById(from_compute_id).getCompute_addr();
				int temp_tun_id=v_link.getTun_id();
				String vnet_br=AppProperties.getVnet_br();
				ovsserviceimpl.del_switchlink(from_compute_url, from_compute_url, name_from , vnet_br,
						name_from+vnet_br+temp_tun_id, vnet_br+name_from+temp_tun_id);
				// delete switch's flow entries
				String vnet_br_outport="1";
				String temp_tun_tag=tundao.getById(temp_tun_id).getTun_tag();
				ovsserviceimpl.del_flow(from_compute_url, temp_tun_tag,Integer.toString(tunofport_from), vnet_br_outport);
				
				
				//(2) delete vnet-br's port that related to the host at to_compute_url
				String to_compute_url=computedao.getById(to_compute_id).getCompute_addr();
				ovsserviceimpl.del_vmlink(to_compute_url, vnet_br, thisOsportOsid);
				// delete the corresponding flow entries
				ovsserviceimpl.del_flow(to_compute_url, temp_tun_tag, Integer.toString(tunofport_to), vnet_br_outport);
				
				
			}
			//update osport database table
			thisOsport.setOsport_status("DOWN");
			osportdao.updateOsport(thisOsport);
			
		}
		else 
		{
			
			//if is switch connecting a switch
			
			name_from=switchdao.getById(id_from).getSwitch_name();
			name_to=switchdao.getById(id_to).getSwitch_name();
			
			//Then, it is divided into two cases
			//1: Both ends are at the same compute node; 2:at the different compute nodes
			
			int from_compute_id=compute_switchdao.getBySwitchId(id_from).getCompute_id();
			int to_compute_id=compute_switchdao.getBySwitchId(id_to).getCompute_id();
			
			if(from_compute_id==to_compute_id)
			{
				
				String compute_url=computedao.getById(from_compute_id).getCompute_addr();
				ovsserviceimpl.del_switchlink(compute_url, compute_url, 
						name_from, name_to, name_from+name_to+"0", name_to+name_from+"0");
				
			}
			else
			{
				//(1) first, delete the vnet-br's link that related to the switch at from_compute
				String from_compute_url=computedao.getById(from_compute_id).getCompute_addr();
				int temp_tun_id=v_link.getTun_id();
				String vnet_br=AppProperties.getVnet_br();
				ovsserviceimpl.del_switchlink(from_compute_url, from_compute_url, 
						name_from, vnet_br, name_from+vnet_br+temp_tun_id, vnet_br+name_from+temp_tun_id);
				// delete vnet-br's relative flow entries at from_compute
				String vnet_br_outport="1";
				String temp_tun_tag=tundao.getById(temp_tun_id).getTun_tag();
				ovsserviceimpl.del_flow(from_compute_url, temp_tun_tag, Integer.toString(tunofport_from), vnet_br_outport);
				
				//(2) delete to_compute's vnet-br
				String to_compute_url=computedao.getById(to_compute_id).getCompute_addr();
				ovsserviceimpl.del_switchlink(to_compute_url,to_compute_url,
						name_to,vnet_br, name_to+vnet_br+temp_tun_id, vnet_br+name_to+temp_tun_id);
				// delete vnet-br's relative flow entries at to_compute
				ovsserviceimpl.del_flow(to_compute_url, temp_tun_tag, Integer.toString(tunofport_to), vnet_br_outport);
				
			}
			
		}
		
		//set link's status as DELETED, and update in database table 
		v_link.setLink_status("DELETED");
		linkdao.updateLink(v_link);
		
		vnet_resourcedao.setDeleted(v_link.getLink_id());
		
		return 0;
	}
	
	@Override
	public List<Map<String, String>> FlexToJava(ArrayCollection ofports,ArrayCollection controllers,ArrayCollection switches,
						  ArrayCollection hosts,ArrayCollection oflinks,ArrayCollection links,
						  ArrayCollection switch_ofports,Vnet_vnet vnet,Vnet_executor_vnet e_v,
						  ArrayCollection vnet_resources,ArrayCollection requests,
						  ArrayCollection instances,ArrayCollection flavor_instance,
						  ArrayCollection image_instance,ArrayCollection instance_controller,
						  ArrayCollection instance_host,ArrayCollection instance_osport,
						  String vnet_id)
	{
		
		Vnet_memList memlist=new Vnet_memList();
		
		map_memlist.put(vnet_id, memlist);
		
		List<Map<String, String>> return_list = new ArrayList<Map<String, String>>();
		try {
		
			
			int i1 = ofportFlexToJava(ofports,vnet_id);
			
			int i2 = controllerFlexToJava(controllers,vnet_id);
			
			int i3 = switchFlexToJava(switches,vnet_id);
			
			int i4 = hostFlexToJava(hosts,vnet_id);
			
			int i5 = oflinkFlexToJava(oflinks,vnet_id);
			
			int i6 = linkFlexToJava(links,vnet_id);
			
			int i7= instanceFlexToJava(instances,vnet_id);
			
			int i8 = vnetFlexToJava(vnet,vnet_id);
			
			int i9= requestFlexToJava(requests,vnet_id);	
			
		//	int i10= osportFlexToJava(osports,vnet.getVnet_id());
			
			int i11 = executor_vnetFlexToJava(e_v,vnet_id);
			
			int i12= instance_osportFlexToJava(instance_osport,vnet_id);
			
			int i13 =flavor_instanceFlexToJava(flavor_instance,vnet_id);
			
			int i14= image_instanceFlexToJava(image_instance,vnet_id);
			
			int i15=instance_controllerFlexToJava(instance_controller,vnet_id);
			
			int i16= instance_hostFlexToJava(instance_host,vnet_id);			
			
			int i17 = vnet_resourceFlexToJava(vnet_resources,vnet_id);
			
			int i18 = switch_ofportsFlexToJava(switch_ofports,vnet_id);
			
			Thread.sleep(1000);
			
			return_list = requestHandle(vnet_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return return_list;
	}

/*	Request Type      Meaning          vnet ID      Content ID
	1             	Create Vnet           *              -
	2          		Delete Vnet           *              -
	3          		Create Vhost          *           Host ID
	4          		Delete Vhost    	  *           Host ID
	5          		Create Vswitch     	  *           Switch ID
	6         		Delete Vswitch     	  *           Switch ID
	7          		Create Vcontroller    *           Controller ID
	8          		Delete Vcontroller    *           Controller ID
	9          		Create Vlink      	  *           Link ID
	10         		Delete Vlink   	      *           Link ID
	11				Create oflink 		  *			  oflink ID
	12				Delete oflink         *           oflink ID
	
*/
	@Override
	public int createInstance(String temp_ins,String temp_ins_type,Integer compute_zone_id,String tag)
	{
		System.out.println(" creating instance ****************");
		//flavor id
		int temp_ins_flavor_id=0;    
		//flavor osid
		String temp_ins_flavor_osid="";
		
		//find out flavor
		for(int in_flavor=0;in_flavor<map_memlist.get(tag).flavor_instancelist.size();in_flavor++)
		{
			if(map_memlist.get(tag).flavor_instancelist.get(in_flavor).getInstance_id().equals(temp_ins))
			{
				temp_ins_flavor_id=map_memlist.get(tag).flavor_instancelist.get(in_flavor).getFlavor_id();
				temp_ins_flavor_osid=flavordao.getOsid(temp_ins_flavor_id);
				System.out.println(temp_ins_flavor_osid + "  ##temp_ins_flavor_osid##");
				break;
			}
		}
		
		//image id
		int temp_ins_ima_id=0;
		//image osid
		String temp_ins_ima_osid="";
		
		for(int in_image=0;in_image<map_memlist.get(tag).image_instancelist.size();in_image++)
		{
			if(map_memlist.get(tag).image_instancelist.get(in_image).getInstance_id().equals(temp_ins))
			{
				temp_ins_ima_id=map_memlist.get(tag).image_instancelist.get(in_image).getImage_id();
				temp_ins_ima_osid=imagedao.getOsid(temp_ins_ima_id);
				System.out.println(temp_ins_ima_osid + "  ##temp_ins_ima_osid##");
				break;
			}
		}
		//save at config file
		String temp_network_osid="";
		String temp_subnet_osid="";
		
		
		String temp_floatingip_osid="";
		Integer temp_floatingip_id=0;
		
		if(temp_ins_type.equals("host"))
		{
			temp_network_osid=AppProperties.getVnet_host_network_id();
			temp_subnet_osid=AppProperties.getVnet_host_subnet_id();
			for(int in_host=0;in_host<map_memlist.get(tag).instance_hostlist.size();in_host++)
			{
				if(map_memlist.get(tag).instance_hostlist.get(in_host).getInstance_id().equals(temp_ins))
				{
					if(map_memlist.get(tag).hostlist.get(in_host).getHost_config() == 1)
					{
						synchronized (this) {
							while(floatingip_flag==0);
							
							floatingip_flag=0;
							Vnet_floatingip temp_floatingip=floatingipdao.getRandomFloatingip("DOWN");
							temp_floatingip.setFloatingip_status("TAKEN");
							floatingipdao.updateFloatingipByOsid(temp_floatingip);
							floatingip_flag=1;
							temp_floatingip_osid=temp_floatingip.getFloatingip_osid();
							temp_floatingip_id=temp_floatingip.getFloatingip_id();
						}
					}
				}
			}
		}
		else if(temp_ins_type.equals("controller"))
		{
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ controller  !!!!!!!!!!!!!@@@@@@@");
			temp_network_osid=AppProperties.getVnet_controller_network_id();
			temp_subnet_osid=AppProperties.getVnet_controller_subnet_id();
			
			// only if it is controller, get random floatingip with state as DOWN
			
			// waiting until the signal is not 0
			synchronized (this) {
				while(floatingip_flag==0);
		
				floatingip_flag=0;
				Vnet_floatingip temp_floatingip=floatingipdao.getRandomFloatingip("DOWN");
				// set floatingip state as TAKEN and add it into database
				temp_floatingip.setFloatingip_status("TAKEN");
		    	floatingipdao.updateFloatingipByOsid(temp_floatingip);
		    	floatingip_flag=1;
				temp_floatingip_osid=temp_floatingip.getFloatingip_osid();
				temp_floatingip_id=temp_floatingip.getFloatingip_id();
			}
			
		}
		
		System.out.println("########" + temp_floatingip_osid);
		System.out.println("network_osid" + temp_network_osid);
		System.out.println("subnet_osid" + temp_subnet_osid);
		
		//according to compute id
		String compute_zone_name=computedao.getById(compute_zone_id).getCompute_name();
		
		// return value is stack_id
		System.out.println(temp_ins_flavor_osid+ "%%%^^^%%%^^^" + temp_ins_ima_osid);
		int stack_id= opsheatservice.createInstance(temp_ins_type,temp_ins,temp_ins_flavor_osid, temp_ins_ima_osid, temp_network_osid, temp_subnet_osid, temp_floatingip_osid,compute_zone_name);
		
		if(temp_ins_type.equals("controller"))
		{
			// if is controller, after seting up, add its floatingip id into controller database table
			String temp_controller_id=instance_controllerdao.getController_id(temp_ins);
			
			Vnet_controller temp_controller=new Vnet_controller(temp_controller_id,"","",temp_floatingip_id);
			controllerdao.updateController(temp_controller);
			// add new data into compute_controller database table
			Vnet_compute_controller temp_compute_controller=new Vnet_compute_controller(compute_zone_id,temp_controller_id);
			compute_controllerdao.insertCompute_controller(temp_compute_controller);
			
		}
		else if(temp_ins_type.equals("host"))
		{
			// add new data into compute_host database table
			String temp_host_id=instance_hostdao.getByInstance_id(temp_ins).getHost_id();
			Vnet_compute_host temp_compute_host=new Vnet_compute_host(compute_zone_id,temp_host_id);
			compute_hostdao.insertCompute_host(temp_compute_host);
			
			//if is host，after seting up, similar to controller，add its floatingip id into host database table
			Vnet_host temp_host=new Vnet_host(temp_host_id,"",0,temp_floatingip_id);
			hostdao.updateHost(temp_host);
		}
		
		return stack_id;
		// return stack id
		
	}
	
	@Override
	public int deleteInstance(String content_id,String content_type){
		//  content_id can not only be controller_id, but also can be host_id
		System.out.println(" deleting instance &&&&&&&&&&&&&&&&&&&");
		if(content_type.equals("host"))
		{
			//find out compute node url by host_id
//			Integer compute_zone_id=compute_hostdao.getByHostId(content_id).getCompute_id();
//			String compute_url=computedao.getById(compute_zone_id).getCompute_addr();
			String compute_url=computedao.getByHostId(content_id).getCompute_addr();
			
			//find out host's stack by host_id
//			String instance_id=instance_hostdao.getByHost_id(content_id).getInstance_id();
//			int stack_id=stack_instancedao.getByInstanceId(instance_id).getStack_id();
//			Vnet_stack temp_stack=stackdao.getById(stack_id);
			Vnet_stack temp_stack=stackdao.getByHostId(content_id);
			
			opsheatservice.deleteInstance(temp_stack.getStack_name(), temp_stack.getStack_osid(),compute_url);
			//deleting is over, start to delete and update associated table items
			
			//update database
			//set instance's state item as deleted
			//set stack's state item as deleted
			//set osport's state item as deleted
			Vnet_instance temp_instance=instancedao.getByHostId(content_id);
//			Vnet_instance temp_instance=instancedao.getById(instance_id);
			temp_instance.setInstance_status("DELETED");
			instancedao.updateInstance(temp_instance);
			
			//set osport's state item as deleted
			temp_stack.setStack_status("DELETED");
			stackdao.updateStack(temp_stack);
			
			//set used floatingip's state as DOWN
			int floatingip_id=hostdao.getById(content_id).getFloatingip_id();
			floatingipdao.setDownById(floatingip_id);
			
//			String osport_id=instance_osportdao.getOsport_id(instance_id);
//			Vnet_osport temp_osport=osportdao.getById(osport_id);
			Vnet_osport temp_osport=osportdao.getbyHostid(content_id);
			temp_osport.setOsport_status("DELETED");
			
			osportdao.updateOsport(temp_osport);
			
		}
		else
		{
			//if is deleted controller, retrieve its floatingip
//			Integer compute_zone_id=compute_controllerdao.getByControllerId(content_id).getCompute_id();
//			String compute_url=computedao.getById(compute_zone_id).getCompute_addr();
			String compute_url=computedao.getByConId(content_id).getCompute_addr();


			// find out stack by host_id
//			String instance_id=instance_controllerdao.getInstance_id(content_id);
//			int stack_id=stack_instancedao.getByInstanceId(instance_id).getStack_id();
//			Vnet_stack temp_stack=stackdao.getById(stack_id);
			Vnet_stack temp_stack=stackdao.getByConId(content_id);
			
			opsheatservice.deleteInstance(temp_stack.getStack_name(),temp_stack.getStack_osid(), compute_url);
			
			//update database
			//set instance's state item as deleted
			//set stack's state item as deleted
			
			Vnet_instance temp_instance=instancedao.getByConId(content_id);
			temp_instance.setInstance_status("DELETED");
			instancedao.updateInstance(temp_instance);
			
			temp_stack.setStack_status("DELETED");
			stackdao.updateStack(temp_stack);
			
			//set used floatingip's state as DOWN
			int floatingip_id=controllerdao.getById(content_id).getFloatingip_id();
			floatingipdao.setDownById(floatingip_id);
			
			//find out osport by controller_id 
//			String osport_id=instance_osportdao.getOsport_id(instance_id);
//			Vnet_osport temp_osport=osportdao.getById(osport_id);
			Vnet_osport temp_osport=osportdao.getbyConid(content_id);
			temp_osport.setOsport_status("DELETED");
			osportdao.updateOsport(temp_osport);
			
		}
		//update resource database table 
		vnet_resourcedao.setDeleted(content_id);
		return 0;
	}

	@Override
	public List<Map<String, String>> requestHandle(String tag) throws InterruptedException
	{
		Vnet_vnet temp_vnet=vnetdao.getById(tag);
		temp_vnet.setVnet_status("wait");
		vnetdao.updateVnet(temp_vnet);
		
		List<Map<String, String>> return_list_reqHandle = new ArrayList<Map<String,String>>();
		
		int requestSize=map_memlist.get(tag).requestlist.size();
		
		Vnet_requestComparator comp=new Vnet_requestComparator();
		
		Collections.sort(map_memlist.get(tag).requestlist,comp);
		
		//before setup link, all stacks are checked
		Map<Integer,Integer> stackid_map=new HashMap<Integer,Integer>();
		
		
		//required vm number to create
		int count_instance_to_create=0;
		//vm number during creating
		int count_instance_creating=0;
		//singale, 0: unfinished; 1: all finished
		int instance_create_finished=0;
		
		for(int i=0;i<requestSize;i++)
		{
			if(map_memlist.get(tag).requestlist.get(i).getRequest_type().equals("3")||map_memlist.get(tag).requestlist.get(i).getRequest_type().equals("7"))
				count_instance_to_create++;
		}
		
		for(int i=0;i<requestSize;i++)
		{ 
			//all operations in this iteration are against one request
			
			Vnet_request temp_request=map_memlist.get(tag).requestlist.get(i);
			
			System.out.println("currently deal with request: " + temp_request.getRequest_id()+"###################");
			System.out.println("current count_instance_to_create："+count_instance_to_create+"################");
			System.out.println("current count_instance_creating："+count_instance_creating+"################");
			System.out.println("current instance_create_finished："+instance_create_finished+"################");
			
			if(count_instance_to_create!=0&&count_instance_to_create==count_instance_creating&&instance_create_finished==0)
			{
				//make sure all instances are created completely
				while(true)
				{
					Thread.sleep(5000);
					
					boolean flag=true;
					
					String stack_lists=opsheatservice.ListStack();
					
					JSONObject jo1=JSONObject.fromObject(stack_lists);
					
					JSONArray jo2=jo1.getJSONArray("stacks");
					
					for(int ik=0;ik<jo2.size();ik++)
					{
						JSONObject jo3=JSONObject.fromObject(jo2.get(ik));
						Integer dd=Integer.parseInt(jo3.getString("stack_name").substring(5));
						if(!stackid_map.containsKey(dd))
							//if the stack is not created stack, jump to the next iteration
							continue;
						
						String stack_status=jo3.getString("stack_status");
						if(!stack_status.equals("CREATE_COMPLETE"))
						{
							flag=false;  
							//find out stack failed to create, flag with false
							//and jump to outside of iteration
							break;
						}
						
					}
					
					if(flag==true)
					{
						//all stacks are created completely
						//find out all stacks by a stack list, get their id and name
						int send_counter2 = 0;
						String json_id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + map_memlist.get(tag).executor_vnet.getExecutor_id();
						for(int ik=0;ik<jo2.size();ik++)
						{
							JSONObject jo3=JSONObject.fromObject(jo2.get(ik));
							Integer dd=Integer.parseInt(jo3.getString("stack_name").substring(5));
							if(!stackid_map.containsKey(dd))
								continue;
							String stack_name=jo3.getString("stack_name");
							String stack_id=jo3.getString("id");
							
							String show_stack_result= opsheatservice.ShowStack(stack_name, stack_id);
							
							String temp_stack_osid="";
						     
						     String temp_ins_osid="";
						     //host's os and id
						     
						     String temp_ins_ip="";
						     //host's ip
						     
						     
						     String temp_osport_osid="";
						     //instance port's os and id
						     
						     //temp_ins_id  primary key
						     
						     JSONObject hj1=JSONObject.fromObject(show_stack_result);
						     JSONObject hj2=hj1.getJSONObject("stack");
						     temp_stack_osid=hj2.getString("id");
						     System.out.println("\n\n" + hj1);
						     JSONArray hj3=hj2.getJSONArray("outputs");
						     for(int index=0;index<hj3.size();index++)
						     {
						    	 JSONObject hj4=JSONObject.fromObject(hj3.get(index));
						    	 String output_key=hj4.getString("output_key");
						    	 if(output_key.equals("instance_addr"))
						    	 {
						    		 temp_ins_ip=hj4.getString("output_value");
						    	 }
						    	 else if(output_key.equals("instance_osid"))
						    	 {
						    		 temp_ins_osid=hj4.getString("output_value");
						    	 }
						    	 else if(output_key.equals("instance_osport_osid"))
						    	 {
						    		 temp_osport_osid=hj4.getString("output_value");
						    	 }
						    	 
						     }     
						     
						     //above is creatation procedure, have finished. Then deal with backend database
						     //update database table, sum up 4 tables
						     //set stack's state as ACTIVE, add other stack's info into database
						     //instance   update instance_osid, set its state as ACTIVE
						     //ip   	  update its state
						     
						     
						     // osport	   create new item 
						     // instance_osport  create new associated item

						     System.out.println(stack_name + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
						     int temp_stack_id=Integer.parseInt(stack_name.substring(5));
						     System.out.println(temp_stack_id + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
						     Vnet_stack t_stack=new Vnet_stack(temp_stack_id,stack_id,stack_name,"","ACTIVE");
						     stackdao.updateStack(t_stack);
						     
						     //update instance database table
						     
//						     String temp_ins_id=stack_instancedao.getByStackId(temp_stack_id).getInstance_id();
						     Vnet_instance t_instance=instancedao.getByStackId(temp_stack_id);
						     t_instance.setInstance_osid(temp_ins_osid);
						     t_instance.setInstance_status("ACTIVE");
						     instancedao.updateInstance(t_instance);
						     
						     
						     //update ip database table 
						     //temp_ins_ip, as ip, is index item
//						     Vnet_ip t_ip=new Vnet_ip(temp_ins_ip,"ACTIVE");
						     Vnet_ip t_ip=ipdao.getByAddr(temp_ins_ip);
						     t_ip.setIp_status("ACTIVE");
						     
//						     //get ip's id
//						     temp_ins_ip_id=((Vnet_ip)ipdao.getByAddr(temp_ins_ip)).getIp_id();
						     
						     //update by ip
						     ipdao.updateByIpAddr(t_ip);
						     
						     
						     //create new osport
							 Date dt=new Date();
							 //DateFormat df=new SimpleDateFormat("HHmmss");
							
						   	 DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
							
							 String nowTime="";
							 nowTime=df.format(dt);
						  
						     String temp_osport_id="osport" + nowTime + ik;
						     Vnet_osport t_osport=new Vnet_osport(temp_osport_id,temp_osport_osid,"","","ACTIVE",t_ip.getIp_id());
						     //insert new osport item
						     osportdao.insertOsport(t_osport);
						     
						     //create new instance_osport
						     Vnet_instance_osport t_instance_osport=new Vnet_instance_osport(temp_osport_id,t_instance.getInstance_id());
						     instance_osportdao.insertInstanceOsport(t_instance_osport);
						     
						    //send http request into instance, and setup WebSocket, make sure ip is correctly configured
							String image_name="";
							for(int in_image=0;in_image<map_memlist.get(tag).image_instancelist.size();in_image++)
							{
								if(map_memlist.get(tag).image_instancelist.get(in_image).getInstance_id().equals(t_instance.getInstance_id()))
								{
									int image_id=map_memlist.get(tag).image_instancelist.get(in_image).getImage_id();
									image_name=imagedao.getimage_name(image_id);
									break;
								}
							}
							
						    Map<String, String> map3 = new HashMap<String, String>();
						    map3.put("instance_id", t_instance.getInstance_id());
						    if(t_instance.getInstance_type().equals("Controller")){
						    	map3.put("ip_addr", floatingipdao.getbyInsid(t_instance.getInstance_id()).getFloatingip_addr());
						    }
						    else{
						    	map3.put("ip_addr", temp_ins_ip);
						    }
							
							int tmp_host_config=0;
							int tmp_floatingip_id=0;
							String tmp_floatingip="";
							for(int ins_host=0;ins_host<map_memlist.get(tag).instance_hostlist.size();ins_host++){
								if(map_memlist.get(tag).instance_hostlist.get(ins_host).getInstance_id().equals(t_instance.getInstance_id())){
									
									String host_id = map_memlist.get(tag).instance_hostlist.get(ins_host).getHost_id();
									tmp_host_config = hostdao.getById(host_id).getHost_config();
									if(hostdao.getById(host_id).getHost_config() == 1){
										System.out.println("###############################################");
										System.out.println(tmp_host_config);
										System.out.println("###############################################");
										tmp_floatingip_id = hostdao.getById(host_id).getFloatingip_id();
										System.out.println("###############################################");
										System.out.println(tmp_floatingip_id);
										System.out.println("###############################################");
										tmp_floatingip = floatingipdao.getById(tmp_floatingip_id).getFloatingip_addr();
										System.out.println("###############################################");
										System.out.println(tmp_floatingip);
										System.out.println("###############################################");
										map3.put("floating_IP",tmp_floatingip);
									}
								}
							}
							
						    //send ip, instance_id to frontend
						    return_list_reqHandle.add(map3);
						    
							if(!image_name.equals("rhel")){
								System.out.println("send http request into instance, and setup WebSocket, make sure ip is correctly configured");

								Vnet_WSRequest req3 = new Vnet_WSRequest(json_id, "send_for_dhcp", null, null, null, null, null);
								System.out.println(t_instance.getInstance_id() + "  ###$$$send_for_dhcp###$$$");

								websocket.create_Websocket(req3, "send", temp_ins_osid, image_name, temp_ins_ip,tmp_host_config,tmp_floatingip);
								send_counter2++;
							}
						} //end stack_list
						if(send_counter2++ > 0){
							System.out.println("query_for_dhcp##################################");

							Vnet_WSRequest req4 = new Vnet_WSRequest(json_id, "query_for_dhcp", null, null, null, null, null);

		  					int n = 0;
							while (true) {
								String res = websocket.create_Websocket(req4, "recv", "", "", "",0,"");
								if(res.contains("READY")){
									System.out.println("READY!");
									break;
								}
								else if(res.contains("BAD")){
									n++;
									System.out.println("BAD!");
									if(n > 6){
										System.out.println("serial request timeout, continue other operations");
										break;
									}
									Thread.sleep(8000);
								}
								else if(res.contains("NULL")){
									System.out.println("NULL!");
									Thread.sleep(2000);
								}
								else{
									System.out.println("exit!");
									System.out.println(res);
									break;
								}
							}
						}
						break;
					}
					
				}
				instance_create_finished=1;
				
			}
			
			if(temp_request.getRequest_type().equals("1"))
			//if request's type is 1, create a new topology
			{
				
				//controllers's id
				ArrayList<String> temp_controllers=new ArrayList<String>();
				
				//switches's id
				ArrayList<String> temp_switches=new ArrayList<String>();
				
				//hosts's id
				ArrayList<String> temp_hosts=new ArrayList<String>();
				
				//links' id
				ArrayList<String> temp_links=new ArrayList<String>();
				
				//oflink's id
				ArrayList<String> temp_oflinks=new ArrayList<String>();
				
				
				temp_controllers.clear();
				temp_switches.clear();
				temp_hosts.clear();
				temp_links.clear();
				temp_oflinks.clear();
				
				Map<Integer,Integer> stack_id_map=new HashMap<Integer,Integer>();
				for(int j=0;j<map_memlist.get(tag).vnet_resourcelist.size();j++)
				{
					//resolve resource type: host controller switch
					
					//find out and save into temp_instances
					//if(map_memlist.get(tag).vnet_resourcelist.get(j).getVnet_id().equals(temp_vnet_id)&&map_memlist.get(tag).vnet_resourcelist.get(j).getResource_type().equals("instances"))
					//	temp_instances.add(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_id());
					
					//search vnet_resource table, save controller's id into array
					if(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_type().equals("Controller"))
						temp_controllers.add(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_id());
					
					//search vnet_resource table, save switch's id into array
					if(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_type().equals("Switch"))
						temp_switches.add(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_id());
					
					//search vnet_resource table, save host's id into array
					if(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_type().equals("Host"))
						temp_hosts.add(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_id());
					
					//search vne_resource table, save link's id into array
					if(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_type().equals("link"))
						temp_links.add(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_id());
					
					//search vnet_resourcetable, save oflink's id into array
					if(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_type().equals("oflink"))
						temp_oflinks.add(map_memlist.get(tag).vnet_resourcelist.get(j).getResource_id());
									
				}
				System.out.println("temp_host:#############" + temp_hosts.toString());
				//  first deal with all hosts
				for(int k=0;k<temp_hosts.size();k++)
				{
					String temp_host=temp_hosts.get(k);
					String temp_ins="";  //host's instance_id
				//	String temp_ins_type="host";
					
					for(int jj=0;jj<map_memlist.get(tag).instance_hostlist.size();jj++)
					{
						if(temp_host.equals(map_memlist.get(tag).instance_hostlist.get(jj).getHost_id()))
						{
							temp_ins=map_memlist.get(tag).instance_hostlist.get(jj).getInstance_id();
							break;

						}
					}
					
					// dst compute id
					int compute_number=AppProperties.getVnet_compute_num();
					String compute_zone_name="";
					
					/*************************************************************************************************************
					/*   According to a dedicated load balance strategy, get the compute_zone_id where the host should be
					/*   Current test strategy: randomly get a integer within the scope of compute node num, and create host on it
					**************************************************************************************************************/
					Random ran=new Random();
					int compute_zone_count=ran.nextInt(compute_number)+1;
					if(compute_zone_count == 1){
						compute_zone_name = "zone-compute1";
					}else{
						compute_zone_name = "zone-compute2";
					}
					int compute_zone_id = computedao.getByCompute_name(compute_zone_name);
					
					int stack_id=createInstance(temp_ins,"host",compute_zone_id,tag);
					
					stack_id_map.put(stack_id, 1);
					Vnet_stack_instance temp_s_i=new Vnet_stack_instance(stack_id,temp_ins);
					
					// add stack_instance item into database table
					stack_instancedao.insertStack_instance(temp_s_i);
				}
				
				// deal with all controllers
				for(int k=0;k<temp_controllers.size();k++)
				{
					//controller id
					String temp_controller=temp_controllers.get(k);
					//controller's instance id
					String temp_ins="";

					for(int jj=0;jj<map_memlist.get(tag).instance_controllerlist.size();jj++)
					{
						if(temp_controller.equals(map_memlist.get(tag).instance_controllerlist.get(jj).getController_id()))
						{
							temp_ins=map_memlist.get(tag).instance_controllerlist.get(jj).getInstance_id();
							break;
						}
					}
					
					// dst compute id
					int compute_number=AppProperties.getVnet_compute_num();
					
					
					/*******************************************************************************************************************
					/*   According to a dedicated load balance strategy, get the compute_zone_id where the controller should be
					/*   Current test strategy: randomly get a integer within the scope of compute node num, and create controller on it
					********************************************************************************************************************/
					Random ran=new Random();
					int compute_zone_id=ran.nextInt(compute_number)+1;
					
					int stack_id=createInstance(temp_ins,"controller",compute_zone_id,tag);
					stack_id_map.put(stack_id, 1);
					Vnet_stack_instance temp_s_i=new Vnet_stack_instance(stack_id,temp_ins);
					// add stack_instance item into database table
					
					stack_instancedao.insertStack_instance(temp_s_i);
				}
				// waiting until backend's openstack resource have finished building

				while(true)
				{
					Thread.sleep(10000);
					
					boolean flag=true;
					
					String stack_lists=opsheatservice.ListStack();
					//liststack, including previous stack
					
					JSONObject jo1=JSONObject.fromObject(stack_lists);
					
					JSONArray jo2=jo1.getJSONArray("stacks");
					
					for(int ik=0;ik<jo2.size();ik++)
					{
						JSONObject jo3=JSONObject.fromObject(jo2.get(ik));
						int dd=Integer.parseInt(jo3.getString("stack_name").substring(5));
						if(!stack_id_map.containsKey(dd))
							//if the stack is not created stack, jump into next iteration
							continue;
						
						String stack_status=jo3.getString("stack_status");
						if(!stack_status.equals("CREATE_COMPLETE"))
						{
							flag=false;  
							//found out failed stack, flag it as false
							//jump into outside of iteration
							break;
						}
					}
					if(flag==true)
					{
						//found out all stacks have been finished building
						//find out all stack by stack-list, get their id and name
						int send_counter = 0;
						String json_id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + map_memlist.get(tag).executor_vnet.getExecutor_id();
						for(int ik=0;ik<jo2.size();ik++)
						{
							JSONObject jo3=JSONObject.fromObject(jo2.get(ik));
							Integer dd=Integer.parseInt(jo3.getString("stack_name").substring(5));
							if(!stack_id_map.containsKey(dd))
								continue;
							String stack_name=jo3.getString("stack_name");
							String stack_id=jo3.getString("id");
							
							String show_stack_result= opsheatservice.ShowStack(stack_name, stack_id);
							
							String temp_stack_osid="";
						     
						     String temp_ins_osid="";
						     //host os id
						     
						     String temp_ins_ip="";
						     //host ip
						     
						     int temp_ins_ip_id=0;
						     //instance ip's primary key
						     
						     String temp_osport_osid="";
						     //instance port's os id
						     
						     String temp_osport_osid2="";
						     //instance port2's os id 16-8-31 by ychuang
						     
						     String temp_instance_ip2="";
						     
						     //temp_ins_id is the primary key of created vm
						     
						     JSONObject hj1=JSONObject.fromObject(show_stack_result);
						     JSONObject hj2=hj1.getJSONObject("stack");
						     temp_stack_osid=hj2.getString("id");
						     System.out.println("\n\n" + hj1);
						     JSONArray hj3=hj2.getJSONArray("outputs");
						     for(int index=0;index<hj3.size();index++)
						     {
						    	 JSONObject hj4=JSONObject.fromObject(hj3.get(index));
						    	 String output_key=hj4.getString("output_key");
						    	 if(output_key.equals("instance_addr"))
						    	 {
						    		 temp_ins_ip=hj4.getString("output_value");
						    	 }
						    	 else if(output_key.equals("instance_osid"))
						    	 {
						    		 temp_ins_osid=hj4.getString("output_value");
						    	 }
						    	 else if(output_key.equals("instance_osport_osid"))
						    	 {
						    		 temp_osport_osid=hj4.getString("output_value");
						    	 }
						    	 else if(output_key.equals("instance_osport_osid2"))
						    	 {
						    		 temp_osport_osid2=hj4.getString("output_value");
						    	 }
						    	 else if(output_key.equals("instance_ip2"))
						    	 {
						    		 temp_instance_ip2=hj4.getString("output_value").substring(2,hj4.getString("output_value").length()-2);
						    		 System.out.println("########################################"+temp_instance_ip2+"########################################");
						    	 }
						     }     
						     
						     //above is creation procedure, have finished. Then, start to deal with backend's database table
						     //update database table, sum up 4 tables
						     //set stack's state as ACTIVE and add other stack related info into database table
						     //update instance's instance_osid and set its state as ACTIVE
						     //update floatingip's state
						     //update ip's state
						     
						     
						     // create new osport item
						     
						     // create new associated item instance_osport  
						     
						     System.out.println(stack_name + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
						     int temp_stack_id=Integer.parseInt(stack_name.substring(5));
						     System.out.println(temp_stack_id + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//						     Vnet_stack t_stack=new Vnet_stack(temp_stack_id,stack_id,stack_name,"","ACTIVE");
						     Vnet_stack t_stack=stackdao.getById(temp_stack_id);
						     // set stack's osid
						     t_stack.setStack_osid(stack_id);
						     t_stack.setStack_name(stack_name);
						     t_stack.setStack_status("ACTIVE");
						     stackdao.updateStack(t_stack);
						     
						     //update instance database table
//						     String temp_ins_id=stack_instancedao.getByStackId(temp_stack_id).getInstance_id();
//						     Vnet_instance t_instance=new Vnet_instance(temp_ins_id,temp_ins_osid,"ACTIVE");
						     Vnet_instance t_instance=instancedao.getByStackId(temp_stack_id);
						     t_instance.setInstance_osid(temp_ins_osid);
						     t_instance.setInstance_status("ACTIVE");
						     instancedao.updateInstance(t_instance);
						     
						     //update ip database table
						     //temp_ins_ip is index, have same value with ip
//						     Vnet_ip t_ip=new Vnet_ip(temp_ins_ip,"ACTIVE");
						     Vnet_ip t_ip=ipdao.getByAddr(temp_ins_ip);
						     t_ip.setIp_status("ACTIVE");
						     
//						     //get ip's id
//						     temp_ins_ip_id=((Vnet_ip)ipdao.getByAddr(temp_ins_ip)).getIp_id();
						    
						     //update by ip
						     ipdao.updateByIpAddr(t_ip);
						     
						     //create new table osport
							 Date dt=new Date();
							 //DateFormat df=new SimpleDateFormat("HHmmss");
							
						   	 DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
							
							 String nowTime="";
							 nowTime=df.format(dt);
						  
						     String temp_osport_id="osport" + nowTime + ik;
						     Vnet_osport t_osport=new Vnet_osport(temp_osport_id,temp_osport_osid,"","","ACTIVE",t_ip.getIp_id());
						     //add newly created osport item
						     osportdao.insertOsport(t_osport);
						     
						     //create instance_osport
						     Vnet_instance_osport t_instance_osport=new Vnet_instance_osport(temp_osport_id,t_instance.getInstance_id());
						     instance_osportdao.insertInstanceOsport(t_instance_osport);
						     
						     
						     //send ip, instance_id into frontend 
						     Map<String, String> map1 = new HashMap<String, String>();
						     map1.put("instance_id", t_instance.getInstance_id());
						     if(t_instance.getInstance_type().equals("Controller")){
						    	 map1.put("ip_addr", floatingipdao.getbyInsid(t_instance.getInstance_id()).getFloatingip_addr());
							 }
							 else{
							   	map1.put("ip_addr", temp_ins_ip);
							 }
						     return_list_reqHandle.add(map1);
				
						     
							//send http request into instance, setup WebSocket, make sure ip is installed correctly
							String image_name="";
							for(int in_image=0;in_image<map_memlist.get(tag).image_instancelist.size();in_image++)
							{
								if(map_memlist.get(tag).image_instancelist.get(in_image).getInstance_id().equals(t_instance.getInstance_id()))
								{
									int image_id=map_memlist.get(tag).image_instancelist.get(in_image).getImage_id();
									image_name=imagedao.getimage_name(image_id);
									break;
								}
							}
							int tmp_host_config=0;
							int tmp_floatingip_id=0;
							String tmp_floatingip="";
							for(int ins_host=0;ins_host<map_memlist.get(tag).instance_hostlist.size();ins_host++){
								if(map_memlist.get(tag).instance_hostlist.get(ins_host).getInstance_id().equals(t_instance.getInstance_id())){
									String host_id = map_memlist.get(tag).instance_hostlist.get(ins_host).getHost_id();
									if(hostdao.getById(host_id).getHost_config() == 1){
										tmp_host_config = hostdao.getById(host_id).getHost_config();
										System.out.println("###############################################");
										System.out.println(tmp_host_config);
										System.out.println("###############################################");
										tmp_floatingip_id = hostdao.getById(host_id).getFloatingip_id();
										System.out.println("###############################################");
										System.out.println(tmp_floatingip_id);
										System.out.println("###############################################");
										tmp_floatingip = floatingipdao.getById(tmp_floatingip_id).getFloatingip_addr();
										System.out.println("###############################################");
										System.out.println(tmp_floatingip);
										System.out.println("###############################################");
										map1.put("floating_IP", tmp_floatingip);
									}
								}
							}
							
							 return_list_reqHandle.add(map1);
							
							if(!image_name.equals("rhle")){
								System.out.println("send http request into instance, setup WebSocket, make sure ip is installed correctly");

								Vnet_WSRequest req1 = new Vnet_WSRequest(json_id, "send_for_dhcp", null, null, null, null, null);
								System.out.println(t_instance.getInstance_id() + "  ###$$$send_for_dhcp###$$$");

								websocket.create_Websocket(req1, "send", temp_ins_osid, image_name, temp_ins_ip,tmp_host_config,temp_instance_ip2);
								send_counter++;
							}
						} //end stack_list
						if(send_counter > 0){
							System.out.println("query_for_dhcp##################################");

							Vnet_WSRequest req2 = new Vnet_WSRequest(json_id, "query_for_dhcp", null, null, null, null, null);

							int n = 0;
							while (true) {
								String res = websocket.create_Websocket(req2, "recv", "", "", "",0,"");
								if(res.contains("READY")){
									System.out.println("READY!");
									break;
								}
								else if(res.contains("BAD")){
									n++;
									System.out.println("BAD!");
									if(n > 6){
										System.out.println("serial request timeout, continue other operations");
										break;
									}
									Thread.sleep(8000);
								}
								else if(res.contains("NULL")){
									System.out.println("NULL!");
									Thread.sleep(2000);
								}
								else{
									System.out.println("exit!");
									System.out.println(res);
									break;
								}
							}
						}
						break;
					}
				}
				
				// deal with all switchs, update corresponding database table
				for(int k=0;k<temp_switches.size();k++)
				{
					
					String temp_switch_id=temp_switches.get(k);
					int switch_counter=0;
					switch_counter=switchdao.getSwitch_counter(temp_switch_id);
					String temp_switch_name="s" + switch_counter;
					
					// dst compute id
					int compute_number=AppProperties.getVnet_compute_num();
					
					
					/*******************************************************************************************************************
					/*   According to a dedicated load balance strategy, get the compute_zone_id where the switch should be
					/*   Current test strategy: randomly get a integer within the scope of compute node num, and create switch on it
					********************************************************************************************************************/
					Random ran=new Random();
					int compute_zone_id=ran.nextInt(compute_number)+1;
					
					String compute_addr=computedao.getById(compute_zone_id).getCompute_addr();

					// first, create a bridge named temp_switch_name in the dst compute, as created switch
					ovsserviceimpl.add_br(compute_addr,temp_switch_name);

					
					// get entity by switch id, set its state and update properties such as switch_name
//					Vnet_switch t_switch=new Vnet_switch(temp_switch_id,temp_switch_name,switch_counter,"ACTIVE");
					Vnet_switch t_switch=switchdao.getById(temp_switch_id);
					t_switch.setSwitch_name(temp_switch_name);
					t_switch.setSwitch_status("ACTIVE");
					switchdao.updateSwitch(t_switch);
					
					//create compute_switch item
					Vnet_compute_switch temp_compute_switch=new Vnet_compute_switch(compute_zone_id,temp_switch_id);
					compute_switchdao.insertCompute_switch(temp_compute_switch);
					
				}
				
				// until above all steps are done, all database's
				// instance, host, controller, switch table have been finished building
				// all data will be updated, all osid wiil also be added into database
				
				//start to iterate all switch saved in memory, deal with link creation
				
				for(int k=0;k<map_memlist.get(tag).switchlist.size();k++)
				{
					//get each switch
					//sort switch port by frontend's num
					//then, create port in switch, the order of creation is same with num sorting
					//judge each ofport, 1: connecting two switch;
					//2: connecting host and switch
					//3: connecting switch and controller
					
					Vnet_switch v_switch=map_memlist.get(tag).switchlist.get(k);
					
					// get all used ofport in switch
					
					List<Vnet_switch_ofport> list_switch_ofport=switch_ofportdao.getBySwitchId(v_switch.getSwitch_id());
					List<Vnet_ofport> list_ofport=new ArrayList<Vnet_ofport>();
					
					for(int kk=0;kk<list_switch_ofport.size();kk++)
					{
						list_ofport.add(ofportdao.getById(list_switch_ofport.get(kk).getOfport_id()));
					}
					
					System.out.println("BEFORE FLAG!!!####!!!!!####!!###@#!#!####");
					System.out.println(list_ofport.toString());
					
					//  then sort list_ofport, and 
					//  find out each port and its opposite port in each link, and create corresponding port
					
					
					//  define a new sorter, resort list_ofport by the method of quick sorting
					Vnet_ofportComparator comp1=new Vnet_ofportComparator();
					Collections.sort(list_ofport,comp1);
					
					System.out.println("FLAG!!!!!####!!!!!####!!###@#!#!####");
					System.out.println(v_switch.getSwitch_id() + "%%%%%%%%%current switch's ofport num is: " + list_ofport.size());
					
					System.out.println(list_ofport.toString());
					
					//  iterate resorted list_ofport
					for(int ii=0;ii<list_ofport.size();ii++)
					{
						// src switch_id and dst switch_id
						String id_from="";
						String id_to="";
						
						// src switch_name and dst switch_name
						
						String  name_from="";
						String  name_to="";
						
						// src resource type and dst resource type 
						
						String  type_from="";
						String  type_to="";
						
						// ofport's temp
						Vnet_ofport tt_ofport= list_ofport.get(ii);
						
						for(int kk=0;kk<map_memlist.get(tag).linklist.size();kk++)
						{
							
							Vnet_link v_link=map_memlist.get(tag).linklist.get(kk);
							
							//signal, 1: src is from; 2: src is to
							int mark=0;
							
							if(v_link.getLink_ofport_src() != null && v_link.getLink_ofport_src().equals(tt_ofport.getOfport_id()))
							{
								
								//src switch_id info
								id_from=map_memlist.get(tag).linklist.get(kk).getLink_src_id();
								//dst switch_id info 
								id_to=map_memlist.get(tag).linklist.get(kk).getLink_dst_id();
								
								type_from=map_memlist.get(tag).linklist.get(kk).getLink_src_type();
								
								type_to=map_memlist.get(tag).linklist.get(kk).getLink_dst_type();
								mark=1;
							}
							
							else if(v_link.getLink_ofport_dst()!=null&&v_link.getLink_ofport_dst().equals(tt_ofport.getOfport_id()))
							{
								//src switch_id info
								id_from=map_memlist.get(tag).linklist.get(kk).getLink_dst_id();
								//dst switch_id info 
								id_to=map_memlist.get(tag).linklist.get(kk).getLink_src_id();
								
								type_from=map_memlist.get(tag).linklist.get(kk).getLink_dst_type();
								
								type_to=map_memlist.get(tag).linklist.get(kk).getLink_src_type();
								
								mark=2;
							}
							
							else
							{
								
								continue;
								
							}
							//src switch_name_from
							
							if(type_to.equals("Host"))
							{
								//if switch connected with host
								name_from=switchdao.getById(id_from).getSwitch_name();
								//dst instance port's osid
								
//								
//								System.out.println("print dst host's id################" + id_to);
//								String thisInstanceId=instance_hostdao.getInstance_id(id_to);
//								String thisOsportId=instance_osportdao.getOsport_id(thisInstanceId);
//								Vnet_osport thisOsport=osportdao.getById(thisOsportId);
//								String thisOsportOsid=thisOsport.getOsport_osid();
								
								Vnet_osport thisOsport=osportdao.getbyHostid(id_to);
								String thisOsportOsid=thisOsport.getOsport_osid();
								
								//below can be divided into two cases
								
								int from_compute_id=compute_switchdao.getBySwitchId(id_from).getCompute_id();
								int to_compute_id=compute_hostdao.getByHostId(id_to).getCompute_id();
								
								if(from_compute_id==to_compute_id)
								{
									System.out.println("switch and host located in the same compute node!!!!!!!!!!!!!!!!!!!!!");
									System.out.println("switch's id is" + id_from + "%%%%%%%%%%%%%%%%%%%%%%" + "host's id is" + id_to);
									//case 1: link's two end located in the same compute node
									String compute_url=computedao.getById(from_compute_id).getCompute_addr();
									ovsserviceimpl.set_vmlink(compute_url, name_from, thisOsportOsid);
									v_link.setLink_status("ACTIVE");
									linkdao.updateLink(v_link);
								}
								
								else
								{
									// need conduct set_switchlink for three times
									//case 2: link's two end located in the different compute nodes
									System.out.println("switch and host located in the different compute nodes*~!*~!*~!*~!*~!*~!*~!*~!*~!*~!*~!*~!*~!");
									System.out.println("switch's id is" + id_from + "%%%%%%%%%%%%%%%%%%%%%%" + "host's id is" + id_to);
									
//									//get the max index, add 1 as port number as well tag number
//									String temp_tunofport_id=compute_tunofportdao.getByComputeId(from_compute_id).getTunofport_id();
//									Vnet_tunofport temp_tunofport =tunofportdao.getById(temp_tunofport_id);
//									int portNum=temp_tunofport.getTunofport_portnum()+1;
//									temp_tunofport.setTunofport_portnum(portNum);
//									//update tunofport table, return current available max port num back into database
//									tunofportdao.updatePortNum(temp_tunofport);
									
									//randomly get a available tun
									Vnet_tun temp_tun=tundao.getRandom("unused");
									//tun_id 
									int temp_tun_id=temp_tun.getTun_id();
									//tun_tag
									String temp_tun_tag=temp_tun.getTun_tag();
									
									//update tun database table
									temp_tun.setTun_status("used");
									tundao.updateTun(temp_tun);
									
									// (1) connect the switch into compute1's vnet_br bridge
									String compute_url_1=computedao.getById(from_compute_id).getCompute_addr();
									// connect this switch and get the port number
									String vnet_br=AppProperties.getVnet_br();
									ovsserviceimpl.set_switchlink(compute_url_1, name_from, vnet_br,temp_tun_id);
									
									// (2) connect vnet-br into the switch
									ovsserviceimpl.set_switchlink(compute_url_1, vnet_br, name_from,temp_tun_id);
									
									// install flow entries by port number
									// vnet_br default use 1-ofport to connect with external network
									// flow entries installation
									
									String port_name=vnet_br+name_from+temp_tun_id;
									String ofport_result=ovsserviceimpl.get_portnumber(compute_url_1,port_name);
									JSONObject json1=JSONObject.fromObject(ofport_result);
									JSONObject json2=json1.getJSONObject("result");
									String portNumstr=json2.getString("output");
									int portNum=Integer.parseInt(portNumstr);
									
									String vnet_br_out_port="1";
									String res=ovsserviceimpl.set_flow(compute_url_1, temp_tun_tag, 
											Integer.toString(portNum) ,vnet_br_out_port);
									
									
									// (3) connect host into compute2's Vnet_br bridge
									String compute_url_2=computedao.getById(to_compute_id).getCompute_addr();
									// connect this host with compute node's vnet_br bridge, and get the connection's port number
									ovsserviceimpl.set_vmlink(compute_url_2,vnet_br,thisOsportOsid);
									
									
									
//									String temp_tunofport_id2=compute_tunofportdao.getByComputeId(to_compute_id).getTunofport_id();
//									Vnet_tunofport temp_tunofport2=tunofportdao.getById(temp_tunofport_id2);
//									int portNum2=temp_tunofport2.getTunofport_portnum()+1;
//									temp_tunofport2.setTunofport_portnum(portNum2);
//									//update tunofport table, return current available max port num back into database
//									tunofportdao.updatePortNum(temp_tunofport2);
									
									String port_name2="qvo" + thisOsportOsid.substring(0,11);
									String ofport_result2=ovsserviceimpl.get_portnumber(compute_url_2,port_name2);
									JSONObject json3=JSONObject.fromObject(ofport_result2);
									JSONObject json4=json3.getJSONObject("result");
									String portNumstr2=json4.getString("output");
									int portNum2=Integer.parseInt(portNumstr2);
									
									// conduct host end flow entries installation
									String vnet_br_out_port2="1";
									String res2=ovsserviceimpl.set_flow(compute_url_2, temp_tun_tag, 
											Integer.toString(portNum2) , vnet_br_out_port2);
									
									// update link table's tun_id
									// update link table's link_tunofport_src and link_tunofport_dst
									
									//mark is signal, 1: src is from; 2: src is to
									v_link.setTun_id(temp_tun_id);
									v_link.setLink_iscross(1);
									v_link.setLink_status("ACTIVE");
									if(mark==1)
									{
										v_link.setLink_tunofport_src(portNum);
										v_link.setLink_tunofport_dst(portNum2);
									}
									else if(mark==2)
									{
										v_link.setLink_tunofport_src(portNum2);
										v_link.setLink_tunofport_dst(portNum);
									}
									
									linkdao.updateLink(v_link);
								}
								//src switch's name and dst osport's osid have been gotten

								// update osport database table
								thisOsport.setOsport_status("ACTIVE");
								osportdao.updateOsport(thisOsport);
								
							
								
							}
							else if(type_to.equals("Switch"))
							{
								//if switch's opposite is also switch
								name_from=switchdao.getById(id_from).getSwitch_name();
								//dst switch_name_to
								name_to=switchdao.getById(id_to).getSwitch_name();

								//src switch's name and dst switch's name have been gotten
								
								
								int from_compute_id=compute_switchdao.getBySwitchId(id_from).getCompute_id();
								int to_compute_id=compute_switchdao.getBySwitchId(id_to).getCompute_id();
								
								
								System.out.println("link's two end compute nodes are&&&&&&&&&&&&&&&&&& " + from_compute_id + " & " + to_compute_id );
								if(from_compute_id==to_compute_id)
								{
									System.out.println("two switch are located in the same compute nodeKKKKKKKKKKKKKKKKKKKKKKKKK");
									System.out.println("switch's id is" + id_from + "%%%%%%%%%%%%%%%%%%%%%%" + "host's id is" + id_to);
									
									String compute_url=computedao.getById(from_compute_id).getCompute_addr();
									ovsserviceimpl.set_switchlink(compute_url, name_from, name_to,0);	
									v_link.setLink_status("ACTIVE");
									linkdao.updateLink(v_link);
								}
								else
								{
									//two switch are located in the different compute nodes
									//get available max port number from database, add 1 as current port number
									
									System.out.println("两个switch不在同一个compute节点上 NO NO NO NO NO NO NO NO NO NO NO");
									System.out.println("switch的id为 " + id_from + "%%%%%%%%%%%%%%%%%%%%%%" + "host的id为" + id_to);
									
//									String temp_tunofport_id=compute_tunofportdao.getByComputeId(from_compute_id).getTunofport_id();
//									Vnet_tunofport temp_tunofport=tunofportdao.getById(temp_tunofport_id);
//									int portNum=temp_tunofport.getTunofport_portnum()+1;
//									temp_tunofport.setTunofport_portnum(portNum);
//									//update tunofport database table
//									tunofportdao.updatePortNum(temp_tunofport);
									
									
									int temp_tun_id;
									String temp_tun_tag;
									System.out.println("getTun_id^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + v_link.getTun_id());
									if(v_link.getTun_id()==0)
									{
										//randomly get a available tun
										Vnet_tun temp_tun=tundao.getRandom("unused");
										//tun_tag
										temp_tun_tag=temp_tun.getTun_tag();
										temp_tun_id=temp_tun.getTun_id();
										System.out.println("temp_tun_id=" + temp_tun_id + "|||||||||||||||||||||||||||||||||..");
										//update tun database table
										temp_tun.setTun_status("used");
										tundao.updateTun(temp_tun);
										//tun_id 
										
									}
									else
									{
										temp_tun_id=v_link.getTun_id();
										System.out.println("temp_tun_id=" + temp_tun_id + "|||||||||||||||||||||||||||||||||..");
										temp_tun_tag=tundao.getById(temp_tun_id).getTun_tag();
									}
									// (1) from_switch connected with from_vnet-br
									String compute_url_1=computedao.getById(from_compute_id).getCompute_addr();
									
									String vnet_br=AppProperties.getVnet_br();
									ovsserviceimpl.set_switchlink(compute_url_1, name_from, vnet_br,temp_tun_id);
									// (2) vnet-br connected with from_switch
									ovsserviceimpl.set_switchlink(compute_url_1, vnet_br, name_from,temp_tun_id);
									
									String port_name=vnet_br+name_from+temp_tun_id;
									String ofport_result=ovsserviceimpl.get_portnumber(compute_url_1,port_name);
									JSONObject json1=JSONObject.fromObject(ofport_result);
									JSONObject json2=json1.getJSONObject("result");
									String portNumstr=json2.getString("output");
									int portNum=Integer.parseInt(portNumstr);
									
									
									// install flow entries by gotten port number
									// vnet_br default use 1-ofport to connect with external network
									String vnet_br_out_port="1";
									String res=ovsserviceimpl.set_flow(compute_url_1, temp_tun_tag, 
											Integer.toString(portNum) ,vnet_br_out_port);
									
									v_link.setTun_id(temp_tun_id);
									v_link.setLink_iscross(1);
									v_link.setLink_status("ACTIVE");
									
									if(mark==1)
									{
										v_link.setLink_tunofport_src(portNum);
									}
									else if(mark==2)
									{
										v_link.setLink_tunofport_dst(portNum);
									}
									linkdao.updateLink(v_link);
								}
						
							}
							// update database table item
							// ofport
							
							//update ofport database table 
							tt_ofport.setOfport_status("ACTIVE");
							ofportdao.updateOfport(tt_ofport);
							break;
							//from this port, jump outside the link's iteration, and walk into next port, find out next port related opposite port
						}
					}
				}
				
				// create the oflink between controller and switch
				for(int kk=0;kk<map_memlist.get(tag).oflinklist.size();kk++)
				{
					
					Vnet_oflink temp_oflink=map_memlist.get(tag).oflinklist.get(kk);
					System.out.println("create oflink$$$$$$$$$$$$$$$$$$$$$$$$$: " + temp_oflink.toString());
					String controller_id=temp_oflink.getController_id();
					String switch_id=temp_oflink.getSwitch_id();
					createOflink(controller_id,switch_id);
					
//					String temp_port=AppProperties.Vnet_floodlight_openflow_port();
//					
//					// find out floatingip by controller
////					int temp_floatingip_id=controllerdao.getById(temp_oflink.getController_id()).getFloatingip_id();
////					String temp_loatingip=floatingipdao.getById(temp_floatingip_id).getFloatingip_addr();
//					String temp_floatingip_addr=floatingipdao.getbyControllerid(temp_oflink.getController_id()).getFloatingip_addr();
//					
//					// find out dst switch's compute node url
////					Vnet_switch temp_switch=switchdao.getById(temp_oflink.getSwitch_id());
////					String temp_switch_name=temp_switch.getSwitch_name();
//					String temp_switch_name=switchdao.getById(temp_oflink.getSwitch_id()).getSwitch_name();
////					String temp_switch_id=temp_switch.getSwitch_id();
////					Integer temp_compute_id=compute_switchdao.getBySwitchId(temp_switch_id).getCompute_id();
////					String temp_compute_url=computedao.getById(temp_compute_id).getCompute_addr();
//					Vnet_compute temp_compute=computedao.getBySwitchId(temp_oflink.getSwitch_id());
//					
//					ovsserviceimpl.set_controller(temp_compute.getCompute_addr(), temp_switch_name, temp_floatingip_addr, temp_port);

					// update oflink back into database
					temp_oflink.setOflink_status("ACTIVE");
					oflinkdao.updateOflink(temp_oflink);
					System.out.println("successfully create oflink$$$$$$$$$$$$$$$$$$$$$$$$$: " + temp_oflink.toString());
				}

			}
			
			if(temp_request.getRequest_type().equals("2"))
			// create virtual network
			{
				// in the request list, if request type is 2, then the content indicates the vnet to to deleted 
				String vnet_id=temp_request.getContent_id();
				
				ArrayList<String> oflinks=vnet_resourcedao.getOflinkByVnet(vnet_id);
				ArrayList<String> links=vnet_resourcedao.getLinkByVnet(vnet_id);
				ArrayList<String> switches=vnet_resourcedao.getSwitchByVnet(vnet_id);
				ArrayList<String> hosts=vnet_resourcedao.getHostByVnet(vnet_id);
				ArrayList<String> controllers=vnet_resourcedao.getControllerByVnet(vnet_id);
				
				// (1) delete all links
				for(int kk=0;kk<links.size();kk++)
				{
					
					deleteLink(links.get(kk));
					vnet_resourcedao.setDeleted(links.get(kk));
				}
				
				// (2) first delete all oflink
				for(int kk=0;kk<oflinks.size();kk++)
				{
					deleteOflink(oflinks.get(kk));
					vnet_resourcedao.setDeleted(oflinks.get(kk));
				}

				// (3) delete all switchs
				for(int kk=0;kk<switches.size();kk++)
				{
					deleteSwitch(switches.get(kk));
					vnet_resourcedao.setDeleted(switches.get(kk));
				}
				// (4) delete all hosts
				for(int kk=0;kk<hosts.size();kk++)
				{
					deleteInstance(hosts.get(kk),"host");
					vnet_resourcedao.setDeleted(hosts.get(kk));
				}
				//  (5) delete all controllers
				for(int kk=0;kk<controllers.size();kk++)
				{
					deleteInstance(controllers.get(kk),"controller");
					vnet_resourcedao.setDeleted(controllers.get(kk));
					
				}
				//delete vnet_excutor_vnet by vnet_id
				executor_vnetdao.delByVnet_id(vnet_id);
				//update vnet table, set vnet table's state as DELETED
				Vnet_vnet t_vnet=vnetdao.getById(tag);
				t_vnet.setVnet_status("DELETED");
				vnetdao.updateVnet(t_vnet);
				
				
			}
			
			if(temp_request.getRequest_type().equals("3"))
			// 
			{
				System.out.println("Create Single Virtual Host");
				

				for (int i_h_num = 0; i_h_num < map_memlist.get(tag).instance_hostlist.size(); i_h_num++) 
				{
					
					if (map_memlist.get(tag).instance_hostlist.get(i_h_num).getHost_id().equals(temp_request.getContent_id())) 
					{
						Vnet_host temp_host=hostdao.getById(temp_request.getContent_id());
						String temp_ins=map_memlist.get(tag).instance_hostlist.get(i_h_num).getInstance_id();
						
						// get the count of compute node 
						int compute_number=AppProperties.getVnet_compute_num();
						/*******************************************************************************************************************
						/*   According to a dedicated load balance strategy, get the compute_zone_id where the host should be
						/*   Current test strategy: randomly get a integer within the scope of compute node num, and create host on it
						********************************************************************************************************************/
						Random ran=new Random();
						int compute_zone_id=ran.nextInt(compute_number)+1;
						
						//conduct the vm creation, return value is the stack_id of the, now we only save stack_id into database 
						//other stack's info still don't be taken care of so far
						int stack_id=createInstance(temp_ins, "host",compute_zone_id,tag);
						
						//stack's id into map
						stackid_map.put(stack_id, 1);
						
						//stack's count +1
						count_instance_creating++;
						
						Vnet_stack_instance temp_s_i=new Vnet_stack_instance(stack_id,temp_ins);
						
						// create stack_instance item and insert it into database
						stack_instancedao.insertStack_instance(temp_s_i);

						// jump outside
						break;
					}
				}
				
			}
			
			if(temp_request.getRequest_type().equals("4"))
			// destroy single vm 
			{
				deleteInstance(temp_request.getContent_id(),"host");
			}
			
			if(temp_request.getRequest_type().equals("5"))
			// create single switch
			{
				String switch_id=temp_request.getContent_id();
				int switch_counter=switchdao.getSwitch_counter(switch_id);
				
				/*******************************************************************************************************************
				/*   According to a dedicated load balance strategy, get the compute_zone_id where the switch should be
				/*   Current test strategy: randomly get a integer within the scope of compute node num, and create switch on it
				********************************************************************************************************************/
				int compute_number=AppProperties.getVnet_compute_num();
				Random ran=new Random();
				int compute_zone_id=ran.nextInt(compute_number)+1;
				
				String compute_addr=computedao.getById(compute_zone_id).getCompute_addr();
				
				ovsserviceimpl.add_br(compute_addr, "s"+switch_counter);
				
				// finish creating, update switch table's switch_name
				Vnet_switch temp_switch=new Vnet_switch(switch_id,"s"+switch_counter,switch_counter,"ACTIVE");
				switchdao.updateSwitch(temp_switch);
				
				// update compute_switch item
				Vnet_compute_switch temp_compute_switch=new Vnet_compute_switch(compute_zone_id,switch_id);
				compute_switchdao.insertCompute_switch(temp_compute_switch);
				
				
			}
			
			if(temp_request.getRequest_type().equals("6"))
			// destroy single switch
			{
				deleteSwitch(temp_request.getContent_id());

			}
			
			if(temp_request.getRequest_type().equals("7"))
			// create single controller
			{
				
				for (int i_h_num = 0; i_h_num < map_memlist.get(tag).instance_controllerlist.size(); i_h_num++) 
				{
					
					if (map_memlist.get(tag).instance_controllerlist.get(i_h_num).getController_id().equals(temp_request.getContent_id())) 
					{
						
						String temp_ins=map_memlist.get(tag).instance_controllerlist.get(i_h_num).getInstance_id();
						
						// get the count of compute node 
						int compute_number=AppProperties.getVnet_compute_num();
						/*******************************************************************************************************************
						/*   According to a dedicated load balance strategy, get the compute_zone_id where the controller should be
						/*   Current test strategy: randomly get a integer within the scope of compute node num, and create controller on it
						********************************************************************************************************************/
						Random ran=new Random();
						int compute_zone_id=ran.nextInt(compute_number)+1;
						
						
						//conduct the vm creation, return value is the stack_id of the, now we only save stack_id into database 
						//other stack's info still don't be taken care of so far
						int stack_id=createInstance(temp_ins, "controller",compute_zone_id,tag);
						
						//stack into map
						stackid_map.put(stack_id, 1);
						
						//count+1
						count_instance_creating++;
						
						Vnet_stack_instance temp_s_i=new Vnet_stack_instance(stack_id,temp_ins);
						
						// create stack_instance item and insert it into database
						stack_instancedao.insertStack_instance(temp_s_i);
						
						// jump outside
						break;
					}
				}
				
			}
			
			if(temp_request.getRequest_type().equals("8"))
			// destroy single controller
			{
				deleteInstance(temp_request.getContent_id(),"controller");
			}
			
			if(temp_request.getRequest_type().equals("9"))
			// create single link
			{
				
				for(int k=0;k<map_memlist.get(tag).linklist.size();k++)
				{
					if(temp_request.getContent_id().equals(map_memlist.get(tag).linklist.get(k).getLink_id()))
					{
						int mark=0;
						Vnet_link v_link=map_memlist.get(tag).linklist.get(k);
						String id_from="";
						String id_to="";
						String name_from="";
						String name_to="";
						String type_from="";
						String type_to="";
						
						if(v_link.getLink_src_type().equals("Host"))
						{
							id_from=v_link.getLink_dst_id();
							id_to=v_link.getLink_src_id();
							type_from=v_link.getLink_dst_type();
							type_to=v_link.getLink_src_type();
							mark=2;
							// mark is signal, 2: from is src
						}
						else
						{
							id_from=v_link.getLink_src_id();
							id_to=v_link.getLink_dst_id();
							type_to=v_link.getLink_dst_type();
							type_from=v_link.getLink_src_type();
							mark=1;
							// mark is signal, 1: from is dst
							
						}
						
						if(type_to.equals("Host"))
						{
							//if switch's opposite end is host
							name_from= switchdao.getById(id_from).getSwitch_name();
							
//							String thisInstanceId=instance_hostdao.getInstance_id(id_to);
//							String thisOsportId=instance_osportdao.getOsport_id(thisInstanceId);
//							String thisOsportId=instance_osportdao.getOsport_id_by_hostid(id_to);
//							Vnet_osport thisOsport=osportdao.getById(thisOsportId);
//							String thisOsportOsid=thisOsport.getOsport_osid();
							
							//find out host's osportid by host id
							Vnet_osport thisOsport=osportdao.getbyHostid(id_to);
							
							//below is divided into two cases
							//1: two end is located in the same compute node; 2: two end is located in the different compute nodes
 							
							int from_compute_id=compute_switchdao.getBySwitchId(id_from).getCompute_id();
							int to_compute_id=compute_hostdao.getByHostId(id_to).getCompute_id();
							
							if(from_compute_id==to_compute_id)
							{
								
								String compute_url=computedao.getById(from_compute_id).getCompute_addr();
								ovsserviceimpl.set_vmlink(compute_url, name_from, thisOsport.getOsport_osid());
							}
							else
							{
								

								
								//get a available tun
								Vnet_tun temp_tun=tundao.getRandom("unused");
								int temp_tun_id=temp_tun.getTun_id();
								String temp_tun_tag=temp_tun.getTun_tag();
								
								//update tun table
								temp_tun.setTun_status("used");
								tundao.updateTun(temp_tun);
								
								// (1) at the first compute node, connect switch with related vnet-br
								String from_compute_url=computedao.getById(from_compute_id).getCompute_addr();
								String vnet_br=AppProperties.getVnet_br();
								
								ovsserviceimpl.set_switchlink(from_compute_url,name_from, vnet_br, temp_tun_id);
								
								// (2) at the first compute node, connect vnet-br with related switch
								ovsserviceimpl.set_switchlink(from_compute_url, vnet_br, name_from, temp_tun_id);
								
//								//get current available max index, get available tag
//								String temp_tunofport_id=compute_tunofportdao.getByComputeId(from_compute_id).getTunofport_id();
//								Vnet_tunofport temp_tunofport = tunofportdao.getById(temp_tunofport_id);
//								int portNum=temp_tunofport.getTunofport_portnum()+1;
//								temp_tunofport.setTunofport_portnum(portNum);
//								// update tunofport table, update current available max port
//								tunofportdao.updatePortNum(temp_tunofport);
								
								String port_name=vnet_br+name_from+temp_tun_id;
								String ofport_result=ovsserviceimpl.get_portnumber(from_compute_url,port_name);
								JSONObject json1=JSONObject.fromObject(ofport_result);
								JSONObject json2=json1.getJSONObject("result");
								String portNumstr=json2.getString("output");
								int portNum=Integer.parseInt(portNumstr);
								
								// install flow entries by related port
								// vnet-br default use 1-ofport to connect with external network
								String vnet_br_out_port="1";
								String res=ovsserviceimpl.set_flow(from_compute_url, temp_tun_tag, Integer.toString(portNum), vnet_br_out_port);
								
								// (3) at the second compute node, connect vnet-br with related host
								String to_compute_url=computedao.getById(to_compute_id).getCompute_addr();
								
								
								ovsserviceimpl.set_vmlink(to_compute_url, vnet_br, thisOsport.getOsport_osid());
								
//								//get tunofport related port number at the second compute node
//								String temp_tunofport_id2=compute_tunofportdao.getByComputeId(to_compute_id).getTunofport_id();
//								Vnet_tunofport temp_tunofport2=tunofportdao.getById(temp_tunofport_id2);
//								int portNum2=temp_tunofport2.getTunofport_portnum()+1;
//								temp_tunofport2.setTunofport_portnum(portNum2);
//								//update tunofport table, return current available max port back into database
//								tunofportdao.updatePortNum(temp_tunofport2);
								
								String port_name2="qvo" + thisOsport.getOsport_osid().substring(0,11);
								String ofport_result2=ovsserviceimpl.get_portnumber(to_compute_url,port_name2);
								JSONObject json3=JSONObject.fromObject(ofport_result2);
								JSONObject json4=json3.getJSONObject("result");
								String portNumstr2=json4.getString("output");
								int portNum2=Integer.parseInt(portNumstr2);
								
								//install flow entries by related port
								//similarly, vnet-br use 1-ofport to commnunicate with external network
								String vnet_br_out="1";
								String res2=ovsserviceimpl.set_flow(to_compute_url, temp_tun_tag, Integer.toString(portNum2), vnet_br_out);
								
								// update link table's tun_id, link_tunofport_src, link_tunofport_dst
								
								// mark is signal, 1: if src is from; 2: if src is to
								v_link.setTun_id(temp_tun_id);
								v_link.setLink_iscross(1);
								v_link.setLink_status("ACTIVE");
								if(mark==1)
								{
									v_link.setLink_tunofport_src(portNum);
									v_link.setLink_tunofport_dst(portNum2);
								}
								else if(mark==2)
								{
									v_link.setLink_tunofport_src(portNum2);
									v_link.setLink_tunofport_dst(portNum);
								}
								linkdao.updateLink(v_link);
								
							}
							//update osport database table
							thisOsport.setOsport_status("ACTIVE");
							osportdao.updateOsport(thisOsport);
							
						}
						else 
						{
							
							name_from=switchdao.getById(id_from).getSwitch_name();
							name_to=switchdao.getById(id_to).getSwitch_name();
							int from_compute_id=compute_switchdao.getBySwitchId(id_from).getCompute_id();
							int to_compute_id=compute_switchdao.getBySwitchId(id_to).getCompute_id();
							
							if(from_compute_id==to_compute_id)
							{
								
								String compute_url=computedao.getById(from_compute_id).getCompute_addr();
								ovsserviceimpl.set_switchlink(compute_url, name_from, name_to,0);	
								ovsserviceimpl.set_switchlink(compute_url, name_to, name_from, 0);
							}
							
							else
							{
								//in the different compute nodes
								//first get a available tun_id
								int temp_tun_id;
								String temp_tun_tag;
								
								Vnet_tun temp_tun=tundao.getRandom("unused");
								//tun_tag
								temp_tun_tag=temp_tun.getTun_tag();
								temp_tun_id=temp_tun.getTun_id();
								System.out.println("temp_tun_id=" + temp_tun_id + "|||||||||||||||||||||||||||||||||..");
								//update tun database table 
								temp_tun.setTun_status("used");
								tundao.updateTun(temp_tun);
								//tun_id 

								
								// (1) at the first compute node, connect switch into related vnet-br

								String from_compute_url=computedao.getById(from_compute_id).getCompute_addr();
								String vnet_br=AppProperties.getVnet_br();
								ovsserviceimpl.set_switchlink(from_compute_url, name_from, vnet_br, temp_tun_id);
								
								// (2) at the first compute node, connect vnet-br with related switch
								
								ovsserviceimpl.set_switchlink(from_compute_url, vnet_br, name_from, temp_tun_id);
								
//								// get the port number of vnet-br set up at the first compute node
//								String temp_tunofport_id=compute_tunofportdao.getByComputeId(from_compute_id).getTunofport_id();
//								Vnet_tunofport temp_tunofport=tunofportdao.getById(temp_tunofport_id);
//								int portNum=temp_tunofport.getTunofport_portnum()+1;
//								temp_tunofport.setTunofport_portnum(portNum);
//								//update tunofport database table 
//								tunofportdao.updatePortNum(temp_tunofport);
								
								String port_name=vnet_br+name_from+temp_tun_id;
								String ofport_result=ovsserviceimpl.get_portnumber(from_compute_url,port_name);
								JSONObject json1=JSONObject.fromObject(ofport_result);
								JSONObject json2=json1.getJSONObject("result");
								String portNumstr=json2.getString("output");
								int portNum=Integer.parseInt(portNumstr);
								
								// install flow entries
								String vnet_br_out_port="1";
								String res=ovsserviceimpl.set_flow(from_compute_url, temp_tun_tag, Integer.toString(portNum), vnet_br_out_port);

								
								// (3) at the second compute node, connect switch with related vnet-br
								String to_compute_url=computedao.getById(to_compute_id).getCompute_addr();

								ovsserviceimpl.set_switchlink(to_compute_url, name_to, vnet_br, temp_tun_id);
								
								// (4) at the second compute node, connect vnet-br with related switch
								ovsserviceimpl.set_switchlink(to_compute_url, vnet_br, name_to, temp_tun_id);
								
//							    // get the port number of vnet-br set up at the second compute node
//								String temp_tunofport_id2=compute_tunofportdao.getByComputeId(to_compute_id).getTunofport_id();
//								Vnet_tunofport temp_tunofport2=tunofportdao.getById(temp_tunofport_id2);
//								int portNum2=temp_tunofport2.getTunofport_portnum()+1;
//								temp_tunofport2.setTunofport_portnum(portNum2);
//								//update tunofport database table
//								tunofportdao.updatePortNum(temp_tunofport2);
								
								String port_name2=vnet_br+name_to+temp_tun_id;
								String ofport_result2=ovsserviceimpl.get_portnumber(to_compute_url,port_name2);
								JSONObject json3=JSONObject.fromObject(ofport_result2);
								JSONObject json4=json1.getJSONObject("result");
								String portNumstr2=json4.getString("output");
								int portNum2=Integer.parseInt(portNumstr2);
								
								//install flow entries
								String res2=ovsserviceimpl.set_flow(to_compute_url, temp_tun_tag, Integer.toString(portNum2), vnet_br_out_port);
								
								//update link database table
								v_link.setTun_id(temp_tun_id);
								v_link.setLink_iscross(1);
								v_link.setLink_status("ACTIVE");

								v_link.setLink_tunofport_src(portNum);
								v_link.setLink_tunofport_dst(portNum2);

								linkdao.updateLink(v_link);
							}
							
						}
						
						//set link's state as ACTIVE, and return back into database
						v_link.setLink_status("ACTIVE");
						linkdao.updateLink(v_link);
						break;
					}
				}

			}
			
			if(temp_request.getRequest_type().equals("10"))
			// destory single link
			{
				deleteLink(temp_request.getContent_id());
				
			}
			
			if(temp_request.getRequest_type().equals("11"))
			// create single oflink
			{
				for(int kk=0;kk<map_memlist.get(tag).oflinklist.size();kk++)
				{
					if(map_memlist.get(tag).oflinklist.get(kk).getOflink_id().equals(temp_request.getContent_id()))
					{
						
						Vnet_oflink temp_oflink=map_memlist.get(tag).oflinklist.get(kk);
						
						String controller_id=temp_oflink.getController_id();
						String switch_id=temp_oflink.getSwitch_id();
						createOflink(controller_id,switch_id);
						
						// update oflink back into database
						temp_oflink.setOflink_status("ACTIVE");
						oflinkdao.updateOflink(temp_oflink);
						
						break;
						
					}
				}
				
			}
			if(temp_request.getRequest_type().equals("12"))
			// destroy single oflink 
			{
				deleteOflink(temp_request.getContent_id());
				
			}
			
			System.out.println("current request has been conducted, set request's state as finished");
			System.out.println("request's id is: " + temp_request.getRequest_id());
			//At last, set the state as finished 
			
			temp_request.setRequest_status("finished");
			requestdao.updateRequest(temp_request);
			
		}
		
		System.out.println("all operation have been done!!!!!!!!!!!!!!!!!!!!!!!!!");
	
		// if vnet's state is not DELETED, set it as ACTIVE
		Vnet_vnet tt_vnet=vnetdao.getById(tag);
		if(!tt_vnet.getVnet_status().equals("DELETED"))
		{
			tt_vnet.setVnet_status("ACTIVE");
			vnetdao.updateVnet(tt_vnet);
		}
		
		return return_list_reqHandle;
		
		
		
	}
	
	@Override
	public int ofportFlexToJava(ArrayCollection array,String tag)
	{
		
		Vnet_ofport myobj;
		map_memlist.get(tag).ofportlist.clear();
		for(int i=0;i<array.size();i++)
		{
			myobj=new Vnet_ofport();
			myobj=(Vnet_ofport)array.get(i);
			map_memlist.get(tag).ofportlist.add(myobj);
			System.out.println(myobj.toString());
		}	
		
		//update database
		List<Vnet_ofport> ofports = vnet_ofportdao.getAllByVnet(tag);	  //get mysql database
	    
	    int[] add = new int[map_memlist.get(tag).ofportlist.size()]; 
	    int[] del = new int[ofports.size()]; 
	    
	    List<Vnet_ofport> addops = new ArrayList<Vnet_ofport>();
	    List<Vnet_ofport> delops = new ArrayList<Vnet_ofport>();

	    for (int i = 0; i < map_memlist.get(tag).ofportlist.size(); i++) { 
			for (int j = 0; j < ofports.size(); j++) {
				if (map_memlist.get(tag).ofportlist.get(i).getOfport_id().equals(ofports.get(j).getOfport_id())) {
					add[i]++;
					del[j]++;
				}
			}
		}
	    
	    for (int i = 0; i < add.length; i++) {
			if (add[i] == 0) {
				addops.add(map_memlist.get(tag).ofportlist.get(i));
			}
		}
	    
	    for (int i = 0; i < del.length; i++) {
			if (del[i] == 0) {
				delops.add(ofports.get(i));
			}
	    }
	    
	    
        for(int i=0;i < addops.size();i++)
	    {
	    	if (addops.get(i) != null) {
		    	this.vnet_ofportdao.insertOfport(addops.get(i));
			}
	    }
	    
        for(int i=0;i < delops.size();i++)
	    {
        	if (delops.get(i) != null) {
        		this.vnet_ofportdao.delById(delops.get(i).getOfport_id());
			}
	    }
        
		return 0;
	}
	
	@Override
	public int controllerFlexToJava(ArrayCollection array,String tag)
	{
		map_memlist.get(tag).controllerlist.clear();
		Vnet_controller myobj;
		for(int i=0;i<array.size();i++)
		{
			myobj=new Vnet_controller();
			myobj=(Vnet_controller)array.get(i);
			map_memlist.get(tag).controllerlist.add(myobj);
  			int res = controllerdao.insertController(myobj);
			//System.out.println(myobj.toString());

		}	
		return 0;
	}
	
	@Override
	public int switchFlexToJava(ArrayCollection array,String tag)
	{
		map_memlist.get(tag).switchlist.clear();
		Vnet_switch myobj;
		for(int i=0;i<array.size();i++)
		{
			myobj=new Vnet_switch();
			myobj=(Vnet_switch)array.get(i);
			map_memlist.get(tag).switchlist.add(myobj);
  			int res = switchdao.insertSwitch(myobj);
			//System.out.println(myobj.toString());

		}	
		return 0;
	}
	@Override
	public int hostFlexToJava(ArrayCollection array,String tag)
	{
		map_memlist.get(tag).hostlist.clear();
		Vnet_host myobj;
		for(int i=0;i<array.size();i++)
		{
			myobj=new Vnet_host();
			myobj=(Vnet_host)array.get(i);
			map_memlist.get(tag).hostlist.add(myobj);
  			int res = hostdao.insertHost(myobj);
			//System.out.println(myobj.toString());

		}	
		return 0;
	}
	@Override
	public int oflinkFlexToJava(ArrayCollection array,String tag)
	{
		map_memlist.get(tag).oflinklist.clear();
		Vnet_oflink myobj;
		for(int i=0;i<array.size();i++)
		{
			myobj=new Vnet_oflink();
			myobj=(Vnet_oflink)array.get(i);
			map_memlist.get(tag).oflinklist.add(myobj);
  			int res = oflinkdao.insertOflink(myobj);
			//System.out.println(myobj.toString());

		}	
		return 0;
	}
	@Override
	public int linkFlexToJava(ArrayCollection array,String tag)
	{
		map_memlist.get(tag).linklist.clear();
		Vnet_link myobj;
		for(int i=0;i<array.size();i++)
		{
			myobj=new Vnet_link();
			myobj=(Vnet_link)array.get(i);
			map_memlist.get(tag).linklist.add(myobj);
  			int res = linkdao.insertLink(myobj);
			//System.out.println(myobj.toString());

		}	
		return 0;
	}
	@Override
	public int switch_ofportsFlexToJava(ArrayCollection array,String tag) {
		Vnet_switch_ofport myobj;
		map_memlist.get(tag).switch_ofportlist.clear();
		for(int i=0;i<array.size();i++)
		{
			myobj=new Vnet_switch_ofport();
			myobj=(Vnet_switch_ofport)array.get(i);
			map_memlist.get(tag).switch_ofportlist.add(myobj);
		}
		
		//update database
		List<Vnet_switch_ofport> sqls = switch_ofportdao.getAllByVnet(tag);	  //get mysql database
		
	    int[] add = new int[map_memlist.get(tag).switch_ofportlist.size()]; 
	    int[] del = new int[sqls.size()]; 
	    
	    List<Vnet_switch_ofport> adds = new ArrayList<Vnet_switch_ofport>();
	    List<Vnet_switch_ofport> dels = new ArrayList<Vnet_switch_ofport>();

	    for (int i = 0; i < map_memlist.get(tag).switch_ofportlist.size(); i++) { 
			for (int j = 0; j < sqls.size(); j++) {
				if (map_memlist.get(tag).switch_ofportlist.get(i).getOfport_id().equals(sqls.get(j).getOfport_id())) {
					add[i]++;
					del[j]++;
					break;
				}
			}
		}
	    
	    for (int i = 0; i < add.length; i++) {
			if (add[i] == 0) {
				adds.add(map_memlist.get(tag).switch_ofportlist.get(i));
			}
		}
	    
	    for (int i = 0; i < del.length; i++) {
			if (del[i] == 0) {
				dels.add(sqls.get(i));
			}
		}
	    
        for(int i=0;i < adds.size();i++)
	    {
	    	if (adds.get(i) != null) {
		    	this.switch_ofportdao.insertSwitch_ofport(adds.get(i));
			}
	    }
	    
        for(int i=0;i < dels.size();i++)
	    {
        	if (dels.get(i) != null) {
        		this.switch_ofportdao.delById(dels.get(i).getSwitch_ofport_id());
			}
	    }
		return 0;
	}
	
	@Override
	public int vnetFlexToJava(Vnet_vnet vnet,String tag) {
		
	
		if(vnet != null){
			map_memlist.get(tag).myvnet = vnet;
  			int res = vnetdao.insertVnet(vnet);
			//System.out.println(vnet.toString());
		}
		return 0;
	}
	
	@Override
	public int executor_vnetFlexToJava(Vnet_executor_vnet e_v,String tag) {
		
		if(e_v != null){
			map_memlist.get(tag).executor_vnet = e_v;
  			int res = executor_vnetdao.insertExecutor_vnet(e_v);
			//System.out.println(e_v.toString());
		}
		return 0;
	}
	
	@Override
	public int vnet_resourceFlexToJava(ArrayCollection array,String tag) {
		Vnet_vnet_resource myobj;
		map_memlist.get(tag).vnet_resourcelist.clear();
		for(int i=0;i<array.size();i++)
		{
			myobj=(Vnet_vnet_resource)array.get(i);
			map_memlist.get(tag).vnet_resourcelist.add(myobj);
  			int res = vnet_resourcedao.insertVnetResource(myobj);
			//System.out.println(myobj.toString());
		}
		
		return 0;
	}
	
	@Override
	public int requestFlexToJava(ArrayCollection requests,String tag){
		Vnet_request myobj;
//		List<Vnet_ofport> ofports = vnet_ofportdao.getAll();
//		List<Vnet_switch_ofport> swi_ofports = switch_ofportdao.getAll();
		map_memlist.get(tag).requestlist.clear();
		for(int i=0;i<requests.size();i++)
		{
			myobj=(Vnet_request)requests.get(i);
  			int res=requestdao.insertRequest(myobj);
  			myobj.setRequest_id(res);
			map_memlist.get(tag).requestlist.add(myobj);
			//System.out.println(myobj.toString());
		}
		
		return 0;
	}
	
	@Override	
	public int instanceFlexToJava(ArrayCollection instances ,String tag)
	{
		map_memlist.get(tag).instancelist.clear();
		Vnet_instance myobj;
		for(int i=0;i<instances.size();i++)
		{
			myobj=(Vnet_instance)instances.get(i);
			map_memlist.get(tag).instancelist.add(myobj);
  			int res=instancedao.insertInstance(myobj);
			//System.out.println(myobj.toString());
		}
		return 0;
	}
	
	@Override	
	public int flavor_instanceFlexToJava(ArrayCollection flavor_instance ,String tag){
		map_memlist.get(tag).flavor_instancelist.clear();
		Vnet_flavor_instance myobj;
		for(int i=0;i<flavor_instance.size();i++)
		{
			myobj=(Vnet_flavor_instance)flavor_instance.get(i);
			map_memlist.get(tag).flavor_instancelist.add(myobj);
  			int res=flavor_instancedao.insertFlavorInstance(myobj);
			//System.out.println(myobj.toString());
		}
		return 0;
	}
	
	@Override	
	public int image_instanceFlexToJava(ArrayCollection image_instance ,String tag)
	{
		map_memlist.get(tag).image_instancelist.clear();
		Vnet_image_instance myobj;
		for(int i=0;i<image_instance.size();i++)
		{
			myobj=(Vnet_image_instance)image_instance.get(i);
			map_memlist.get(tag).image_instancelist.add(myobj);
  			int res=image_instancedao.insertImageInstance(myobj);
			//System.out.println(myobj.toString());
		}
		return 0;
	}
	
	@Override	
	public int instance_controllerFlexToJava(ArrayCollection instance_controller,String tag)
	{
		map_memlist.get(tag).controllerlist.clear();
		Vnet_instance_controller myobj;
		for(int i=0;i<instance_controller.size();i++)
		{
			myobj=(Vnet_instance_controller)instance_controller.get(i);
			map_memlist.get(tag).instance_controllerlist.add(myobj);
  			int res=instance_controllerdao.insertInstance_controller(myobj);
			//System.out.println(myobj.toString());
		}
		return 0;
	}
	
	
	@Override	
	public int instance_osportFlexToJava(ArrayCollection instance_osport,String tag)
	{
		map_memlist.get(tag).instance_osportlist.clear();
		Vnet_instance_osport myobj;
		for(int i=0;i<instance_osport.size();i++)
		{
			myobj=(Vnet_instance_osport)instance_osport.get(i);
			map_memlist.get(tag).instance_osportlist.add(myobj);
  			int res=instance_osportdao.insertInstanceOsport(myobj);
			//System.out.println(myobj.toString());
		}
		return 0;
	}
	
	@Override	
	public int osportFlexToJava(ArrayCollection osports,String tag)
	{
		map_memlist.get(tag).osportlist.clear();
		Vnet_osport myobj;
		for(int i=0;i<osports.size();i++)
		{
			myobj=(Vnet_osport)osports.get(i);
			map_memlist.get(tag).osportlist.add(myobj);
  			int res=osportdao.insertOsport(myobj);
			//System.out.println(myobj.toString());
		}
		return 0;
	}
	
	@Override	
	public void establish()
	{
		
	}




	@Override
	public int instance_hostFlexToJava(ArrayCollection instance_host,String tag) {
		// TODO Auto-generated method stub
		map_memlist.get(tag).instance_hostlist.clear();
		Vnet_instance_host myobj;
		for(int i=0;i<instance_host.size();i++)
		{
			myobj=(Vnet_instance_host)instance_host.get(i);
			map_memlist.get(tag).instance_hostlist.add(myobj);
  			int res=instance_hostdao.insertInstance_host(myobj);
			//System.out.println(myobj.toString());
		}
		
		return 0;
		
	}
	
}
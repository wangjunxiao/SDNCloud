package cn.dlut.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_instanceDao;
import cn.dlut.dao.Vnet_instance_osportDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_osportDao;
import cn.dlut.dao.Vnet_stackDao;
import cn.dlut.dao.Vnet_stack_instanceDao;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.entity.Vnet_instance_osport;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_osport;
import cn.dlut.entity.Vnet_stack;
import cn.dlut.entity.Vnet_stack_instance;
import cn.dlut.service.impl.Vnet_OpsIdentityServiceImpl;
import cn.dlut.service.Vnet_OpsHeatService;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;

import flex.messaging.services.messaging.ThrottleManager.ThrottleResult.Result;

@Service("Vnet_OpsHeatService")
@SuppressWarnings("all")

public class Vnet_OpsHeatServiceImpl extends AbstractBaseService implements Vnet_OpsHeatService {
	
	@Autowired
	private Vnet_OpsIdentityService ide;
	
	@Autowired
	private Vnet_floatingipDao floatingipdao;
	
	@Autowired
	private Vnet_instanceDao instancedao;
	
	@Autowired
	private Vnet_ipDao ipdao;
	
	@Autowired
	private Vnet_osportDao osportdao;
	
	@Autowired 
	Vnet_instance_osportDao instance_osportdao;
	
	@Autowired 
	private Vnet_stackDao stackdao;
	
	@Autowired
	private Vnet_stack_instanceDao stack_instancedao;
	
	
	@Override
	public int createInstance(String instance_type,String temp_ins_id,String flavor_id,String image_id,String network_id,String subnet_id,String floatingip_osid,String compute_zone_name)
	{
//		String POST_URL="http://192.168.0.87:8004/v1/2379e521097a4f7986f8f7dde862d922/stacks";
		String POST_URL="http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
						+AppProperties.getVnet_tenant_demo()+"/stacks";
		String content="";
		
		Date dt=new Date();
		//DateFormat df=new SimpleDateFormat("HHmmss");
		
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		
		String nowTime="";
		nowTime=df.format(dt);
		

		
		JSONObject json1=new JSONObject();
		JSONObject json2=new JSONObject();
		JSONObject json3=new JSONObject();
		JSONObject json4=new JSONObject();
		JSONObject json5=new JSONObject();
		JSONArray jsonarray1=new JSONArray();
		JSONObject json6=new JSONObject();
		JSONObject json7=new JSONObject();
		JSONObject json8=new JSONObject();
		JSONObject json9=new JSONObject();
		JSONObject json10=new JSONObject();
		JSONArray jsonarray2=new JSONArray();
		JSONObject json11=new JSONObject();
		JSONObject json12=new JSONObject();
		JSONObject json13=new JSONObject();
		JSONObject json14=new JSONObject();
		JSONObject json15=new JSONObject();
		JSONObject json16=new JSONObject();
		JSONObject json17=new JSONObject();
		JSONObject json18=new JSONObject();
		JSONObject json19=new JSONObject();
		JSONObject json20=new JSONObject();
		
		JSONObject json21=new JSONObject();
		JSONObject json22=new JSONObject();
		JSONObject json23=new JSONObject();
		JSONObject json60=new JSONObject();
		JSONObject json61=new JSONObject();
		
		List<String> array3=new ArrayList<String>();
		List<String> array4=new ArrayList<String>();
		List<String> array5=new ArrayList<String>();
		List<String> array6=new ArrayList<String>();
		
		//JSONArray jsonarray3=new JSONArray();
		
		//private_ip
		array5.add("vm1");
		array5.add("first_address");
		json16.put("get_attr", array5);
		json15.put("value", json16);
		
		json18.put("get_resource", "vm1");
		json17.put("value", json18);
		
		json20.put("get_resource", "port1");
		json19.put("value", json20);
		

		
		json14.put("instance_osport_osid", json19);
		json14.put("instance_osid", json17);
		json14.put("instance_addr", json15);
		
		json13.put("get_resource", "port1");
		

		if(!floatingip_osid.equals(""))
		{
			if(instance_type.equals("host"))
			{
				// host is given floatingip
				// add the second NIC's info into outputs 
				json21.put("get_resource", "port2");
				json22.put("value",json21);
				array6.add("vm1");
				array6.add("networks");
				array6.add("host-iface-net");
				json60.put("get_attr", array6);
				json61.put("value", json60);
				json14.put("instance_osport_osid2", json22);
				json14.put("instance_ip2", json61);
				
				json12.put("floatingip_id", floatingip_osid);
				
				JSONObject json50=new JSONObject();
				json50.put("get_resource", "port2");
				
				json12.put("port_id", json50);
				
				json11.put("type","OS::Neutron::FloatingIPAssociation");
				json11.put("properties", json12);
				
				array3.add("vm1");
				json11.put("depends_on", array3);
				json3.put("floating_ip_ass", json11);
				
			}
				
			else
			{
				//controller is given floatingip
				json12.put("floatingip_id", floatingip_osid);
				json12.put("port_id", json13);
				
				json11.put("type","OS::Neutron::FloatingIPAssociation");
				json11.put("properties", json12);
				
				array3.add("vm1");
				json11.put("depends_on", array3);
				json3.put("floating_ip_ass", json11);
			}
			
		}
		
		json2.put("outputs", json14);

		
		//default as single port, name as port1
		json7.put("get_resource", "port1") ;  
		json6.put("port",json7);
		
		if(instance_type.equals("host")&&!floatingip_osid.equals(""))
		{
			// add the second NIC's info into resources  
			
			String temp_network_osid=AppProperties.getVnet_controller_network_id();
			String temp_subnet_osid=AppProperties.getVnet_controller_subnet_id();
			
			JSONObject json30=new JSONObject();
			json30.put("network_id", temp_network_osid);
			JSONObject json31=new JSONObject();
			json31.put("subnet_id", temp_subnet_osid);
			JSONArray array32=new JSONArray();
			array32.add(json31);
			json30.put("fixed_ips", array32);
			
			JSONObject json34=new JSONObject(); 
			json34.put("properties", json30);
			json34.put("type", "OS::Neutron::Port");
			
			json3.put("port2",json34);
			
			JSONObject json35=new JSONObject();
			json35.put("get_resource", "port2");
			
			JSONObject json36=new JSONObject();
			json36.put("port", json35);
			
			jsonarray1.add(json36);
			
			array4.add("port2");
			
		}
		
		jsonarray1.add(json6);
		
		
		json5.put("networks",jsonarray1);
		json5.put("image",image_id);
		json5.put("flavor", flavor_id);
		json5.put("availability_zone", compute_zone_name);
		array4.add("port1");
		
		json4.put("properties", json5);
		json4.put("depends_on", array4);
		json4.put("type", "OS::Nova::Server");
		json3.put("vm1", json4);
		
		
		// add port info
		// security group info isn't added
		json10.put("subnet_id",subnet_id);
		jsonarray2.add(json10);
		json9.put("fixed_ips",jsonarray2);
		json9.put("network_id", network_id);
		
		json8.put("type","OS::Neutron::Port");
		json8.put("properties", json9);	
		
		json3.put("port1", json8);

		
		//Outermost package
		
		json2.put("resources", json3);
		json2.put("heat_template_version", "2013-05-23");
		
		
		
		//Outermost stack_name as the unique id of stack, must be matchless
		//stack insertion
		Vnet_stack temp_stack=new Vnet_stack();
		//insert the first created stack into database
		int temp_stack_id=stackdao.insertAndGetId(temp_stack);
		
		//use stack + database's increment primary key to construct stack_name, and send into openstack
		String stack_name="stack" + temp_stack_id;
		json1.put("stack_name",stack_name);
		json1.put("template", json2);
		
		content=json1.toString();
		
		//return content;

		String result="";
		
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE()); 
		
		System.out.println("####### http request for instance ###################");
		System.out.println(content);
		
		System.out.println(content);
		
		 try {
			 	result = Vnet_HttpRequest.readContentFromPost(POST_URL, content, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
	     } catch (IOException e) {
		    	// TODO Auto-generated catch block
	    	 	e.printStackTrace();
		 }
	     
	     
	  
	    
	     if(!floatingip_osid.equals(""))
	     { 
	    	 //if floating is not "", update floatingip
	    	 //update floatingip's state as ACTIVE
//		     Vnet_floatingip t_floatingip=new Vnet_floatingip("",floatingip_osid,"ACTIVE");	     
//		     floatingipdao.updateFloatingipByOsid(t_floatingip);
	    	 Vnet_floatingip t_floatingip=floatingipdao.getByOsid(floatingip_osid);
	    	 t_floatingip.setFloatingip_status("ACTIVE");
	    	 floatingipdao.updateFloatingipByOsid(t_floatingip);
	    	 
	     }
	     return temp_stack_id;
	}
	
	@Override
	public void CreateStack() {
		// TODO Auto-generated method stub
		String POST_URL="http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
						+AppProperties.getVnet_tenant_demo()+"/stacks";
		 String content = "{\"stack_name\":\"teststack3\","				 
		 		+ "\"template\":{\"heat_template_version\":\"2013-05-23\","				 
		 		+ "\"resources\":{\"vm1\":{\"type\":\"OS::Nova::Server\","		 		
		 		+ "\"properties\":{\"flavor\":\"7\","		 		
		 		+ "\"image\":\"d0805879-2713-4658-b93b-f4d9f9496b32\"}}}}}";
		 String result = "";
		 ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());  //make sure token is correct
		 
		 try {
			 	result = Vnet_HttpRequest.readContentFromPost(POST_URL, content, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
	     } catch (IOException e) {
		    	// TODO Auto-generated catch block
	    	 	e.printStackTrace();
		 }
	}

	@Override
	public String ListStack() {
		// TODO Auto-generated method stub
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
							+AppProperties.getVnet_tenant_demo()+"/stacks";				
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(result.length()==0){
				result = e.toString();
			}
		}
		return result;
	}

	
	@Override
	public String ShowStack(String stackName, String stackID) {
		// TODO Auto-generated method stub
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
							+AppProperties.getVnet_tenant_demo()+"/stacks/" + stackName + "/" + stackID;
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
//			 	result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}
	

	
	@Override
	public String ShowStack(String stackName) {
		// TODO Auto-generated method stub
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
							+AppProperties.getVnet_tenant_demo()+"/stacks/" + stackName;
		String result="";
		
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
//			 	result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
		
	}

//	@Override
//	public void UpdateStack(String stackName, String stackID) {
//		// TODO Auto-generated method stub
//		String PUT_URL = "http://192.168.0.86:8004/v1/2379e521097a4f7986f8f7dde862d922/stacks/" + stackName + "/" + stackID;
//		String content = "{\"template\": {\"heat_template_version\": \"2013-05-23\",\"resources\": {\"vm1\": {\"type\": \"OS::Nova::Server\",\"properties\": {"
//				+ "\"flavor\": \"m1.small\",\"image\": \"ubuntu-amd64\"}}}}}";
//		String result = "";
//		System.out.println(content);
//		
//		try {
//			result = HttpRequest.readContentFromPut(PUT_URL, "X-Auth-Token", "b0470b1e0bef4b089673f98e26276cf7");
//			System.out.println(result);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}

	@Override
	public void DeleteStack(String stackName, String stackID) {
		// TODO Auto-generated method stub
		String DELETE_URL = "http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
								+AppProperties.getVnet_tenant_demo()+"/stacks/" + stackName + "/" + stackID;
		Boolean result = false;
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		
		try {
			result = Vnet_HttpRequest.readContentFromDelete(DELETE_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			if (result) {
				System.out.println("\"" + stackName + "/" + stackID + "\" is deleted successfully!");
			}
			else {
				System.out.println("Your operator is failed!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	


	@Override
	public void SuspendStack(String stackName, String stackID) {
		// TODO Auto-generated method stub
		String 	POST_URL = "http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
								+AppProperties.getVnet_tenant_demo()+"/stacks/" + stackName + "/" + stackID + "/actions";
		String content = "{\"suspend\": null}";
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		
		try {
			result = Vnet_HttpRequest.readContentFromPost(POST_URL, content, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			if (result.equals("null")) {
				System.out.println("\"" + stackName + "/" + stackID + "\" is suspended successfully!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void ResumeStack(String stackName, String stackID) {
		// TODO Auto-generated method stub
		String 	POST_URL = "http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
								+AppProperties.getVnet_tenant_demo()+"/stacks/" + stackName + "/" + stackID + "/actions";
		String content = "{\"resume\": null}";
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		
		try {
			result = Vnet_HttpRequest.readContentFromPost(POST_URL, content, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			if (result.equals("null")) {
				System.out.println("\"" + stackName + "/" + stackID + "\" is resumed successfully!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

	@Override
	public Boolean deleteInstance(String stackName, String stackID,
			String compute_url) {
		String DELETE_URL = "http://"+AppProperties.getVnet_control_addr()+":8004/v1/"
								+AppProperties.getVnet_tenant_demo()+"/stacks/" + stackName + "/" + stackID;
		Boolean result = false;
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		
		try {
			result = Vnet_HttpRequest.readContentFromDelete(DELETE_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			if (result) {
				System.out.println("\"" + stackName + "/" + stackID + "\" is deleted successfully!");
			}
			else {
				System.out.println("Your operator is failed!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}

//	public static void main(String []args)
//	{
//		
//		Vnet_OpsHeatServiceImpl dd=new Vnet_OpsHeatServiceImpl();
//		dd.createInstance("host","instance_1","1","d0805879-2713-4658-b93b-f4d9f9496b32","df906846-97b8-4428-abe6-67e23cc0246c"
//				,"11aae3da-a997-4060-9c7e-2027ed63a842","b1791fdb-f6eb-4792-b7bb-bf644d0a29f4","zone-compute1");
//		
//	}
	
	
//	public  static void main(String []args)
//	{
//		Vnet_OpsHeatServiceImpl dd=new Vnet_OpsHeatServiceImpl();
//		String myjson=dd.createInstance("2","d0805879-2713-4658-b93b-f4d9f9496b32","b3122ce2-0176-4923-82f2-67df36cc1d2f","edef5052-e252-4b0f-a499-9d4ca0f63b52","be699ba6-a96c-4bba-8ba3-2c8e0bb868cc");
//		System.out.println(myjson);
//	}
	
	
}
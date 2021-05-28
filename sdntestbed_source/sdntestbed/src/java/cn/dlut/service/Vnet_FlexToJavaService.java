package cn.dlut.service;


import java.util.List;
import java.util.Map;

import cn.dlut.entity.Vnet_executor_vnet;
import cn.dlut.entity.Vnet_vnet;
import flex.messaging.io.ArrayCollection;

public interface Vnet_FlexToJavaService {
	
	public List<Map<String, String>> FlexToJava(ArrayCollection ofports,ArrayCollection controllers,ArrayCollection switches,
						  ArrayCollection hosts,ArrayCollection oflinks,ArrayCollection links,
						  ArrayCollection switch_ofports,Vnet_vnet vnet,Vnet_executor_vnet e_v,
						  ArrayCollection vnet_resources,ArrayCollection requests,
						  ArrayCollection instances,ArrayCollection flavor_instance,
						  ArrayCollection image_instance,ArrayCollection instance_controller,
						  ArrayCollection instance_host,ArrayCollection instance_osport,
						  String vnet_id);
	
	public int ofportFlexToJava(ArrayCollection array,String tag);
	
	public int controllerFlexToJava(ArrayCollection array,String tag);	
	
	public int switchFlexToJava(ArrayCollection array,String tag);
	
	public int hostFlexToJava(ArrayCollection array,String tag);
		
	public int oflinkFlexToJava(ArrayCollection array,String tag);
	
	public int linkFlexToJava(ArrayCollection array,String tag);
	
	public int switch_ofportsFlexToJava(ArrayCollection array,String tag);
	
	public int vnetFlexToJava(Vnet_vnet vnet,String tag);
	
	public int executor_vnetFlexToJava(Vnet_executor_vnet e_v,String tag);
	
	public int vnet_resourceFlexToJava(ArrayCollection array,String tag);
	
	public int requestFlexToJava(ArrayCollection requests,String tag);
	
	public int instanceFlexToJava(ArrayCollection instances,String tag);
	
	public int flavor_instanceFlexToJava(ArrayCollection flavor_instance,String tag);
	
	public int image_instanceFlexToJava(ArrayCollection image_instance,String tag);
	
	public int instance_controllerFlexToJava(ArrayCollection instance_controller,String tag);
	
	public int instance_hostFlexToJava(ArrayCollection instance_host,String tag);
	
	public int instance_osportFlexToJava(ArrayCollection instance_osport,String tag);
	
	public int osportFlexToJava(ArrayCollection osports,String tag);
	
	public void establish();
	
	public List<Map<String, String>> requestHandle(String tag) throws InterruptedException;
	
	public int deleteInstance(String content_id,String content_type);

	public int createInstance(String temp_ins, String temp_ins_type,
			Integer compute_zone_id,String tag);

	public int deleteLink(String content_id);

	public int deleteSwitch(String content_id);

	public void deleteOflink(String content_id);
	
	public void createOflink(String controller_id,String switch_id);
	
	
	
	
	
}
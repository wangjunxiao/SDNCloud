package cn.dlut.vnetlab.test.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.service.impl.Vnet_FlexToJavaServiceImpl;
import cn.dlut.service.Vnet_FlexToJavaService;

import cn.dlut.entity.Vnet_host;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.entity.Vnet_instance_host;
import cn.dlut.entity.Vnet_ofport;

public class Vnet_FlexToJavaServiceImplTest extends BaseTest{

	private Vnet_FlexToJavaService ftj;
	        
	
	protected void setUp() throws Exception{
		super.setUp();
		ftj=(Vnet_FlexToJavaService)this.ctx.getBean("Vnet_FlexToJavaService");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown(); 
		ftj=null;
	}
	
	@Test
	public void test001() {
		
		ArrayList<Vnet_host> myhost=new ArrayList<Vnet_host>();
		ArrayList<Vnet_instance> myinstance=new ArrayList<Vnet_instance>();
		ArrayList<Vnet_instance_host> myinstance_host=new ArrayList<Vnet_instance_host>();
//		ArrayList<Vnet_request> myrequest=new ArrayList<Vnet_request>();
		
//		ftj.FlexToJava(null, null, null, myhost, null, null, null, null, null, null, request, instance, flavor_instance, image_instance, instance_controller, instance_host, instance_osport, osports)
		
	
		
		
		/*	this.ipsub.getIpFromOps();
		this.ipsub.putIpIntoDB();
		this.ipsub.getUsedIpFromOps();
		this.ipsub.updateUsedIpIntoDB();*/

	}

}

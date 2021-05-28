package cn.dlut.vnetlab.test.service;


import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.service.Vnet_ShowinstanceService;

public class Vnet_ShowinstanceServiceImplTest extends BaseTest{

	private Vnet_ShowinstanceService showinstanceservice;

	protected void setUp() throws Exception {
		super.setUp();
		showinstanceservice = (Vnet_ShowinstanceService)this.ctx.getBean("Vnet_ShowinstanceService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		showinstanceservice = null;
	}
	
	@Test
	public void testGetAll() {
		List<Vnet_instance> instances = showinstanceservice.queryInstance();
		for(Vnet_instance instance:instances){
			System.out.println(instance.toString());
		}
	}
}

package cn.dlut.vnetlab.test.service;


import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.service.Vnet_ShowflavorService;

public class Vnet_ShowflavorServiceImplTest extends BaseTest {

	private Vnet_ShowflavorService showflavorservice;
	protected void setUp() throws Exception{
		super.setUp();
		showflavorservice=(Vnet_ShowflavorService)this.ctx.getBean("Vnet_ShowflavorService");
		
	}
	protected void tearDown() throws Exception{
		super.tearDown();
		showflavorservice=null;
	}
	@Test
	public void testGetAll() {
		List<Vnet_flavor> flavors=showflavorservice.queryFlavor();
		for(Vnet_flavor flavor:flavors){
			System.out.println(flavor.toString());
		}
	}

}



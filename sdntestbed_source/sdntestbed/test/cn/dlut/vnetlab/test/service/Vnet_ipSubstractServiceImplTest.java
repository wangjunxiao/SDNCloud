package cn.dlut.vnetlab.test.service;


import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.service.Vnet_ipSubtractService;

public class Vnet_ipSubstractServiceImplTest extends BaseTest{

	private Vnet_ipSubtractService ipsub;
	        
	
	protected void setUp() throws Exception{
		super.setUp();
		ipsub=(Vnet_ipSubtractService)this.ctx.getBean("Vnet_ipSubtractService");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown(); 
		ipsub=null;
	}
	
	@Test
	public void test001() {
/*		this.ipsub.getIpFromOps();
		this.ipsub.putIpIntoDB();
		this.ipsub.getUsedIpFromOps();
		this.ipsub.updateUsedIpIntoDB();*/
		
		this.ipsub.getFloatingipFromOps();
		this.ipsub.updateFloatingipIntoDB();
	}

}

package cn.dlut.vnetlab.test.service;


import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.service.Vnet_OpsComputeService;
import cn.dlut.service.Vnet_ipSubtractService;

public class Vnet_OpsComputeServiceImplTest extends BaseTest{

	private Vnet_OpsComputeService ipsub;
	        
	
	protected void setUp() throws Exception{
		super.setUp();
		ipsub=(Vnet_OpsComputeService)this.ctx.getBean("Vnet_OpsComputeService");
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
		
		this.ipsub.getCompute();
		this.ipsub.putComputeIntoDB();
	}

}

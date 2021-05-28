package cn.dlut.vnetlab.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.service.Vnet_OpsFlavorService;

public class Vnet_OpsFlavorServiceImplTest extends BaseTest {

	private Vnet_OpsFlavorService ofsi;
	protected void setUp() throws Exception{
		super.setUp();
		ofsi=(Vnet_OpsFlavorService)this.ctx.getBean("Vnet_OpsFlavorService");
		
	}
	protected void tearDown() throws Exception{
		super.tearDown();
		ofsi=null;
	}
	@Test
	public void testUpdateFlavor() {
		ofsi.updateFlavor();
		System.out.println("ok");
	}

}

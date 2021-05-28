package cn.dlut.vnetlab.test.service;


import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.service.Vnet_UpdateimageService;

public class Vnet_UpdateimageServiceImplTest extends BaseTest{

	private Vnet_UpdateimageService update;
	
	protected void setUp() throws Exception{
		super.setUp();
		update=(Vnet_UpdateimageService)this.ctx.getBean("Vnet_UpdateimageService");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown(); 
		update=null;
	}
	
	@Test
	public void testupdateImage() {
		this.update.updateImage();
	}

}

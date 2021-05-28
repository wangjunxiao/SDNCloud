package cn.dlut.vnetlab.test.service;


import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.entity.Vnet_image;
import cn.dlut.service.Vnet_ShowimageService;

public class Vnet_ShowimageServiceImplTest extends BaseTest{

	private Vnet_ShowimageService showimageservice;

	protected void setUp() throws Exception {
		super.setUp();
		showimageservice = (Vnet_ShowimageService)this.ctx.getBean("Vnet_ShowimageService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		showimageservice = null;
	}
	
	@Test
	public void testGetAll() {
		List<Vnet_image> images = showimageservice.queryImage();
		for(Vnet_image image:images){
			System.out.println(image.toString());
		}
	}
}
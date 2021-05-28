package cn.dlut.vnetlab.test.service;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_imageDao;
import cn.dlut.entity.Vnet_image;
import cn.dlut.service.Vnet_OpsImageService;
import flex.messaging.io.ArrayList;

public class Vnet_OpsImageServiceImplTest extends BaseTest{
	
	private Vnet_OpsImageService image;
	
	protected void setUp() throws Exception{
		super.setUp();
		image=(Vnet_OpsImageService)this.ctx.getBean("Vnet_OpsImageService");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		image=null;
	}

	@Test
	public void testListImage() {
		String result = image.ListImage();
		System.out.println(result);
	}	
}

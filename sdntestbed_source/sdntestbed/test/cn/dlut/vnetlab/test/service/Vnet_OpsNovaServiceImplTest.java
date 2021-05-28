package cn.dlut.vnetlab.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.service.Vnet_OpsImageService;
import cn.dlut.service.Vnet_OpsNovaService;

public class Vnet_OpsNovaServiceImplTest  extends BaseTest{
	
	private Vnet_OpsNovaService nova;

	protected void setUp() throws Exception{
		super.setUp();
		nova=(Vnet_OpsNovaService)this.ctx.getBean("Vnet_OpsNovaService");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		nova=null;
	}

//	@Test
//	public void testGetSeriallist() {
//		ArrayList<Map<String, String>> result = nova.getSeriallist();
//		System.out.println("##########");
//		System.out.println(result.get(0));
//		System.out.println(result.get(1));
//		System.out.println("length:" + result.size());
//	}
	
//	@Test
//	public void testGetvnclist() {
//		List<Map<String, String>> result = nova.getvnclist();
//		System.out.println("##########");
//		System.out.println("length:" + result.size());
//		for (int i = 0; i < result.size(); i++) {
//			System.out.println(result.get(i));
//		}
//	}
	
	@Test
	public void testGetVncUrl() {
		String result = nova.getVncUrl("instance~9~Mon May 9 22:04:37 GMT+0800 2016~lsy");
		System.out.println(result);
	}

}

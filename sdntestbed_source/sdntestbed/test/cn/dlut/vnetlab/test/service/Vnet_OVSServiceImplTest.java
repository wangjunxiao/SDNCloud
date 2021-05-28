package cn.dlut.vnetlab.test.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;


import cn.dlut.BaseTest;
import cn.dlut.entity.Vnet_WSRequest;
import cn.dlut.service.Vnet_OVSService;

public class Vnet_OVSServiceImplTest extends BaseTest{
	
	private Vnet_OVSService ovs;

	protected void setUp() throws Exception{
		super.setUp();
		ovs=(Vnet_OVSService)this.ctx.getBean("Vnet_OVSService");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		ovs=null;
	}
	
	@Test
	private String testdel_br(String compute_url,String br){
		return "";
		
	}
	
	

}
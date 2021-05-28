package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_switchDao;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_switch;

public class Vnet_switchDaoImplTest extends BaseTest{
	 private Vnet_switchDao dao;
		
		protected void setUp() throws Exception{
			super.setUp();
			dao=( Vnet_switchDao ) this.ctx.getBean("Vnet_switchDao");
		}
		
		protected void tearDown() throws Exception{
			super.tearDown();
			dao=null;
		}
//		@Test
//		public void testGetAll() {
//			List<Vnet_switch> switches=dao.getAll();
//			for(Vnet_switch switch1:switches)
//			{
//				System.out.println(switch1.toString());
//			}
//
//			
//		}
//		
		@Test
		public void testUpdate()
		{
			Vnet_switch s1=new Vnet_switch("s~13~Wed May 25 15:50:17 GMT+0800 2016~lsy","s1283",1283);
			dao.updateSwitch(s1);
		}
		

//	@Test
//	public void testGetAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInsertSwitch() {
//		Vnet_switch im = new Vnet_switch();
//		im.setSwitch_name("abc");
//		this.dao.insertSwitch(im);
//	}

//	@Test
//	public void testDelById() {
//		fail("Not yet implemented");
//	}
//
}

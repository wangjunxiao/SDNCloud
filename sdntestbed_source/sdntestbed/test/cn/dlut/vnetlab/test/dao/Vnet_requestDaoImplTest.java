package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_requestDao;
import cn.dlut.dao.Vnet_switchDao;
import cn.dlut.entity.Vnet_request;
import cn.dlut.entity.Vnet_switch;

public class Vnet_requestDaoImplTest  extends BaseTest{
	 private Vnet_requestDao dao;
		
		protected void setUp() throws Exception{
			super.setUp();
			dao=( Vnet_requestDao ) this.ctx.getBean("Vnet_requestDao");
		}
		
		protected void tearDown() throws Exception{
			super.tearDown();
			dao=null;
		}
//		@Test
//		public void testGetAll() {
//			List<Vnet_request> switches=dao.getAll();
//			for(Vnet_request switch1:switches)
//			{
//				System.out.println(switch1.toString());
//			}
//
//			
//		}
		public void testInsertSwitch() {
			Vnet_request im = new Vnet_request();
			im.setRequest_name("aaa");
			this.dao.updateRequest(im);
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
//	public void testDelById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateRequest() {
//		fail("Not yet implemented");
//	}

}

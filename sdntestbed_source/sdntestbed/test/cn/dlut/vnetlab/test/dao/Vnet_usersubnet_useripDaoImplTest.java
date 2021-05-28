package cn.dlut.vnetlab.test.dao;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_subnetDao;
import cn.dlut.dao.Vnet_usersubnet_useripDao;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.entity.Vnet_usersubnet_userip;


public class Vnet_usersubnet_useripDaoImplTest extends BaseTest {
	    private Vnet_usersubnet_useripDao dao;
		
		protected void setUp() throws Exception{
			super.setUp();
			dao=( Vnet_usersubnet_useripDao ) this.ctx.getBean("Vnet_usersubnet_useripDao");
		}
		
		protected void tearDown() throws Exception{
			super.tearDown();
			dao=null;
		}
		@Test
//		public void testinsertUsersubnet_userip() {
//		Vnet_usersubnet_userip im = new Vnet_usersubnet_userip();
////		im.setUsersubnet_userip_id(1);
//	    im.setUsersubnet_id("aaa");
//		
//		this.dao.insertUsersubnet_userip(im);
//		}
		public void testGetAll() {
			List<Vnet_usersubnet_userip> ips=dao.getAll();
			for(Vnet_usersubnet_userip ip:ips)
			{
				System.out.println(ip.toString());
			}

		}

}

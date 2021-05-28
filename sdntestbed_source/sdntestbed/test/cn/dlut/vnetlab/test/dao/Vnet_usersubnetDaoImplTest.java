package cn.dlut.vnetlab.test.dao;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_useripDao;
import cn.dlut.dao.Vnet_subnetDao;
import cn.dlut.entity.Vnet_userip;
import cn.dlut.entity.Vnet_subnet;


public class Vnet_usersubnetDaoImplTest extends BaseTest{
	    private Vnet_subnetDao dao;
		
		protected void setUp() throws Exception{
			super.setUp();
			dao=( Vnet_subnetDao ) this.ctx.getBean("Vnet_usersubnetDao");
		}
		
		protected void tearDown() throws Exception{
			super.tearDown();
			dao=null;
		}
		@Test
//		public void testinsertUsersubnet() {
//		Vnet_usersubnet im = new Vnet_usersubnet();
//		im.setUsersubnet_id("kjhkyujddd");
//	
//		
//		this.dao.insertUsersubnet(im);
//		}
		
		public void testGetAll() {
			List<Vnet_subnet> ips=dao.getAll();
			for(Vnet_subnet ip:ips)
			{
				System.out.println(ip.toString());
			}

		}
}

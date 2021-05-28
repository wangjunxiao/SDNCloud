package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_tunDao;
import cn.dlut.dao.Vnet_tunofportDao;
import cn.dlut.entity.Vnet_tun;
import cn.dlut.entity.Vnet_tunofport;

public class Vnet_tunDaoImplTest extends BaseTest{
	 private Vnet_tunDao dao;
		
		protected void setUp() throws Exception{
			super.setUp();
			dao=( Vnet_tunDao ) this.ctx.getBean("Vnet_tunDao");
		}
		
		protected void tearDown() throws Exception{
			super.tearDown();
			dao=null;
		}
		@Test
		public void testGetAll() {
			List<Vnet_tun> switches=dao.getAll();
			for(Vnet_tun switch1:switches)
			{
				System.out.println(switch1.toString());
			}

			
		}

		
//		@Test
//		public void testInsertSwitch() {
//			Vnet_tun im = new Vnet_tun();
//			im.setTun_tag("www");
//			this.dao.insertTun(im);
//		}


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
//	public void testInsertTun() {
//		fail("Not yet implemented");
//	}

}

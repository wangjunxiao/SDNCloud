package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_switchDao;
import cn.dlut.dao.Vnet_tunofportDao;
import cn.dlut.entity.Vnet_switch;
import cn.dlut.entity.Vnet_tunofport;

public class Vnet_tunofportDaoImplTest extends BaseTest {
	
	 private Vnet_tunofportDao dao;
		
		protected void setUp() throws Exception{
			super.setUp();
			dao=( Vnet_tunofportDao ) this.ctx.getBean("Vnet_tunofportDao");
		}
		
		protected void tearDown() throws Exception{
			super.tearDown();
			dao=null;
		}
//		@Test
//		public void testGetAll() {
//			List<Vnet_tunofport> switches=dao.getAll();
//			for(Vnet_tunofport switch1:switches)
//			{
//				System.out.println(switch1.toString());
//			}
//
//			
//		}

		
		@Test
		public void testInsertSwitch() {
			Vnet_tunofport im = new Vnet_tunofport();
			im.setTunofport_portnum(111);
			this.dao.insertTunofport(im);
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
//	public void testInsertTunofport() {
//		fail("Not yet implemented");
//	}

}

package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_osportDao;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_osport;

public class Vnet_osportDaoImplTest extends BaseTest{
	
	  private Vnet_osportDao dao;
		
		protected void setUp() throws Exception{
			super.setUp();
			dao=( Vnet_osportDao ) this.ctx.getBean("Vnet_osportDao");
		}
		
		protected void tearDown() throws Exception{
			super.tearDown();
			dao=null;
		}
	@Test
//	public void testGetAll() {
//	List<Vnet_osport> osports=dao.getAll();
//	for(Vnet_osport osport:osports)
//	{
//		System.out.println(osport.toString());
//	}
//
//}
	public void testinsertOsport() {
		System.out.println("testing!!");
		Vnet_osport temp=dao.getbyHostid("h~5~Thu May 19 16:05:53 GMT+0800 2016~lc");
		System.out.println(temp);
		
}
//
//	@Test
//	public void testGetById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInsertOsport() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelById() {
//		fail("Not yet implemented");
//	}

}

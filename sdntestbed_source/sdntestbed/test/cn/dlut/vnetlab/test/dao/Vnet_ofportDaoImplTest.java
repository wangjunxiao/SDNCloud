package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_ofportDao;
import cn.dlut.entity.Vnet_ofport;

public class Vnet_ofportDaoImplTest extends BaseTest{
	
	private Vnet_ofportDao dao;
		
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_ofportDao ) this.ctx.getBean("Vnet_ofportDao");
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
	public void testinsertOfport() {
		System.out.println("testing of port");
		Vnet_ofport im = new Vnet_ofport();
		im.setOfport_id("ofport~77");
		im.setOfport_portnum(1);
		im.setOfport_status("wait");
		
		this.dao.insertOfport(im);
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

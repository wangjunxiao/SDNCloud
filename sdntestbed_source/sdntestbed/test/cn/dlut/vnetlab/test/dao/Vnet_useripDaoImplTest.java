package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_useripDao;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_userip;

public class Vnet_useripDaoImplTest  extends BaseTest  {
    private Vnet_useripDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_useripDao ) this.ctx.getBean("Vnet_useripDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

	@Test
	public void testGetAll() {
		List<Vnet_userip> ips=dao.getAll();
		for(Vnet_userip ip:ips)
		{
			System.out.println(ip.toString());
		}

	}

//	public void testGetById() {
//	int iden=1;
//	Vnet_userip test1=dao.getById(iden);
//	System.out.println(test1.toString());
//}
//	public void testinsertUserip() {
//		Vnet_userip im = new Vnet_userip();
//		im.setUserip_id("kjhkyuj");
//		im.setUserip_addr("kjhkyuj");
//		im.setUserip_mask("kjhkyuj");
//		
//		
//		this.dao.insertUserip(im);
//	@Test
//	public void testDelById() {
//		fail("Not yet implemented");
//	}

}


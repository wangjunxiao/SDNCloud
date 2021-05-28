package cn.dlut.vnetlab.test.dao;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_usersubnet_useripDao;
import cn.dlut.dao.Vnet_vnet_usersubnetDao;
import cn.dlut.entity.Vnet_usersubnet_userip;
import cn.dlut.entity.Vnet_vnet_usersubnet;


public class Vnet_vnet_usersubnetDaoImplTest extends BaseTest{
	private Vnet_vnet_usersubnetDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_vnet_usersubnetDao ) this.ctx.getBean("Vnet_vnet_usersubnetDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
	@Test
//	public void testinsertVnet_usersubnet() {
//	Vnet_vnet_usersubnet im = new Vnet_vnet_usersubnet();
//
//    im.setUsersubnet_id("aaa");
//	
//	this.dao.insertVnet_usersubnet(im);
//	}
	public void testGetAll() {
		List<Vnet_vnet_usersubnet> ips=dao.getAll();
		for(Vnet_vnet_usersubnet ip:ips)
		{
			System.out.println(ip.toString());
		}

	}


}

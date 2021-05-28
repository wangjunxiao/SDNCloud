package cn.dlut.vnetlab.test.dao;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_usersubnet_useripDao;
import cn.dlut.dao.Vnet_vnet_userscriptDao;
import cn.dlut.entity.Vnet_usersubnet_userip;
import cn.dlut.entity.Vnet_vnet_userscript;


public class Vnet_vnet_userscriptDaoImplTest extends BaseTest{
	private Vnet_vnet_userscriptDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_vnet_userscriptDao ) this.ctx.getBean("Vnet_vnet_userscriptDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
	@Test
//	public void testinsertVnet_userscript() {
//	Vnet_vnet_userscript im = new Vnet_vnet_userscript();
//
//    im.setUserscript_id("aaa");
//	
//	this.dao.insertVnet_userscript(im);
//	}
	public void testGetAll() {
		List<Vnet_vnet_userscript> ips=dao.getAll();
		for(Vnet_vnet_userscript ip:ips)
		{
			System.out.println(ip.toString());
		}

	}

}

package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_useripDao;
import cn.dlut.dao.Vnet_scriptDao;
import cn.dlut.entity.Vnet_userip;
import cn.dlut.entity.Vnet_script;

public class Vnet_userscriptDaoImplTest extends BaseTest  {
      private Vnet_scriptDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_scriptDao ) this.ctx.getBean("Vnet_userscriptDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
	

	@Test
	public void testGetAll() {
		List<Vnet_script> ips=dao.getAll();
		for(Vnet_script ip:ips)
		{
			System.out.println(ip.toString());
		}

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

//	@Test
//	public void testinsertUserscript() {
//	Vnet_userscript im = new Vnet_userscript();
//	im.setUserscript_id("kjhkyujddd");
//	im.setUserscript_name("kjhkyuj");
//	im.setUserscript_type("kjhkyuj");
//	im.setUserscript_content("kjhkyuj");
//	
//	this.dao.insertUserscript(im);
//	}


}

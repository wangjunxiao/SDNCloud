package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_hostDao;
import cn.dlut.dao.Vnet_vnetDao;
import cn.dlut.entity.Vnet_host;
import cn.dlut.entity.Vnet_vnet;

public class Vnet_vnetDaoImplTest extends BaseTest
{
	private Vnet_vnetDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_vnetDao ) this.ctx.getBean("Vnet_vnetDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

//	@Test
//	public void testGetAll() {
//		List<Vnet_vnet> hosts=dao.getAll();
//		for(Vnet_vnet host:hosts)
//		{
//			System.out.println(host.toString());
//		}
//	}

//	@Test
//	public void testGetById() {
//		int id=1;
//		Vnet_vnet test1=dao.getById(id);
//		System.out.println(test1.toString());
//	}

//	@Test
//	public void testDelById() {
//		int id=2;
//		dao.delById(id);
//		System.out.println(dao.getAll());
//	}

	@Test
	public void testInsertVnet() {
		Vnet_vnet im = new Vnet_vnet();
		im.setVnet_name("dfasd");
		
		this.dao.insertVnet(im);
		System.out.println(dao.getAll());
	}


}

package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_hostDao;
import cn.dlut.entity.Vnet_host;

public class Vnet_hostDaoImplTest extends BaseTest {
	
	private Vnet_hostDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_hostDao ) this.ctx.getBean("Vnet_hostDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

//	@Test
//	public void testGetAll() {
//		List<Vnet_host> hosts=dao.getAll();
//		for(Vnet_host host:hosts)
//		{
//			System.out.println(host.toString());
//		}
//	}

//	@Test
//	public void testGetById() {
//		int id=111;
//		Vnet_host test1=dao.getById(id);
//		System.out.println(test1.toString());
//	}
//
//	@Test
//	public void testDelById() {
//		int id=111;
//		dao.delById(id);
//		System.out.println(dao.getAll());
//	}
//
	@Test
	public void testInsertHost() {
		Vnet_host im = new Vnet_host();
		im.setHost_name("hihihi");
		
		this.dao.insertHost(im);
	}

}

package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_controllerDao;
import cn.dlut.dao.Vnet_vnetDao;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_vnet;

public class Vnet_controllerDaoImplTest extends BaseTest{
	
	private Vnet_controllerDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_controllerDao ) this.ctx.getBean("Vnet_controllerDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

//	@Test
//	public void testGetAll() {
//		List<Vnet_controller> hosts=dao.getAll();
//		for(Vnet_controller host:hosts)
//		{
//			System.out.println(host.toString());
//		}	}

//	@Test
//	public void testGetById() {
//		int id=2;
//		dao.delById(id);
//		System.out.println(dao.getAll());	}

//	@Test
//	public void testDelById() {
//		int id=1;
//		dao.delById(id);
//		System.out.println(dao.getAll());
//	}

//	@Test
//	public void testInsertController() {
//		Vnet_controller im = new Vnet_controller();
//		im.setController_type("floodlight");
//		
//		this.dao.insertController(im);
//		System.out.println(dao.getAll());	}

}

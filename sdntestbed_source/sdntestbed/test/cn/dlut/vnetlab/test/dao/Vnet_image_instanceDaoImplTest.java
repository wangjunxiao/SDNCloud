package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_imageDao;
import cn.dlut.dao.Vnet_image_instanceDao;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

public class Vnet_image_instanceDaoImplTest extends BaseTest{

	private Vnet_image_instanceDao dao;

	protected void setUp() throws Exception {
		super.setUp();
		dao = (Vnet_image_instanceDao) this.ctx.getBean("Vnet_image_instanceDao");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dao = null;
	}
	
	@Test
	public void testGetAll() {
		List<Vnet_image_instance> images = dao.getAll();
		for(Vnet_image_instance image:images){
			System.out.println(image.toString());
		}
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertImageInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleleImageInstance() {
		fail("Not yet implemented");
	}

}

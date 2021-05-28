package cn.dlut.vnetlab.test.dao;


import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.dao.Vnet_instanceDao;

public class Vnet_instanceDaoImplTest extends BaseTest{
    
	private Vnet_instanceDao dao;

	protected void setUp() throws Exception {
		super.setUp();
		dao = (Vnet_instanceDao) this.ctx.getBean("Vnet_instanceDao");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dao = null;
	}
	
//	@Test
//	public void testGetAll() {
//		List<Vnet_instance> instances = dao.getAll();
//		for(Vnet_instance instance:instances){
//			System.out.println(instance.toString());
//		}
//	}
	
	@Test
	public void testgetinstanceidbyosid(String osid) {
		String id = dao.getInstanceidByosid("4bc91845-f1dc-4721-aac8-e89704fd76ef");
		System.out.println(id);
	}

}

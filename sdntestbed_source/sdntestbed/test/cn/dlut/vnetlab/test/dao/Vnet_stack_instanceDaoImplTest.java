package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_stackDao;
import cn.dlut.dao.Vnet_stack_instanceDao;
import cn.dlut.entity.Vnet_stack;
import cn.dlut.entity.Vnet_stack_instance;

public class Vnet_stack_instanceDaoImplTest extends BaseTest {
private Vnet_stack_instanceDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_stack_instanceDao ) this.ctx.getBean("Vnet_stack_instanceDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
//	@Test
//	public void testGetAll() {
//		List<Vnet_stack_instance> switches=dao.getAll();
//		for(Vnet_stack_instance switch1:switches)
//		{
//			System.out.println(switch1.toString());
//		}
//
//		
//	}
//	@Test
//	public void testInsertSwitch() {
//		Vnet_stack_instance im = new Vnet_stack_instance();
//		im.setStack_id(1);
//		this.dao.insertStack_instance(im);
//	}


//	@Test
//	public void testGetAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInsertStack_instance() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelById() {
//		fail("Not yet implemented");
//	}

}

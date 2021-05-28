package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_stackDao;
import cn.dlut.dao.Vnet_switch_ofportDao;
import cn.dlut.entity.Vnet_stack;
import cn.dlut.entity.Vnet_switch_ofport;

public class Vnet_stackDaoImplTest extends BaseTest{

	private Vnet_stackDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_stackDao ) this.ctx.getBean("Vnet_stackDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
	
	@Test
	public void testInsertAndGetId()
	
	{
		Vnet_stack ss=new Vnet_stack();
		ss.setStack_name("temp——ss");
		int res=dao.insertAndGetId(ss);
		System.out.println(res);
	}
	
//	@Test
//	public void testInsertSwitch() {
//		Vnet_stack im = new Vnet_stack();
//		im.setStack_name("aaa");
//		this.dao.insertStack(im);
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
//	public void testInsertStack() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelById() {
//		fail("Not yet implemented");
//	}

}

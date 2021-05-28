package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_compute_instanceDao;
import cn.dlut.dao.Vnet_compute_switchDao;
import cn.dlut.entity.Vnet_compute_instance;
import cn.dlut.entity.Vnet_compute_switch;

public class Vnet_compute_switchDaoImplTest  extends BaseTest {
private Vnet_compute_switchDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_compute_switchDao ) this.ctx.getBean("Vnet_compute_switchDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
	@Test
	public void testGetAll() {
		List<Vnet_compute_switch> switches=dao.getAll();
		for(Vnet_compute_switch switch1:switches)
		{
			System.out.println(switch1.toString());
		}

		
	}

//	@Test
//	public void testInsertSwitch() {
//		Vnet_compute_switch im = new Vnet_compute_switch();
//		im.setCompute_id(2);
//		this.dao.insertCompute_switch(im);
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
//	public void testInsertCompute_switch() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelById() {
//		fail("Not yet implemented");
//	}

}

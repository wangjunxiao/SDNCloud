package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_switchDao;
import cn.dlut.dao.Vnet_switch_ofportDao;
import cn.dlut.entity.Vnet_switch;
import cn.dlut.entity.Vnet_switch_ofport;

public class Vnet_switch_ofportDaoImplTest extends BaseTest{
	
	
	private Vnet_switch_ofportDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_switch_ofportDao ) this.ctx.getBean("Vnet_switch_ofportDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
//	@Test
//	public void testGetAll() {
//		List<Vnet_switch_ofport> switches=dao.getAll();
//		for(Vnet_switch_ofport switch1:switches)
//		{
//			System.out.println(switch1.toString());
//		}
//
//		
//	}
//	@Test
//	public void testInsertSwitch() {
//		Vnet_switch_ofport im = new Vnet_switch_ofport();
//		im.setSwitch_id(11);
//		this.dao.insertSwitch_ofport(im);
//	}

//
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
//	public void testInsertSwitch_ofport() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelById() {
//		fail("Not yet implemented");
//	}

}

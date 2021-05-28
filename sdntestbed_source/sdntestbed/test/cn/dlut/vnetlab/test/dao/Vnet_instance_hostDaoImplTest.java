package cn.dlut.vnetlab.test.dao;

import java.util.List;
import org.junit.Test;
import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_instance_hostDao;
import cn.dlut.entity.Vnet_instance_host;

public class Vnet_instance_hostDaoImplTest extends BaseTest{

	private Vnet_instance_hostDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_instance_hostDao ) this.ctx.getBean("Vnet_instance_hostDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

//	@Test
//	public void testGetAll() {
//		List<Vnet_instance_host> hosts=dao.getAll();
//		for(Vnet_instance_host host:hosts)
//		{
//			System.out.println(host.toString());
//		}	}

	@Test
	public void testGetById() {
//		int id=1;
//		Vnet_vnet test1=dao.getById(id);
//		System.out.println(test1.toString());	
		}

//	@Test
//	public void testDelById() {
//		int id=1;
//		dao.delById(id);
//		System.out.println(dao.getAll());
//		}

	@Test
	public void testInsertController_switch() {
//		Vnet_controller_switch im = new Vnet_controller_switch();
//		im.setSwitch_id(11);
//		
//		this.dao.insertController_switch(im);
//		System.out.println(dao.getAll());	
//	
		}
	
	@Test 
	public void testgetByHost_id(){
		Vnet_instance_host instance_host = dao.getByHost_id("");
		String a = instance_host.getInstance_id();
//		if (instance_host == null) {
//			System.out.println(a + "##");
//		}
	}

}

package cn.dlut.vnetlab.test.dao;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_executorDao;
import cn.dlut.entity.Vnet_executor;

public class Vnet_executorDaoImplTest extends BaseTest{
	
	private Vnet_executorDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_executorDao ) this.ctx.getBean("Vnet_executorDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

	@Test
	public void testGetAll() {
//		List<Vnet_executor> hosts=dao.getAll();
//		for(Vnet_executor host:hosts)
//		{
//			System.out.println(host.toString());
//		}
		}

	@Test
	public void testGetById() {
//		int id=1;
//		Vnet_vnet test1=dao.getById(id);
//		System.out.println(test1.toString());	
		}

	@Test
	public void testDelById() {
//		int id=1;
//		dao.delById(id);
//		System.out.println(dao.getAll());
		}

	@Test
	public void testinsertExecutor() {
		Vnet_executor im = new Vnet_executor();
		im.setExecutor_name("lsy");
		
		this.dao.insertExecutor(im);
		System.out.println(dao.getAll());	
		}
	
	@Test
	public void testGetByNameAndPassword() {
		String executor_name = "admin";
		String executor_password = "0000";
		Map<String, String> nameAndPassword = new HashMap<String, String>();
		nameAndPassword.put("executor_name", executor_name);
		nameAndPassword.put("executor_password", executor_password);
		System.out.println(dao.getByNameAndPassword(nameAndPassword));
	}

}

package cn.dlut.vnetlab.test.dao;

import java.util.List;
import org.junit.Test;
import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_executor_vnetDao;
import cn.dlut.entity.Vnet_executor_vnet;

public class Vnet_executor_vnetDaoImplTest extends BaseTest{

private Vnet_executor_vnetDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_executor_vnetDao ) this.ctx.getBean("Vnet_executor_vnetDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

	@Test
	public void testGetAll() {
		List<Vnet_executor_vnet> hosts=dao.getAll();
		for(Vnet_executor_vnet host:hosts)
		{
			System.out.println(host.toString());
		}	}

	@Test
	public void testGetById() {
//		int id=1;
//		Vnet_vnet test1=dao.getById(id);
//		System.out.println(test1.toString());	
		}

	@Test
	public void testDelById() {
		int id=1;
		dao.delById(id);
		System.out.println(dao.getAll());
		}

	@Test
	public void testInsertExecutor_vnet() {
//		Vnet_executor_vnet im = new Vnet_executor_vnet();
//		im.setVnet_id(1);
//		
//		this.dao.insertExecutor_vnet(im);
//		System.out.println(dao.getAll());	
		
	}
}

package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_instance_osportDao;
import cn.dlut.entity.Vnet_instance_osport;

public class Vnet_instance_osportDaoImplTest extends BaseTest{

	private Vnet_instance_osportDao dao;
	
	protected void setUp() throws Exception{
		
		System.out.println("hello");
		super.setUp();
		dao=( Vnet_instance_osportDao ) this.ctx.getBean("Vnet_instance_osportDao");
		System.out.println("over");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

	
	@Test
	public void test1()
	{
		System.out.println("test for : getOsport_id_by_hostid");
		String result=dao.getOsport_id_by_hostid("h~2~Thu May 19 16:05:53 GMT+0800 2016~lc");
		System.out.println(result);
	}
	
/*	@Test
	public void testGetAll() {
		System.out.println("we get testGetAll() here");
		List<Vnet_instance_osport> ins_osps=dao.getAll();
		for(Vnet_instance_osport ins_osp:ins_osps)
		{
			System.out.println(ins_osp.toString());
		}	
	}*/
	
/*	@Test
	public void testInsert()
	{
		Vnet_flavor_instance im=new Vnet_flavor_instance();
		im.setFlavor_id(3);
		this.dao.insertFlavorInstance(im);
		System.out.println(dao.getAll());
		
	}*/

/*	@Test
	public void testGetById() {
		int id=3;
		Vnet_flavor_instance test1=dao.getById(id);
		System.out.println(test1.toString());	
	}*/
	
/*	@Test
	public void testDelById(){
		int id=3;
		dao.delById(id);
		System.out.println(dao.getAll());
	}*/
	



}

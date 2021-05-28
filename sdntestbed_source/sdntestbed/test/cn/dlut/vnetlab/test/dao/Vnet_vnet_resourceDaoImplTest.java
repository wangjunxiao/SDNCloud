package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_vnet_resourceDao;
import cn.dlut.entity.Vnet_vnet_resource;

public class Vnet_vnet_resourceDaoImplTest extends BaseTest{

	private Vnet_vnet_resourceDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_vnet_resourceDao ) this.ctx.getBean("Vnet_vnet_resourceDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

	@Test
	public void testGetAll() {
		List<Vnet_vnet_resource> fla_ins=dao.getAll();
		for(Vnet_vnet_resource fla_in:fla_ins)
		{
			System.out.println(fla_in.toString());
		}	
	}
	
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

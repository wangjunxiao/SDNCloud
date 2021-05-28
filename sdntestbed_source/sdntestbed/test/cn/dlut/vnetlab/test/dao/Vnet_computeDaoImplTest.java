package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_computeDao;
import cn.dlut.dao.Vnet_hostDao;
import cn.dlut.entity.Vnet_compute;
import cn.dlut.entity.Vnet_vnet;

public class Vnet_computeDaoImplTest extends BaseTest{
	
	private Vnet_computeDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_computeDao ) this.ctx.getBean("Vnet_computeDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

	@Test
	public void testGetAll() {
		List<Vnet_compute> hosts=dao.getAll();
		for(Vnet_compute host:hosts)
		{
			System.out.println(host.toString());
		}	}

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
	public void testInsertCompute() {
//		Vnet_compute im = new Vnet_compute();
//		im.setCompute_addr("daheil");
//		
//		this.dao.insertCompute(im);
//		System.out.println(dao.getAll());	
		}

}

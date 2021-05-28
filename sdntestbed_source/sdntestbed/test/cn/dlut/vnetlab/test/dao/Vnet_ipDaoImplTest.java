package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.entity.Vnet_ip;

public class Vnet_ipDaoImplTest extends BaseTest {
    private Vnet_ipDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_ipDao ) this.ctx.getBean("Vnet_ipDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
//Test
//	public void testGetAll() {
//		List<Vnet_ip> ips=dao.getAll();
//		for(Vnet_ip ip:ips)
//		{
//			System.out.println(ip.toString());
//		}
//
//	}

//	public void testGetById() {
//		int iden=1;
//		Vnet_ip test1=dao.getById(iden);
//		System.out.println(test1.toString());
//	}
	@Test
	public void testinsertIp() {
		Vnet_ip im = new Vnet_ip();
		im.setIp_net("kjhkyuj,nbv");
		
		this.dao.insertIp(im);
		
	}
//	@Test
//	public void testDelById() {
//		fail("Not yet implemented");
//	}

}

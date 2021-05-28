package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_computeDao;
import cn.dlut.dao.Vnet_oflinkDao;
import cn.dlut.entity.Vnet_compute;
import cn.dlut.entity.Vnet_oflink;

public class Vnet_controller_switchDaoImplTest extends BaseTest{
	private Vnet_oflinkDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_oflinkDao ) this.ctx.getBean("Vnet_oflinkDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}

	@Test
	public void testGetAll() {
		List<Vnet_oflink> oflinks=dao.getAll();
		for(Vnet_oflink oflink:oflinks)
		{
			System.out.println(oflink.toString());
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

//	@Test
//	public void testInsertOflink_switch() {
//		Vnet_oflink im = new Vnet_oflink();
//		im.setSwitch_id(11);
//		
//		this.dao.insertOflink(im);
//		System.out.println(dao.getAll());	
//		}
}

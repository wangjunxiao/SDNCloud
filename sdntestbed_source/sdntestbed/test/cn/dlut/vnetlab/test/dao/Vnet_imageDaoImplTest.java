package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;

import cn.dlut.dao.Vnet_imageDao;

import cn.dlut.entity.Vnet_image;

public class Vnet_imageDaoImplTest extends BaseTest{

	private Vnet_imageDao dao;

	protected void setUp() throws Exception {
		super.setUp();
		dao = (Vnet_imageDao) this.ctx.getBean("Vnet_imageDao");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dao = null;
	}
	
	@Test
	public void testGetAll() {	
		List<Vnet_image> images = dao.getAll();
		for(Vnet_image image:images){
			System.out.println(image.toString());
		}
	}
	 
//	@Test
//	public void testUpdateImage() {
//		Vnet_image im = new Vnet_image();
//		im.setImage_format("eeeeeee");
//		im.setImage_name("fffffff");
//		im.setImage_osid("ffff");
//		im.setImage_size("ddeeed");
//		
//		this.dao.updateImage(im);
//	}
	
//	@Test
//	public void testDelById() {
//		dao.delById(1);
//	}


}

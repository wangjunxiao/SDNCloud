package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_ip;

public class Vnet_floatingipDaoImplTest extends BaseTest{
	
	private Vnet_floatingipDao dao;
		
		protected void setUp() throws Exception{
			super.setUp();
			dao=( Vnet_floatingipDao ) this.ctx.getBean("Vnet_floatingipDao");
		}
		
		protected void tearDown() throws Exception{
			super.tearDown();
			dao=null;
		}
		
		@Test 
		public void testGetRandom()
		{
			Vnet_floatingip temp=dao.getbyControllerid("c~18~Thu May 19 16:05:53 GMT+0800 2016~lc");
			
			System.out.println(temp.toString());
			
		}
		
/*		@Test
		public void testGetAll()
		{
			//System.out.println("before");
			List<Vnet_floatingip> temps=dao.getAll();
			//System.out.println("after");
			for(Vnet_floatingip i:temps)
			{
				System.out.println(i.toString());
			}
		}*/

}

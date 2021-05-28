package cn.dlut.vnetlab.test.service;

import org.junit.Test;
import cn.dlut.BaseTest;
import cn.dlut.entity.Vnet_executor;
import cn.dlut.service.Vnet_LoginService;

public class Vnet_LoginServiceImplTest extends BaseTest {

	private Vnet_LoginService loginService;
	protected void setUp() throws Exception{
		super.setUp();
		loginService=(Vnet_LoginService)this.ctx.getBean("Vnet_LoginService");
		
	}
	protected void tearDown() throws Exception{
		super.tearDown();
		loginService=null;
	}
	@Test
	public void test() {
		Vnet_executor executor = loginService.Auth("admin","0000");
		if(executor != null)
			System.out.println("ok");
	}
}

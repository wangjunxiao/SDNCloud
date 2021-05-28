package cn.dlut.vnetlab.test.service;

import org.junit.Test;

import cn.dlut.BaseTest;
import cn.dlut.entity.Vnet_WSRequest;
import cn.dlut.service.Vnet_WebSocketService;

public class Vnet_WebSocketServiceImplTest extends BaseTest{

	private Vnet_WebSocketService websocket;
//	private Vnet_OpsHeatService heat;
	
	protected void setUp() throws Exception{
		super.setUp();
		websocket=(Vnet_WebSocketService)this.ctx.getBean("Vnet_WebSocketService");
//		heat=(Vnet_OpsHeatService)this.ctx.getBean("Vnet_OpsHeatService");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown(); 
		websocket=null;
	}
}
	
//	@Test
//	public void test() throws InterruptedException {
//		Vnet_WSRequest req1 = new Vnet_WSRequest("", "send_for_dhcp", null, null, null, null, null);
//		Vnet_WSRequest req2 = new Vnet_WSRequest("", "query_for_dhcp", null, null, null, null, null);
//		String send = websocket.create_Websocket(req1, "send", "0b9ce27d-e7a3-4e3d-945c-32b4b5eedd25", "ubuntu", "");
//		System.out.println(send);
//		while (true) {
//			String res = websocket.create_Websocket(req2, "recv", "", "ubuntu", "");
//			if(res.contains("READY")){
//				System.out.println("READY!");
//				System.out.println(res);
//				break;
//			}
//			else if(res.contains("BAD")){
//				System.out.println("BAD!");
//				System.out.println(res);
//				Thread.sleep(8000);
//			}
//			else if(res.contains("NULL")){
//				System.out.println("NULL!");
//				System.out.println(res);
//				Thread.sleep(2000);
//			}
//			else{
//				System.out.println("exit!");
//				System.out.println(res);
//				break;
//			}
//		}
//	}
//
//
//}

package cn.dlut.service.impl;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.entity.Vnet_WSRequest;
import cn.dlut.service.Vnet_OpsHeatService;
import cn.dlut.service.Vnet_OpsNovaService;
import cn.dlut.service.Vnet_WebSocketService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;


@Service("Vnet_WebSocketService")
public class Vnet_WebSocketServiceImpl extends AbstractBaseService  implements Vnet_WebSocketService{
	
	@Autowired
	private Vnet_OpsNovaService nova;
	

	@Override
	public String create_Websocket(Vnet_WSRequest req, String method, String target, String type, String action ,int config,String floating_ip) {
		
		String result = "";
		String str = "";
		//str = #url#~#action/ip_addr#~#type#
		String POST_URL = "http://"+AppProperties.getVnet_config_url();
		System.out.println("##############" + POST_URL + "######################");
//		String POST_URL = "http://192.168.182.242:56789";
		String content_showovs = "";
		
		if(req.getType().equals("ping")){
			final long between = (req.getStop().getTime()-req.getStart().getTime())/1000;
			action = "ping " + req.getArgs2() + " -w " + between + " > ping" 
							+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt &";
			if (method == "send") {
				String serial_url = nova.getSerialUrl(target);
				str = serial_url + "~" + action;
			}
		    content_showovs = "{\"id\":\"" + req.getJson_id() + "\",\"jsonrpc\":\"2.0\",\"method\":\"" 
		    					+ method + "\",\"params\":{\"cmd\":\"" + str + "\"}}";
		    try {
	     		result = Vnet_HttpRequest.rpcFromPost(POST_URL, content_showovs);
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		
		else if(req.getType().equals("ifconfig")){ 	
			action = "sudo ifconfig eth0 " + req.getArgs1() + " netmask " + req.getArgs2();
			if (method == "send") {
				String serial_url = nova.getSerialUrl(target);
				str = serial_url + "~" + action;
			}
			content_showovs = "{\"id\":\"" + req.getJson_id() + "\",\"jsonrpc\":\"2.0\",\"method\":\"" 
								+ method + "\",\"params\":{\"cmd\":\"" + str + "\"}}";
			try {
				result = Vnet_HttpRequest.rpcFromPost(POST_URL, content_showovs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(req.getType().equals("rc.local")){
			action = "sudo echo \"ifconfig eth0 " + req.getArgs1() + " netmask " + req.getArgs2() + "\" >> /ect/rc.d/rc.local";
			if (method == "send") {
				String serial_url = nova.getSerialUrl(target);
				str = serial_url + "~" + action;
			}
			content_showovs = "{\"id\":\"" + req.getJson_id() + "\",\"jsonrpc\":\"2.0\",\"method\":\"" 
								+ method + "\",\"params\":{\"cmd\":\"" + str + "\"}}";
			try {
				result = Vnet_HttpRequest.rpcFromPost(POST_URL, content_showovs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(req.getType().equals("send_for_dhcp")){
			if (method == "send") {
				String serial_url = nova.getSerialUrlByosid(target);
				str = serial_url + "~" + action + "~" + type + "~" + target+"~" + config + "~" + floating_ip;
				System.out.println("##############" + str + "######################");
			}
			content_showovs = "{\"id\":\"" + req.getJson_id() + "\",\"jsonrpc\":\"2.0\",\"method\":\"" 
								+ method + "\",\"params\":{\"cmd\":\"" + str + "\"}}";
			try {
				result = Vnet_HttpRequest.rpcFromPost(POST_URL, content_showovs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(req.getType().equals("query_for_dhcp")){
			content_showovs = "{\"id\":\"" + req.getJson_id() + "\",\"jsonrpc\":\"2.0\",\"method\":\"" 
								+ method + "\",\"params\":{\"cmd\":\"" + str + "\"}}";
			try {
				result = Vnet_HttpRequest.rpcFromPost(POST_URL, content_showovs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}

//@Test
//public void testCreate_Websocket() throws InterruptedException, ParseException {
//	Date start = new SimpleDateFormat("yyyyMMddHHmmss").parse("20160427102900"); 
//	Date stop = new SimpleDateFormat("yyyyMMddHHmmss").parse("20160427102905"); 
//	
//	final Vnet_WSRequest ws1 = new Vnet_WSRequest(1, "ping", start, stop, "10.2.0.43", "10.2.0.65", "");
//	final Vnet_WSRequest ws2 = new Vnet_WSRequest(2, "ping", start, stop, "10.2.0.65", "10.2.0.43", "");
//	final long between = (stop.getTime()-start.getTime())/1000;
//	
//	System.out.println("ping " + ws1.getDst() + " -w " + between + " > ping" 
//			+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt &");
//	
//	System.out.println("ping " + ws2.getDst() + " -w " + between + " > ping" 
//			+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt &");
//	
//	Timer timer = new Timer();
//    TimerTask task = new TimerTask(){
//    	@Override
//    	public void run() {
//    		String send1 = ovs.create_Websocket(1, "send", "instance~test-tty",
//												"ping " + ws1.getDst() + " -w " + between + " > ping" 
//												+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt &" );
//    		String send2 = ovs.create_Websocket(2, "send", "instance~test-tty2",
//    											"ping " + ws2.getDst() + " -w " + between + " > ping" 
//    											+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt &" );
//			System.out.println(send1 + " ###send1###");
//			System.out.println(send2 + " ###send2###");
//			
////			try {
////				Thread.sleep(1000);
////			} catch (InterruptedException e) {
////				e.printStackTrace();
////			}
//			
////			String res1 = ovs.create_Websocket(1, "send", "","close");
////			String res2 = ovs.create_Websocket(2, "send", "instance~test-tty2","close");
////			System.out.println(res1 + " ###result1###");
////			System.out.println(res2 + " ###result2###");
//    	}
//    };
//    
//    timer.schedule(task, start);
//    
//    Thread.sleep(99999999);
//}


package cn.dlut.service.impl;

import java.io.IOException;

import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_flavorDao;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.service.Vnet_OVSService;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.service.Vnet_OpsNeutronService;
import cn.dlut.service.Vnet_OpsNovaService;
import cn.dlut.service.Vnet_ShowflavorService;
import cn.dlut.util.Vnet_HttpRequest;
import flex.messaging.cluster.BroadcastHandler;


import java.io.*;

@Service("Vnet_OVSService")
 public class Vnet_OVSServiceImpl extends AbstractBaseService implements Vnet_OVSService{
	@Autowired
	private Vnet_OpsIdentityService ide;
	
	@Autowired
	private Vnet_OpsNovaService nova;
	
	@Override
	public String showovs(String  compute_url) {//ovs-vsctl show
		String POST_URL1 = "http://"+compute_url+":12345";
	    String content_showovs="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl show\"}}";
	    String result1="";
	    String result2="";
	    try {
     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_showovs);
	 } catch (IOException e) {
	    	e.printStackTrace();
	 }
	       return result1;
	}
	
	
	
	
	@Override
	public String del_br(String compute_url,String br) {//delete bridge
		String POST_URL1 = "http://"+compute_url+":12345";
	    String content_del_br="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl del-br "+br+"\"}}";
	    String result1="";
	    String result2="";
	    try {
     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_del_br);
	 } catch (IOException e) {
	    	e.printStackTrace();
	 } 
	       return result1;
	}
	
	public String add_br(String compute_url,String br) {//create bridge
		String POST_URL1 = "http://"+compute_url+":12345";
	    String content_add_br="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl add-br "+br+"\"}}";
	    String result1="";
	    try {
     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_add_br);
	 } catch (IOException e) {
	    	e.printStackTrace();
	 }
	 
	   	String content_set_secure="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl set-fail-mode "+br+ " secure" + "\"}}";
	   	String result2="";
	   	try
	   	{
	   		result2=Vnet_HttpRequest.rpcFromPost(POST_URL1, content_set_secure);
	   	}catch(IOException e)
	   	{
	   		e.printStackTrace();
	   	}
	   	return result2;
	}
	
	public String set_controller(String compute_url,String br,String ip,String port) {//add oflink
		String POST_URL1 = "http://"+compute_url+":12345";
	    String content_set_controller="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl set-controller "+br+" "+"tcp:"+ip+":"+port+"\"}}";
	    String result1="";
	    try {
     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_set_controller);
	 } catch (IOException e) {
	    	e.printStackTrace();
	 }
       return result1;
	}
	
	public String del_controller(String  compute_url,String br,String ip) {//delete oflink
		String POST_URL1 = "http://"+compute_url+":12345";
	    String content_del_controller="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl del-controller "+br+"\"}}";
	    String result1="";
	    try {
     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_del_controller);
	 } catch (IOException e) {
	    	e.printStackTrace();
	 }
       return result1;
	}

	@Override
	public String set_switchlink(String  compute_url,String br0, String br1,Integer flag) {//add two switch link
		String POST_URL1 = "http://"+compute_url+":12345";
		String content_set_switchlink="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl add-port "+br0+
		" "+br0+br1+flag+" -- set interface "+br0+br1+flag+" type=patch options:peer="+br1+br0+flag+"\"}}";
		 String result1="";
		    try {
	     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_set_switchlink);
//	     		System.out.println(result);
		 } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		 }
	       return result1;
	}
	
	
	
	@Override
	public void del_switchlink(String  compute_url1,String  compute_url2,String br0, String br1,String port0,String port1) {//delete two switch link
		String POST_URL1 = "http://"+compute_url1+":12345";
		String POST_URL2 = "http://"+compute_url2+":12345";
		String result1="";
		String result2="";
		String content_del_switchlink1="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl del-port "+br0+" "+port0+"\"}}";
		    try {
	     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_del_switchlink1);
//	     		System.out.println(result);
		 } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		 }
		String content_del_switchlink2="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl del-port "+br1+" "+port1+"\"}}";
		    try {
	     		result2 = Vnet_HttpRequest.rpcFromPost(POST_URL2, content_del_switchlink2);
//	     		System.out.println(result);
		 } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		 }
	      
	}
    
	
	
	@Override
	public String set_vmlink(String compute_url,String br0, String port) {//create host switch link
		String POST_URL1 = "http://"+compute_url+":12345";
		String port1=port.substring(0,11);
		port1="qvo"+port1;
		// delete original br-int's link
		String content_set_vmlink1="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl del-port br-int "+port1+"\"}}";
		 String result1="";
		    try {
	     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_set_vmlink1);
//	     		System.out.println(result);
		 } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		 }
		 // create link connected with new created switch
		String content_set_vmlink2="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl add-port "+br0+" "+port1+"\"}}";
		 String result2="";
		    try {
	     		result2 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_set_vmlink2);
//	     		System.out.println(result);
		 } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		 }
		 return result2;
	}
	@Override
	public String del_vmlink(String compute_url, String br0, String port) {
		// TODO Auto-generated method stub
		String POST_URL1 = "http://"+compute_url+":12345";
		String port1=port.substring(0,11);
		port1="qvo"+port1;
		
		String content_set_vmlink1="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl del-port "+br0+" "+port1+"\"}}";
		String result1="";
		    try {
	     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL1, content_set_vmlink1);
//	     		System.out.println(result);
		 } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		 }
		return result1;
	}

	//install flow entries
	@Override
	public String set_flow(String compute_url,String tun_tag,String inport_id,String outport_id)
	{
	//	BufferedWriter flowWrite=null;
	//	BufferedWriter resultWrite=null;
//			flowWrite=new BufferedWriter(new FileWriter("D:/flows.txt",true));
//			resultWrite=new BufferedWriter(new FileWriter("D:/results.txt",true));
			
			// install flow entries for each compute node, sum up 4 entries
			String result1="";
			String result2="";
			String result3="";
			String result4="";
			
			String vnet_br=AppProperties.getVnet_br();
			String table_id=AppProperties.getVnet_flow_table_id();
			
			String POST_URL="http://" + compute_url + ":12345";
			//including 4 commands
			String content1="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-ofctl add-flow "
				+ vnet_br + " 'table=0, priority=10,in_port=" + inport_id + 
				",actions=resubmit(," + table_id + ")' \"}}";
			
	//		flowWrite.write(content1 + "\n");
			
			try {
		     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL, content1);
		 //    		resultWrite.write(result1 + "\n");
			} catch (IOException e) {
			    	e.printStackTrace();
			}
			
			String content2="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-ofctl add-flow "
				+ vnet_br + " 'table=0, priority=10,in_port=" + outport_id + 
				",actions=resubmit(," + table_id + ")' \"}}";
			
	//		flowWrite.write(content2 + "\n");
			try {
	     		result2 = Vnet_HttpRequest.rpcFromPost(POST_URL, content2);
	//     		resultWrite.write(result2 + "\n");
			} catch (IOException e) {
		    	e.printStackTrace();
			}		
			
			String content3="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-ofctl add-flow " 
				+ vnet_br + " 'table=" + table_id + ",in_port=" + inport_id +
				",actions=set_tunnel:" + tun_tag + ",output:" + outport_id + "' \"}}";
	//		flowWrite.write(content3 + "\n");
			try {
	     		result3 = Vnet_HttpRequest.rpcFromPost(POST_URL, content3);
	//     		resultWrite.write(result3 + "\n");
			} catch (IOException e) {
		    	e.printStackTrace();
			}
			
			String content4="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-ofctl add-flow " 
				+ vnet_br + " 'table=" + table_id + ",in_port=" + outport_id +
				",tun_id=" + tun_tag + ",actions=output:" + inport_id + "' \"}}";
			
	//		flowWrite.write(content4 + "\n");
			try {
	     		result4 = Vnet_HttpRequest.rpcFromPost(POST_URL, content4);
	   //  		resultWrite.write(result4 + "\n");
			} catch (IOException e) {
		    	e.printStackTrace();
			}
		
			return "";
	}




	@Override
	public String del_flow(String compute_url, String tun_tag, String inport_id, String outport_id) 
	{
	//	BufferedWriter flow_delWrite=null;
	//	BufferedWriter result_delWrite=null;
		
			// cause compute node's flow entry installation includes 4 entries, so need to conduct 4 flow operations
			
	//		flow_delWrite=new BufferedWriter(new FileWriter("D:/flowsdel.txt",true));
	//		result_delWrite=new BufferedWriter(new FileWriter("D:/resultsdel.txt",true));
			
			String result1="";
			String result2="";
			String result3="";
			
			String vnet_br=AppProperties.getVnet_br();
			String table_id=AppProperties.getVnet_flow_table_id();
			
			String POST_URL="http://" + compute_url + ":12345";
			//including 4 commands
			
			String con1="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-ofctl del-flows "
				+ vnet_br + " 'table=0,in_port=" + inport_id + "'\"}}";
	//		flow_delWrite.write(con1 + "\n");
			
			String con2="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-ofctl del-flows "
				+ vnet_br + " 'table=" + table_id +",in_port=" + inport_id + "'\"}}";
	//		flow_delWrite.write(con2 + "\n");
			
			String con3="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-ofctl del-flows "
				+ vnet_br + " 'table=" + table_id +",in_port=" + outport_id + ",tun_id=" + tun_tag + "'\"}}";
	//		flow_delWrite.write(con3 + "\n");
			
				
			try {
		     		result1 = Vnet_HttpRequest.rpcFromPost(POST_URL, con1);
	//	     		result_delWrite.write(result1+"\n");
			} catch (IOException e) {
			    	e.printStackTrace();
			}
			
			try {
	     		result2 = Vnet_HttpRequest.rpcFromPost(POST_URL, con2);
//	     		result_delWrite.write(result2+"\n");
			} catch (IOException e) {
		    	e.printStackTrace();
			}		
			try {
	     		result3 = Vnet_HttpRequest.rpcFromPost(POST_URL, con3);
//	     		result_delWrite.write(result3+"\n");
			} catch (IOException e) {
		    	e.printStackTrace();
			}
			

			return "";
	}




	@Override
	public String get_portnumber(String compute_url, String port_name) {
		// TODO Auto-generated method stub
		
	//	BufferedWriter writer=null;
		String GET_URL="http://" + compute_url + ":12345";
		String result="";
		

	//		writer=new BufferedWriter(new FileWriter("D:/ofport.txt",true));
			
			//including 1 command
			// ovs-vsctl get Interface vnet-tun(port-name) ofport 
			
			String content="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl get Interface "
				+ port_name + " ofport \"}}";
	//		writer.write(content+"\n");
			
			
			try {
	     		result = Vnet_HttpRequest.rpcFromPost(GET_URL, content);
	//     		writer.write(result + "\n");
	     		
			} catch (IOException e) {
		    	e.printStackTrace();
			}
		
			return result;
	}

}
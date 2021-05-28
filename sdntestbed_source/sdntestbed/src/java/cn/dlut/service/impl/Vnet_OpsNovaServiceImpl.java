package cn.dlut.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.*;


import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_instanceDao;
import cn.dlut.dao.Vnet_instance_controllerDao;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.service.Vnet_OpsNeutronService;
import cn.dlut.service.Vnet_OpsNovaService;
import cn.dlut.service.Vnet_ShowflavorService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;


@Service("Vnet_OpsNovaService")
public class Vnet_OpsNovaServiceImpl extends AbstractBaseService implements Vnet_OpsNovaService{
	@Autowired
	private Vnet_OpsIdentityService ide;
	@Autowired
	private Vnet_instanceDao instancedao;

	@Override
	public ArrayList<Map<String, String>> getSeriallist() {
		ArrayList<Map<String, String>> console_urlList1=new ArrayList<Map<String, String>>();
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
							+AppProperties.getVnet_tenant_demo()+"/servers";
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
			e.printStackTrace();
			if(result.length()==0){
				result = e.toString();
			}
		}
	
	    JSONObject json1=JSONObject.fromObject(result);
	    JSONArray servers=json1.getJSONArray("servers");
	    int size=servers.size();
	    for(int i=0;i<size;i++)
	     {
	    	 JSONObject temp=servers.getJSONObject(i);
	    	 String id=temp.getString("id");
	    	 String ins_id=instancedao.getInstanceidByosid(id);
	    	 String POST_URL="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
									+AppProperties.getVnet_tenant_demo()+"/servers/"+id+"/action";
	    	 String bodyUrl="{\"os-getSerialConsole\":{\"type\":\"serial\"}}";
		     String result1="";
		     
			 try {
		     		result1 = Vnet_HttpRequest.readContentFromPost(POST_URL, bodyUrl, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			 } catch (IOException e) {
			    	e.printStackTrace();
			 }
			 System.out.println(result1);
			 JSONObject jsona=JSONObject.fromObject(result1);
			 JSONObject jsonb=jsona.getJSONObject("console");
			 
			 String console_url=jsonb.getString("url");
			 Map<String, String> map1=new HashMap<String, String>();
			 map1.put("instance_id", ins_id);
			 map1.put("console_url", console_url);
			 console_urlList1.add(map1);
			 
	    }
	    return console_urlList1;
	
    }
	public ArrayList<Map<String, String>> getvnclist() {
		ArrayList<Map<String, String>> console_urlList2=new ArrayList<Map<String, String>>();
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
							+AppProperties.getVnet_tenant_demo()+"/servers";
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
			e.printStackTrace();
			if(result.length()==0){
				result = e.toString();
			}
		}
		
	    JSONObject json1=JSONObject.fromObject(result);
	    JSONArray servers=json1.getJSONArray("servers");
	    int size=servers.size();
	    for(int i=0;i<size;i++)
	     {
	    	 JSONObject temp=servers.getJSONObject(i);
	    	 String id=temp.getString("id");
	    	 String ins_id2=instancedao.getInstanceidByosid(id);
	    	 String POST_URL="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
	 							+AppProperties.getVnet_tenant_demo()+"/servers/"+id+"/action";
	    	 String bodyUrl="{\"os-getVNCConsole\":{\"type\":\"novnc\"}}";
		     String result1="";
		     
			 try {
		     		result1 = Vnet_HttpRequest.readContentFromPost(POST_URL, bodyUrl, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			 } catch (IOException e) {
			    	e.printStackTrace();
			 }
			 System.out.println(result1);
			 JSONObject jsona=JSONObject.fromObject(result1);
			 JSONObject jsonb=jsona.getJSONObject("console");
			 
			 String console_url=jsonb.getString("url");
			 Map<String, String> map2=new HashMap<String, String>();
			 map2.put("instance_id", ins_id2);
			 map2.put("console_url", console_url);
			 console_urlList2.add(map2);
	    }
	    
        return console_urlList2;
    }
	@Override
	public String getVncUrl(String sql_id) {
		Vnet_instance ins = instancedao.getById(sql_id);
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
    	String POST_URL="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
							+AppProperties.getVnet_tenant_demo()+"/servers/"+ins.getInstance_osid()+"/action";
    	String bodyUrl="{\"os-getVNCConsole\":{\"type\":\"novnc\"}}";
	    String result="";
	    String console_url="";
		try {
			result = Vnet_HttpRequest.readContentFromPost(POST_URL, bodyUrl, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
		   	e.printStackTrace();
		}
		if(!result.equals("")){
			JSONObject jsona=JSONObject.fromObject(result);
			JSONObject jsonb=jsona.getJSONObject("console");
			console_url=jsonb.getString("url");
		}
		//replace returned control node's url with tomcat server's ip
		if(AppProperties.getVnet_tomcat_server().equals("0")){
			return console_url;
		}
		return console_url.replaceFirst(AppProperties.getVnet_control_addr(),AppProperties.getVnet_tomcat_server());
	}
	@Override
	public String getSerialUrl(String sql_id) {
		Vnet_instance ins = instancedao.getById(sql_id);
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		String POST_URL="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
							+AppProperties.getVnet_tenant_demo()+"/servers/"+ins.getInstance_osid()+"/action";
		String bodyUrl="{\"os-getSerialConsole\":{\"type\":\"serial\"}}";
		String result="";
		try {
			result = Vnet_HttpRequest.readContentFromPost(POST_URL, bodyUrl, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
		    e.printStackTrace();
		}
		JSONObject jsona=JSONObject.fromObject(result);
		JSONObject jsonb=jsona.getJSONObject("console");
		String console_url=jsonb.getString("url");
	    return console_url;
	}
	
	@Override
	public String getSerialUrlByosid(String osid) {
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		String POST_URL="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
							+AppProperties.getVnet_tenant_demo()+"/servers/"+osid+"/action";
		String bodyUrl="{\"os-getSerialConsole\":{\"type\":\"serial\"}}";
		String result="";
		try {
			result = Vnet_HttpRequest.readContentFromPost(POST_URL, bodyUrl, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
		    e.printStackTrace();
		}
		JSONObject jsona=JSONObject.fromObject(result);
		JSONObject jsonb=jsona.getJSONObject("console");
		String console_url=jsonb.getString("url");
	    return console_url;
	}
}
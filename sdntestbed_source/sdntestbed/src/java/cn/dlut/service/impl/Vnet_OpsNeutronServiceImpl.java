package cn.dlut.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.*;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_flavorDao;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.service.Vnet_OpsNeutronService;
import cn.dlut.service.Vnet_ShowflavorService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;


@Service("Vnet_OpsNeutronService")
public class Vnet_OpsNeutronServiceImpl extends AbstractBaseService implements Vnet_OpsNeutronService{
	@Autowired
	private Vnet_OpsIdentityService ide;

	

	@Override
	public String ListSubnet() {//return subnet list
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":9696/v2.0/subnets";
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
		return result;
	}

	@Override
	public String ShowSubnet(String subnet_id) {//return detailed subnet list
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":9696/v2.0/subnets/"+subnet_id;
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}


	@Override
	public String ListPort() {//return port list
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":9696/v2.0/ports";
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
		JSONObject json1 = JSONObject.fromObject(result);
		JSONArray json2=json1.getJSONArray("ports");
		for(int i=0;i<json2.size();i++)
     		{
	    	 	JSONObject json3=JSONObject.fromObject(json2.get(i));
	    	 	JSONArray json4=json3.getJSONArray("fixed_ips");
		    	for(int j=0;j<json4.size();j++)
		    	 {
		    		 JSONObject json5=JSONObject.fromObject(json4.get(j));
		    		 result+=json5.getString("ip_address");
		    	 }
		     }
		     
		return result;
	}

	

	@Override
	public String ListFloatingip() {//return floatingip list
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":9696/v2.0/floatingips";
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(result.length()==0){
				result = e.toString();
			}
		}
		
		return result;
	}
	
	@Override
	public String ListUsedIp()
	{
		String GET_URL="http://"+AppProperties.getVnet_control_addr()+":9696/v2.0/ports";
		String result="";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try{
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		}catch(IOException e)
		{
			System.out.println("exception occured");
			e.printStackTrace();
			if(result.length()==0)
			{
				result=e.toString();
			}
		}
		//System.out.println("EveryThings' fine, return result!");
		return result;
	}
	
	
	@Override
	public String ListIp()
	{
		String GET_URL="http://"+AppProperties.getVnet_control_addr()+":9696/v2.0/subnets";
		String result="";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try{
//			result = Vnet_HttpRequest.readContentFromGet(GET_URL, "X-Auth-Token", "6dc9919cbb5049ae9f0d39b80a9c5ab0");
			result=Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(),Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		}catch(IOException e)
		{
			e.printStackTrace();
			if(result.length()==0)
			{
				result=e.toString();
			}
		}
		return result;
	}
	
//	@Override
//	public String getFlavorFromOps(){
//		String GET_URL="http://192.168.0.86:9292/v2.0/flavors";
//		String result="";
//		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
//		try{
//			result=Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(),Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
//		}catch(IOException e)
//		{
//			e.printStackTrace();
//			if(result.length()==0)
//			{
//				result=e.toString();
//			}
//		}
//		return result;
//	}
//
//	@Override
//	public String getInstanceFromOps() {
//		String GET_URL="http://192.168.0.86:9292/v2.0/instances";
//		String result="";
//		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
//		try{
//			result=Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(),Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
//		}catch(IOException e)
//		{
//			e.printStackTrace();
//			if(result.length()==0)
//			{
//				result=e.toString();
//			}
//		}
//		return result;
//	}
//
//	@Override
//	public String getImageFromOps() {
//		String GET_URL="http://192.168.0.86:9292/v2.0/images";
//		String result="";
//		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
//		try{
//			result=Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(),Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
//		}catch(IOException e)
//		{
//			e.printStackTrace();
//			if(result.length()==0)
//			{
//				result=e.toString();
//			}
//		}
//		return result;
//	}
}
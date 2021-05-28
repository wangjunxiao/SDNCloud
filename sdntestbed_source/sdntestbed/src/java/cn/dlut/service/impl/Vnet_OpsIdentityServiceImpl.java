package cn.dlut.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.bcel.internal.generic.NEW;


import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;
import cn.dlut.service.impl.Vnet_OpsHeatServiceImpl;


@Service("Vnet_OpsIdentityService")
@SuppressWarnings("all")
public class Vnet_OpsIdentityServiceImpl extends AbstractBaseService implements Vnet_OpsIdentityService {
	
	public static String HEADER_NAME = "X-Auth-Token";
	public static String HEADER_VALUE = "";
	
	
	public static String getHEADER_NAME() {
		return HEADER_NAME;
	}


	public static void setHEADER_NAME(String hEADER_NAME) {
		HEADER_NAME = hEADER_NAME;
	}

	public static String getHEADER_VALUE() {
		return HEADER_VALUE;
	}


	public static void setHEADER_VALUE(String hEADER_VALUE) {
		HEADER_VALUE = hEADER_VALUE;
	}

	
	public void CreateToken() {
		// TODO Auto-generated method stub
			 System.out.println("create a new token");
			 String POST_URL = "http://"+AppProperties.getVnet_control_addr()+":35357/v2.0/tokens";
			 String content = "{\"auth\":{\"tenantName\":\"demo\","
			 		+ "\"passwordCredentials\":{\"username\":\"demo\",\"password\":\"0000\"}}}"; 
			 String result = "";
			 try {
		     		result = Vnet_HttpRequest.readContentFromPost(POST_URL, content, "", "");
//		     		System.out.println(result);
			 } catch (IOException e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			 }
			 
		     JSONObject json1 = JSONObject.fromObject(result);
//		     System.out.println(json1);
		     JSONObject json2 = json1.getJSONObject("access");
		     System.out.println(json2);
		     JSONObject json3 = json2.getJSONObject("token");
//		     System.out.println(json3);
			 Vnet_OpsIdentityServiceImpl.setHEADER_VALUE(json3.getString("id"));
			 System.out.println("token: " + Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
	}
	
	public static void main(String args[]) {
		Vnet_OpsIdentityServiceImpl ide = new Vnet_OpsIdentityServiceImpl();
//		ide.ValidateToken("d3ac02bc5b484f0a2e75");
		ide.CreateToken();
//		System.out.println(HEADER_VALUE);

	}

	
	
	@Override
	public void ValidateToken(String head) {
		// TODO Auto-generated method stub
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":35357/v2.0/tokens/" + head;
		String result = "";
		
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, "X-Auth-Token", head);
			System.out.println(result);
		} catch (IOException e) {
//			e.printStackTrace();
			if((e.toString().indexOf("response code: 401"))!= -1){
				this.CreateToken();
			}
		}
		
	}
	
}



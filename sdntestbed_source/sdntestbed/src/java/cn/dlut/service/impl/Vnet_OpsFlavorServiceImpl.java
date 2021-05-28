package cn.dlut.service.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.service.Vnet_OpsFlavorService;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;

@Service("Vnet_OpsFlavorService")
public class Vnet_OpsFlavorServiceImpl extends AbstractBaseService implements Vnet_OpsFlavorService {
	
	@Autowired
	private Vnet_flavorDao flavordao ;	
	
	public Vnet_OpsFlavorServiceImpl(){
		
	}
	
	@Override
	public void updateFlavor(){
		
		 String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
		 					+AppProperties.getVnet_tenant_demo()+ "/flavors";
		 
		 String result = "";
		 
		 String token="e6f23bd7f27b4bc3b65041b3e93b5332";
		 try {
//			 	HttpURLConnection connection = "";
	     		result = Vnet_HttpRequest.readContentFromGet(GET_URL,"X-Auth-Token",token);
	     		System.out.println(result);
	     } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		 }
	     JSONObject json1=JSONObject.fromObject(result);
	     JSONArray flavors=json1.getJSONArray("flavors");
	     int size=flavors.size();
	     System.out.println(size);
	     for(int i=0;i<size;i++)
	     {
	    	 //System.out.print(i);
	    	 //System.out.println("iteration begin");
	    	 JSONObject temp=flavors.getJSONObject(i);
	    	 String id=temp.getString("id");
	    	 String flavor_URL="http://192.168.0.86:8774/v2/2379e521097a4f7986f8f7dde862d922/flavors/"+id;
	    	 try {
				String result_flavor=Vnet_HttpRequest.readContentFromGet(flavor_URL,"X-Auth-Token",token);				
				JSONObject flavor_whole=JSONObject.fromObject(result_flavor);
				JSONObject flavor=flavor_whole.getJSONObject("flavor");
				
				String flavor_name=flavor.getString("name");
				String flavor_osid=flavor.getString("id");
				System.out.println(flavor_name);
				System.out.println(flavor_osid);
				Vnet_flavor f=new Vnet_flavor(flavor_name);
				
				System.out.println(f.toString());
				
				flavordao.insertFlavor(f);
				
				//System.out.println("insert success");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("iteration end");
	    	 
	     }
	     
	}
	@Autowired
	private Vnet_OpsIdentityService ide;

	@Override
	public String ListFlavor() {
		String GET_URL = "http://192.168.0.86:8774/v2/2379e521097a4f7986f8f7dde862d922/flavors";
		String result = "";
//		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try {
//			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, "X-Auth-Token", "6b021085390b467387999215b7f2a98f");
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
	public String ShowFlavor(String flavor_id) {
		String GET_URL = "http://192.168.0.86:8774/v2/2379e521097a4f7986f8f7dde862d922/flavors/"+flavor_id;
		String result = "";
//		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try {
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, "X-Auth-Token", "6b021085390b467387999215b7f2a98f");
//			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}
}

package cn.dlut.service.impl;

import java.io.IOException;
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

import cn.dlut.service.impl.Vnet_OpsIdentityServiceImpl;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.service.Vnet_OpsImageService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;

import flex.messaging.services.messaging.ThrottleManager.ThrottleResult.Result;

@Service("Vnet_OpsImageService")
public class Vnet_OpsImageServiceImpl extends AbstractBaseService implements Vnet_OpsImageService {
	
	@Autowired
	private Vnet_OpsIdentityService ide;
	
	@Override
	public String ListImage() {
		// TODO Auto-generated method stub
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":9292/v2/images";				
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
	public String ShowImage(String image_id) {
		String GET_URL = "http://"+AppProperties.getVnet_control_addr()+":9292/v2/images/"+image_id;
		String result = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try {
//			result = Vnet_HttpRequest.readContentFromGet(GET_URL, "X-Auth-Token", "6b021085390b467387999215b7f2a98f");
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}
	
		
}
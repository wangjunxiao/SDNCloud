package cn.dlut.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_computeDao;
import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_requestDao;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.service.Vnet_ipSubtractService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;
import cn.dlut.entity.Vnet_compute;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_request;

import cn.dlut.entity.Vnet_ip;
import cn.dlut.util.AppProperties;

import cn.dlut.service.Vnet_OpsNeutronService;

import cn.dlut.service.Vnet_OpsComputeService;



@Service("Vnet_OpsComputeService")
  public class Vnet_OpsComputeServiceImpl extends AbstractBaseService  implements Vnet_OpsComputeService{
	
	@Autowired
	private Vnet_computeDao computedao;
	
	public static int Vnet_compute_num=AppProperties.getVnet_compute_num();
	
	public ArrayList<Vnet_compute> computelist=new ArrayList<Vnet_compute>();
	
/*	public ArrayList<Vnet_ip> iplist=new ArrayList<Vnet_ip>();
	public ArrayList<Vnet_ip> usediplist=new ArrayList<Vnet_ip>();
	public ArrayList<Vnet_floatingip> floatingiplist=new ArrayList<Vnet_floatingip>();
	
	//note that subnet_control and subnet_host are initialized with values at the first time fetched from ops
	public static String subnet_control=AppProperties.getVnet_subnet_control();
	public static String subnet_host=AppProperties.getVnet_subnet_host();
	
	public static String subnet_control_id="";
	public static String subnet_host_id="";
	*/
	//public static String tempend;
	
	//ArrayList<String> listA=new ArrayList<String>();
	//ArrayList<String> listB=new ArrayList<String>();

	@Override
	public void getCompute()
	{
		ArrayList<String> result=AppProperties.getVnet_details(Vnet_compute_num);
		int counter=0;
		for(int i=1;i<=Vnet_compute_num;i++)
		{
			String compute_name=result.get(counter);
			counter++;
			String compute_addr=result.get(counter);
			counter++;
			String compute_ram=result.get(counter);
			counter++;
			String compute_cpu=result.get(counter);
			counter++;
			Vnet_compute temp=new Vnet_compute(i,compute_name,compute_addr,compute_ram,compute_cpu);
			computelist.add(temp);
		}
	}
	
	@Override
	public void putComputeIntoDB() {
		for(int i=0;i<computelist.size();i++)
		{
			computedao.insertCompute(computelist.get(i));
		}
	    
	}

}
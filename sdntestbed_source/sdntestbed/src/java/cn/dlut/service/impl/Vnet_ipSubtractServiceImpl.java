package cn.dlut.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_requestDao;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.service.Vnet_ipSubtractService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_request;

import cn.dlut.entity.Vnet_ip;
import cn.dlut.util.AppProperties;

import cn.dlut.service.Vnet_OpsNeutronService;


  @Service("Vnet_ipSubtractService")
  public class Vnet_ipSubtractServiceImpl extends AbstractBaseService  implements Vnet_ipSubtractService{
	
	@Autowired
	private Vnet_ipDao ipdao;
	
	@Autowired
	private Vnet_floatingipDao floatingipdao;
	
    @Autowired
	private Vnet_OpsNeutronService neutron;
	
	public ArrayList<Vnet_ip> iplist=new ArrayList<Vnet_ip>();
	public ArrayList<Vnet_ip> usediplist=new ArrayList<Vnet_ip>();
	public ArrayList<Vnet_floatingip> floatingiplist=new ArrayList<Vnet_floatingip>();
	
	//note that subnet_control and subnet_host are initialized with values at the first time fetched from ops
	public static String subnet_control=AppProperties.getVnet_subnet_control();
	public static String subnet_host=AppProperties.getVnet_subnet_host();
	
	public static String subnet_control_id="";
	public static String subnet_host_id="";
	
	//public static String tempend;
	
	//ArrayList<String> listA=new ArrayList<String>();
	//ArrayList<String> listB=new ArrayList<String>();
	
	
	@Override
	public void putIpIntoDB()
	{
		for(int i=0;i<iplist.size();i++)
		{
			ipdao.insertIp(iplist.get(i));
		}	
	}
	


	@Override
	public void putFloatingipIntoDB()
	{
		for(int i=0;i<floatingiplist.size();i++)
			floatingipdao.insertFloatingip(floatingiplist.get(i));
	}
	
	@Override
	public void updateFloatingipIntoDB()
	{
		for(int i=0;i<floatingiplist.size();i++)
			floatingipdao.updateFloatingip(floatingiplist.get(i));
	}
	
	@Override
	public void updateUsedIpIntoDB()	
	{
		for(int i=0;i<usediplist.size();i++)
		{
			ipdao.updateIp(usediplist.get(i));
		}
	}
	
	@Override
	public void getFloatingipFromOps()
	{
		String result=neutron.ListFloatingip();
		
		JSONObject json1=JSONObject.fromObject(result);
		JSONArray json2=json1.getJSONArray("floatingips");
		for(int i=0;i<json2.size();i++)
		{
			JSONObject json3=JSONObject.fromObject(json2.get(i));
			String floating_ip_address=json3.getString("floating_ip_address");
			String status=json3.getString("status");
			
			Vnet_floatingip temp=new Vnet_floatingip(floating_ip_address,status);
			floatingiplist.add(temp);
		}
		
	}
	@Override
	public void getUsedIpFromOps()
	{
		String result=neutron.ListUsedIp();			
		//System.out.println(result);
		
		JSONObject json1=JSONObject.fromObject(result);
		
		
		JSONArray jsonarray=json1.getJSONArray("ports");
		for(int j=0;j<jsonarray.size();j++)
		{
			JSONObject jsondd=JSONObject.fromObject(jsonarray.get(j));			
			JSONArray json2=jsondd.getJSONArray("fixed_ips");
			for(int i=0;i<json2.size();i++)
			{
				JSONObject json3=JSONObject.fromObject(json2.get(i));
				String subnet_id=json3.getString("subnet_id");
				String temp_ip=json3.getString("ip_address");
				String subnet_name="";
				if(subnet_id.equals(subnet_control_id))
					subnet_name=subnet_control;
				else if(subnet_id.equals(subnet_host_id))
					subnet_name=subnet_host;
				else
					continue;
				Vnet_ip temp=new Vnet_ip(subnet_name,"used",temp_ip);
				
				//System.out.println(temp.toString());
				usediplist.add(temp);
			}
		}	
	}
	
	public void InitIp()
	{
		this.getIpFromOps();
	 
	
		List<Vnet_ip> sqlips = ipdao.getAll();
		System.out.println(sqlips.size());
		System.out.println(iplist.size());
		System.out.println(ipdao.getByAddr(iplist.get(0).getIp_addr()));
		for(int i=0; i<iplist.size();i++)
		{
		  if( ipdao.getByAddr(iplist.get(i).getIp_addr())==null)
		  {
			  ipdao.insertIp(iplist.get(i));
		  }
			  
		}
	}
	
	@Override
	public void getIpFromOps() {
		
		String result = neutron.ListIp();
		
		JSONObject json1 = JSONObject.fromObject(result);
	    JSONArray json2=json1.getJSONArray("subnets");
		for(int i=0;i<json2.size();i++)
		{
			JSONObject json3=JSONObject.fromObject(json2.get(i));
			String subnet_name=json3.getString("name");
			if(subnet_name.equals(subnet_control))
				subnet_control_id=json3.getString("id");
			else if(subnet_name.equals(subnet_host))
				subnet_host_id=json3.getString("id");				
			else
				continue;
			JSONArray json4=json3.getJSONArray("allocation_pools");						
			
			for(int j=0;j<json4.size();j++)
			{
				JSONObject json5=JSONObject.fromObject(json4.get(j));
				String start=json5.getString("start");
				String end=json5.getString("end");
				
				String[] starts=start.split("\\.");
				String[] ends=end.split("\\.");
				
				String head=starts[0]+"."+starts[1]+".";
				
				int start_c = 0,start_d=0,end_c=0,end_d=0;
				
				//c start
				try
				{
					start_c= Integer.parseInt(starts[2]);
				}catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
				
				try
				{
					start_d= Integer.parseInt(starts[3]);
				}catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
				
				//end
				try
				{
					end_c= Integer.parseInt(ends[2]);
				}catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
				
				try
				{
					end_d= Integer.parseInt(ends[3]);
				}catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
				
				
				for(int index_c=start_c;index_c<=end_c;index_c++)
				{
					int index_d_min=0;
					int index_d_max=255;
					if(index_c==start_c)
						index_d_min=start_d;
					if(index_c==end_c)
						index_d_max=end_d;
					for(int temp_c=index_d_min;temp_c<=index_d_max;temp_c++)
					{
						String temp_ip=head+index_c+"."+temp_c;
						Vnet_ip temp=new Vnet_ip(subnet_name,"unused",temp_ip);
						this.iplist.add(temp);
					}
				}
				
			}
			
	    }
	    
	}

}
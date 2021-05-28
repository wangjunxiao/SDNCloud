package cn.dlut.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dlut.dao.Vnet_computeDao;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_imageDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_tunDao;
import cn.dlut.dao.ibatis.Vnet_flavorDaoImpl;

import cn.dlut.entity.Vnet_compute;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_image;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_tun;
import cn.dlut.service.Vnet_InitFromOpsService;
import cn.dlut.service.Vnet_OpsIdentityService;
import cn.dlut.service.Vnet_OpsImageService;
import cn.dlut.service.Vnet_OpsNeutronService;
import cn.dlut.service.Vnet_ShowimageService;
import cn.dlut.service.Vnet_ipSubtractService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.Vnet_HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("Vnet_InitFromOpsService")
public class Vnet_InitFromOpsServiceImpl extends AbstractBaseService  implements Vnet_InitFromOpsService{
	@Autowired
	private Vnet_flavorDao Flavordao;
    @Autowired
	Vnet_OpsNeutronService neutron;
	public ArrayList<Vnet_flavor> opsflavors=new ArrayList<Vnet_flavor>();
	@Autowired
	private Vnet_OpsIdentityService ide;
	@Autowired
	private Vnet_imageDao imagedao;
	@Autowired
	private Vnet_ipDao ipdao;
	@Autowired
	private Vnet_floatingipDao floatingipdao;
	@Autowired
	private Vnet_computeDao computedao;
	@Autowired
	private Vnet_tunDao tundao;

	@Autowired
	private Vnet_OpsImageService image;
	
	@Autowired
	private Vnet_ipSubtractService ipSubtract;
	
    public static int Vnet_compute_num=AppProperties.getVnet_compute_num();
	public ArrayList<Vnet_compute> computelist=new ArrayList<Vnet_compute>();

	@Override
	public String InitFlavor() {       //get ops's flavor, compare with database, delete the redundant part in database, add the absent part
		String GET_URL="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
							+ AppProperties.getVnet_tenant_demo() + "/flavors";
		String result="";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());//get token	 
		try{                             
			result = Vnet_HttpRequest.readContentFromGet(GET_URL, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(),Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		}catch(IOException e)
		{
			e.printStackTrace();
			if(result.length()==0)
			{
				result=e.toString();
			}
		}
		JSONObject json1=JSONObject.fromObject(result);//resolve json, get flavor list
		JSONArray json2=json1.getJSONArray("flavors");
		List<Integer> idList = new ArrayList<Integer>(); 
		for(int i=0;i<json2.size();i++){
			JSONObject json3=JSONObject.fromObject(json2.get(i));
		    idList.add(json3.getInt("id"));
		}
		for(int j=0;j<idList.size();j++){
		    String GET_URL1="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
								+ AppProperties.getVnet_tenant_demo() + "/flavors/"+idList.get(j);
		    String result1="";
		    ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		    try{
			        result1=Vnet_HttpRequest.readContentFromGet(GET_URL1, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(),Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		       }catch(IOException e)
		    {
			    e.printStackTrace();
			    if(result1.length()==0)
			    {
				    result1=e.toString();
			    }
		    }
		       System.out.println(result1);//resolve json, get detailed flavor list
		       JSONObject jsona=JSONObject.fromObject(result1); 
		       JSONObject jsonb =jsona.getJSONObject("flavor");
		       int disk=jsonb.getInt("disk");
		       int ram=jsonb.getInt("ram");
		       int vcpus=jsonb.getInt("vcpus");
		       String name=jsonb.getString("name");
		       Vnet_flavor temp=new Vnet_flavor();
		       temp.setFlavor_id(idList.get(j));
		       temp.setFlavor_name(name);
		       temp.setFlavor_ram(ram);
		       temp.setFlavor_vcpus(vcpus);
		       temp.setFlavor_rootdisk(disk);
		       temp.setFlavor_osid(""+idList.get(j));
		       opsflavors.add(temp);       //add gotten flavor entity into list
		}
		      List<Vnet_flavor> sqlflavors=Flavordao.getAll();
		      List<Vnet_flavor> delflavors = new ArrayList<Vnet_flavor>();
		      List<Vnet_flavor> addflavors = new ArrayList<Vnet_flavor>();
		      int[] add = new int[opsflavors.size()]; 
			  int[] del = new int[sqlflavors.size()]; 

			    for (int i = 0; i < opsflavors.size(); i++) { //handshake scheme, find out the redundant and absent part, flag them
					for (int j = 0; j < sqlflavors.size(); j++) {
						if (opsflavors.get(i).getFlavor_osid().equals(sqlflavors.get(j).getFlavor_osid())) {
							add[i]++;
							del[j]++;
						}
					}
				}
			    for (int i = 0; i < add.length; i++) {
					if (add[i] == 0) {
						addflavors.add(opsflavors.get(i));
					}
				}
			    for (int i = 0; i < del.length; i++) {
					if (del[i] == 0) {
						delflavors.add(sqlflavors.get(i));
					}
				}
			    for(int i=0;i < addflavors.size();i++)
			    {
			    	if (addflavors.get(i) != null) {
			    		
				    	Flavordao.insertFlavor(addflavors.get(i));
					}
			    }
			    for(int i=0;i < delflavors.size();i++)
				{
			        if (delflavors.get(i) != null) {
			           Flavordao.delById(delflavors.get(i).getFlavor_id());
						}
				    }

	     return "success";
		}
	

	@Override
	public void InitImage() {   //ditto

		    
			String result = image.ListImage();	  //get openstack's database
			
		    JSONObject json1=JSONObject.fromObject(result);
		    JSONArray opsimages=json1.getJSONArray("images");
		    
		    List<Vnet_image> sqlimages = imagedao.getAll();  //get database
		    
		    JSONArray addimages = new JSONArray();
		    List<Vnet_image> delimages = new ArrayList<Vnet_image>();
		    
		    int[] add = new int[opsimages.size()]; 
		    int[] del = new int[sqlimages.size()]; 

		    for (int i = 0; i < opsimages.size(); i++) { 
				for (int j = 0; j < sqlimages.size(); j++) {
					if (opsimages.getJSONObject(i).getString("id").equals(sqlimages.get(j).getImage_osid())) {
						add[i]++;
						del[j]++;
					}
				}
			}
		    
		    for (int i = 0; i < add.length; i++) {
				if (add[i] == 0) {
					addimages.add(opsimages.getJSONObject(i));
				}
			}
		    
		    for (int i = 0; i < del.length; i++) {
				if (del[i] == 0) {
					delimages.add(sqlimages.get(i));
				}
			}
		    
	        for(int i=0;i < addimages.size();i++)
		    {
		    	if (addimages.getJSONObject(i) != null) {
		    		JSONObject temp=addimages.getJSONObject(i);
			    	Vnet_image im = new Vnet_image();
			    	im.setImage_osid(temp.getString("id"));
			    	im.setImage_name(temp.getString("name"));
			    	im.setImage_format(temp.getString("disk_format"));
			    	im.setImage_size(temp.getString("size"));
			    	
			    	this.imagedao.updateImage(im);
				}
		    }
		    
	        for(int i=0;i < delimages.size();i++)
		    {
	        	if (delimages.get(i) != null) {
	            	imagedao.delById(delimages.get(i).getImage_id());
				}
		    }
		}

		
	

	@Override
	public void InitInstance() {
		// TODO Auto-generated method stub
		
	}
	public void InitCompute(){
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
			Vnet_compute temp=new Vnet_compute(i, compute_name,compute_addr,compute_ram,compute_cpu);
			computelist.add(temp);
		}	
		
		    System.out.println(computelist.size());
			List<Vnet_compute> sqlcomputes = computedao.getAll(); 
			List<Vnet_compute> delcomputes = new ArrayList<Vnet_compute>();
		    List<Vnet_compute> addcomputes = new ArrayList<Vnet_compute>();
			
			int[] add = new int[computelist.size()]; 
		    int[] del = new int[sqlcomputes.size()]; 
		    
		    for (int i = 0; i < computelist.size(); i++) { //handshake scheme, find out the redundant and absent part, flag them
				for (int j = 0; j < sqlcomputes.size(); j++) {
					if (computelist.get(i).getCompute_name().equals(sqlcomputes.get(j).getCompute_name())) {
						add[i]++;
						del[j]++;
					}
				}
			}
		    for (int i = 0; i < add.length; i++) {
				if (add[i] == 0) {
					addcomputes.add(computelist.get(i));
				}
			}
		    for (int i = 0; i < del.length; i++) {
				if (del[i] == 0) {
					delcomputes.add(sqlcomputes.get(i));
				}
			}
		    for(int i=0;i < addcomputes.size();i++)
		    {
		    	if (addcomputes.get(i) != null) {
		    		
			    	computedao.insertCompute(addcomputes.get(i));
				}
		    }
		    for(int i=0;i < delcomputes.size();i++)
			{
		        if (delcomputes.get(i) != null) {
		           computedao.delById(delcomputes.get(i).getCompute_id());
					}
			    }

		    
			
		}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void InitFloatingIp()
	{
		 /********InitFloatingip***************/
		String GET_URL_floatingip = "http://" + AppProperties.getVnet_control_addr()
										+":9696/v2.0/floatingips";
		String resultfloatingip = "";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		try {
			resultfloatingip = Vnet_HttpRequest.readContentFromGet(GET_URL_floatingip, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(), Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(resultfloatingip.length()==0){
				resultfloatingip = e.toString();
			}
		}
 

	    ArrayList<Vnet_floatingip> floatingiplist=new ArrayList<Vnet_floatingip>();
		JSONObject jsonfloatingips=JSONObject.fromObject(resultfloatingip);			
		JSONArray jsonarray=jsonfloatingips.getJSONArray("floatingips");
		
		for(int j=0;j<jsonarray.size();j++)
		{
			System.out.println("the index is :" +  j);
			JSONObject jsona=JSONObject.fromObject(jsonarray.get(j));			
			String floatingip_addr=jsona.getString("floating_ip_address");
			String floatingip_status=jsona.getString("status");
			String floatingip_osid = jsona.getString("id");
			Vnet_floatingip temp=new Vnet_floatingip(floatingip_addr, floatingip_osid, floatingip_status);
		    floatingiplist.add(temp);
		    
		}
	
		List<Vnet_floatingip> sqlfloatingips=floatingipdao.getAll();
		
	    List<Vnet_floatingip> delfloatingips = new ArrayList<Vnet_floatingip>();
	    List<Vnet_floatingip> addfloatingips = new ArrayList<Vnet_floatingip>();
	    int[] add_floatingips = new int[floatingiplist.size()]; 
		int[] del_floatingips = new int[sqlfloatingips.size()]; 
		 for (int i = 0; i < floatingiplist.size(); i++) { //handshake scheme, find out the redundant and absent part, flag them
				for (int j = 0; j < sqlfloatingips.size(); j++) {
					if (floatingiplist.get(i).getFloatingip_addr().equals(sqlfloatingips.get(j).getFloatingip_addr())) {
						add_floatingips[i]++;
						del_floatingips[j]++;
					}
				}
			}
		    for (int i = 0; i < add_floatingips.length; i++) {
				if (add_floatingips[i] == 0) {
					addfloatingips.add( floatingiplist.get(i));
				}
			}
		    for (int i = 0; i < del_floatingips.length; i++) {
				if (del_floatingips[i] == 0) {
					delfloatingips.add(sqlfloatingips.get(i));
				}
			}
		    for(int i=0;i < addfloatingips.size();i++)
		    {
		    	if (addfloatingips.get(i) != null) {
		    		
		    		floatingipdao.insertFloatingip(addfloatingips.get(i));
				}
		    }
		    for(int i=0;i < delfloatingips.size();i++)
			{
		        if (delfloatingips.get(i) != null) {
		           floatingipdao.delById(delfloatingips.get(i).getFloatingip_id());
					}
			}
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void InitAll() {
    /**************InitFlavor******************/
    	String GET_URL_flavor="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
									+ AppProperties.getVnet_tenant_demo() + "/flavors";
		String resultflavor="";
		ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());//get token	 
		try{                             
			resultflavor = Vnet_HttpRequest.readContentFromGet(GET_URL_flavor, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(),Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		}catch(IOException e)
		{
			e.printStackTrace();
			if(resultflavor.length()==0)
			{
				resultflavor=e.toString();
			}
		}
		JSONObject jsonflavor1=JSONObject.fromObject(resultflavor);//resolve json, get flavor list
		JSONArray jsonflavor2=jsonflavor1.getJSONArray("flavors");
		List<Integer> flavoridList = new ArrayList<Integer>(); 
		for(int i=0;i<jsonflavor2.size();i++){
			JSONObject jsonflavor3=JSONObject.fromObject(jsonflavor2.get(i));
		    flavoridList.add(jsonflavor3.getInt("id"));
		}
		for(int j=0;j<flavoridList.size();j++){
		    String GET_URL_flavor1="http://"+AppProperties.getVnet_control_addr()+":8774/v2/"
		    							+ AppProperties.getVnet_tenant_demo() + "/flavors/"+flavoridList.get(j);
		    String resultflavor1="";
		    ide.ValidateToken(Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		    try{
			        resultflavor1=Vnet_HttpRequest.readContentFromGet(GET_URL_flavor1, Vnet_OpsIdentityServiceImpl.getHEADER_NAME(),Vnet_OpsIdentityServiceImpl.getHEADER_VALUE());
		       }catch(IOException e)
		    {
			    e.printStackTrace();
			    if(resultflavor1.length()==0)
			    {
				    resultflavor1=e.toString();
			    }
		    }
		       System.out.println(resultflavor1);//resolve json, get detailed flavor list
		       JSONObject jsonflavora=JSONObject.fromObject(resultflavor1); 
		       JSONObject jsonflavorb =jsonflavora.getJSONObject("flavor");
		       int disk=jsonflavorb.getInt("disk");
		       
		       int ram=jsonflavorb.getInt("ram");
		       int vcpus=jsonflavorb.getInt("vcpus");
		       String name=jsonflavorb.getString("name");
		       Vnet_flavor temp=new Vnet_flavor();
		       temp.setFlavor_id(flavoridList.get(j));
		       temp.setFlavor_name(name);
		       temp.setFlavor_ram(ram);
		       temp.setFlavor_vcpus(vcpus);
		       temp.setFlavor_rootdisk(disk);
		       temp.setFlavor_osid(""+flavoridList.get(j));
		       opsflavors.add(temp);       //add gotten flavor entity into list
		}
		      List<Vnet_flavor> sqlflavors=Flavordao.getAll();
		      List<Vnet_flavor> delflavors = new ArrayList<Vnet_flavor>();
		      List<Vnet_flavor> addflavors = new ArrayList<Vnet_flavor>();
		      int[] add_flavor = new int[opsflavors.size()]; 
			  int[] del_flavor = new int[sqlflavors.size()]; 

			    for (int i = 0; i < opsflavors.size(); i++) { //handshake scheme, find out the redundant and absent part, flag them
					for (int j = 0; j < sqlflavors.size(); j++) {
						if (opsflavors.get(i).getFlavor_osid().equals(sqlflavors.get(j).getFlavor_osid())) {
							add_flavor[i]++;
							del_flavor[j]++;
						}
					}
				}
			    for (int i = 0; i < add_flavor.length; i++) {
					if (add_flavor[i] == 0) {
						addflavors.add(opsflavors.get(i));
					}
				}
			    for (int i = 0; i < del_flavor.length; i++) {
					if (del_flavor[i] == 0) {
						delflavors.add(sqlflavors.get(i));
					}
				}
			    for(int i=0;i < addflavors.size();i++)
			    {
			    	if (addflavors.get(i) != null) {
			    		
				    	Flavordao.insertFlavor(addflavors.get(i));
					}
			    }
			    for(int i=0;i < delflavors.size();i++)
				{
			        if (delflavors.get(i) != null) {
			           Flavordao.delById(delflavors.get(i).getFlavor_id());
						}
				}

	
    /*************InitImage******************/
			    
				String resultimage = image.ListImage();	  //get openstack's database
				
			    JSONObject jsonimage1=JSONObject.fromObject(resultimage);
			    JSONArray opsimages=jsonimage1.getJSONArray("images");
			    
			    List<Vnet_image> sqlimages = imagedao.getAll();  //get database
			    
			    JSONArray addimages = new JSONArray();
			    List<Vnet_image> delimages = new ArrayList<Vnet_image>();
			    
			    int[] add_image = new int[opsimages.size()]; 
			    int[] del_image = new int[sqlimages.size()]; 

			    for (int i = 0; i < opsimages.size(); i++) { 
					for (int j = 0; j < sqlimages.size(); j++) {
						if (opsimages.getJSONObject(i).getString("id").equals(sqlimages.get(j).getImage_osid())) {
							add_image[i]++;
							del_image[j]++;
						}
					}
				}
			    
			    for (int i = 0; i < add_image.length; i++) {
					if (add_image[i] == 0) {
						addimages.add(opsimages.getJSONObject(i));
					}
				}
			    
			    for (int i = 0; i < del_image.length; i++) {
					if (del_image[i] == 0) {
						delimages.add(sqlimages.get(i));
					}
				}
			    
		        for(int i=0;i < addimages.size();i++)
			    {
			    	if (addimages.getJSONObject(i) != null) {
			    		JSONObject tempimage=addimages.getJSONObject(i);
				    	Vnet_image im = new Vnet_image();
				    	im.setImage_osid(tempimage.getString("id"));
				    	im.setImage_name(tempimage.getString("name"));
				    	im.setImage_format(tempimage.getString("disk_format"));
				    	im.setImage_size(tempimage.getString("size"));
				    	
				    	this.imagedao.updateImage(im);
					}
			    }
			    
		        for(int i=0;i < delimages.size();i++)
			    {
		        	if (delimages.get(i) != null) {
		            	imagedao.delById(delimages.get(i).getImage_id());
					}
			    }
			    
    /*************InitCompute******************/	
		        
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
				
				    System.out.println("####computelist####: " + computelist.size());
					List<Vnet_compute> sqlcomputes = computedao.getAll(); 
					List<Vnet_compute> delcomputes = new ArrayList<Vnet_compute>();
				    List<Vnet_compute> addcomputes = new ArrayList<Vnet_compute>();
					
					int[] add_compute = new int[computelist.size()]; 
				    int[] del_compute = new int[sqlcomputes.size()]; 
				    
				    for (int i = 0; i < computelist.size(); i++) { //handshake scheme, find out the redundant and absent part, flag them
						for (int j = 0; j < sqlcomputes.size(); j++) {
							if (computelist.get(i).getCompute_name().equals(sqlcomputes.get(j).getCompute_name())) {
								add_compute[i]++;
								del_compute[j]++;
							}
						}
					}
				    for (int i = 0; i < add_compute.length; i++) {
						if (add_compute[i] == 0) {
							addcomputes.add(computelist.get(i));
						}
					}
				    for (int i = 0; i < del_compute.length; i++) {
						if (del_compute[i] == 0) {
							delcomputes.add(sqlcomputes.get(i));
						}
					}
				    for(int i=0;i < addcomputes.size();i++)
				    {
				    	if (addcomputes.get(i) != null) {
				    		
					    	computedao.insertCompute(addcomputes.get(i));
						}
				    }
				    for(int i=0;i < delcomputes.size();i++)
					{
				        if (delcomputes.get(i) != null) {
				           computedao.delById(delcomputes.get(i).getCompute_id());
							}
					    }

	 /*************InitIp******************/			    	        
		       ipSubtract.InitIp();
     /*************InitTun*******************/
		    int Vnet_tun_from=AppProperties.Vnet_tun_from();
			int Vnet_tun_to=AppProperties.Vnet_tun_to();
			List<Vnet_tun> sqltuns = tundao.getAll();
		    List<Vnet_tun> tuns = new ArrayList<Vnet_tun>();
		    List<Vnet_tun> deltuns = new ArrayList<Vnet_tun>();
		    List<Vnet_tun> addtuns = new ArrayList<Vnet_tun>();

			for(int i=Vnet_tun_from;i<=Vnet_tun_to;i++)
			{
				
				String tun_tag="0x" + Integer.toHexString(i);
				String tun_status="unused";
				Vnet_tun temp=new Vnet_tun(tun_tag,tun_status);
				tuns.add(temp);
			}
		    int[] add_tun= new int[tuns.size()]; 
			int[] del_tun = new int[sqltuns.size()]; 
//			System.out.println(tuns.size()+"kkkkkkkkkkkkk");
			
			  for (int i = 0; i <tuns.size(); i++) { //handshake scheme, find out the redundant and absent part, flag them
					for (int j = 0; j < sqltuns.size(); j++) {
						if (tuns.get(i).getTun_tag().equals(sqltuns.get(j).getTun_tag())) {
							add_tun[i]++;
							del_tun[j]++;
						}
					}
				}
			  {	  
//			  System.out.println(add_tun.length+"cscscscscscs");
			  }
			    for (int i = 0; i < add_tun.length; i++) {
					if (add_tun[i] == 0) {
						addtuns.add(tuns.get(i));
					}
				}
			    for(int i=0;i < addtuns.size();i++)
			    {
			    	if (addtuns.get(i) != null) {
			    		
				    	tundao.insertTun(addtuns.get(i));
					}
			    }
			  //update floatingip
			  InitFloatingIp();
   
		}
	
}
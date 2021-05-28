package cn.dlut.vnetlab.test.dao;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import cn.dlut.BaseTest;

import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.util.Vnet_HttpRequest;



public class Vnet_flavorDaoImplTest extends BaseTest {

	private Vnet_flavorDao dao;
	
	protected void setUp() throws Exception{
		super.setUp();
		dao=( Vnet_flavorDao ) this.ctx.getBean("Vnet_flavorDao");
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		dao=null;
	}
//	@Test
//	public void testDel()
//	{
//		int den=1;
//		dao.delById(den);
//	}
	
/*	
	@Test
	public void testGetAll() {
		List<Vnet_flavor> flavors=dao.getAll();
		for(Vnet_flavor flavor:flavors)
		{
			System.out.println(flavor.toString());
		}

		
	}*/
	
/*	@Test
	public void testGetById() {
		int iden=1;
		Vnet_flavor test1=dao.getById(iden);
		System.out.println(test1.toString());
	}*/

	/*@Test
	public void testInsertVnet_flavor() {
		
		String GET_URL = "http://192.168.0.86:8774/v2/2379e521097a4f7986f8f7dde862d922/flavors";
		 
		 String result = "";
		 
		 String token="4568de374f914be0be2b8b54d923eade";
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
	    	 System.out.print(i);
	    	 System.out.println("iteration start");
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
				
				dao.insertFlavor(f);
				
				System.out.println("insert success");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("iteration end");
	    	
	     }
		

	}*/

}

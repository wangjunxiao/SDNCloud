package cn.dlut.service.impl;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_imageDao;

import cn.dlut.entity.Vnet_image;
import cn.dlut.service.Vnet_OpsImageService;
import cn.dlut.service.Vnet_UpdateimageService;


@Service("Vnet_UpdateimageService")
public class Vnet_UpdateimageServiceImpl extends AbstractBaseService  implements Vnet_UpdateimageService{
	
	@Autowired
	private Vnet_imageDao imagedao;
	
	@Autowired
	private Vnet_OpsImageService image;
	
	@Override
	public void updateImage() {
		String result = image.ListImage();	  //get openstack database
		
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
}
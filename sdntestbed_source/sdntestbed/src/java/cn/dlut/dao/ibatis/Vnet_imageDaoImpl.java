/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_image;
import cn.dlut.dao.Vnet_imageDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_imageDao")
public class Vnet_imageDaoImpl extends IBatisEntityDao<Vnet_image> implements Vnet_imageDao {
	static Logger logger = Logger.getLogger(Vnet_imageDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_image> getAll() {
		return (List<Vnet_image>) getSqlMapClientTemplate().queryForList(
				"Vnet_image.getAll");
	}

	@Override
	public int updateImage(Vnet_image image) {
		getSqlMapClientTemplate().insert("Vnet_image.updateImage", image);
		return 0;
	}

	@Override
	public int delById(Integer id) {
		return getSqlMapClientTemplate().delete("Vnet_image.delById", id);
	}

	@Override
	public String getOsid(Integer id) {
		// TODO Auto-generated method stub
		Vnet_image temp=(Vnet_image)getSqlMapClientTemplate().queryForObject("Vnet_image.getOsid", id);
		return temp.getImage_osid();
		
	}

	@Override
	public Vnet_image getById(Integer image_id) {
		// TODO Auto-generated method stub
		return (Vnet_image)getSqlMapClientTemplate().queryForObject("Vnet_image.getById", image_id);
	}
	
	@Override	
	public String getimage_name(Integer id){
		Vnet_image temp=(Vnet_image)getSqlMapClientTemplate().queryForObject("Vnet_image.getimage_name",id);
		return temp.getImage_name();
	}
}

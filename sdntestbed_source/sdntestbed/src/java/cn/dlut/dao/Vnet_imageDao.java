/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_image;

public interface Vnet_imageDao extends IBaseDao {

	public List<Vnet_image> getAll();
	public int updateImage(Vnet_image image);
	public int delById(Integer id);
	public String getOsid(Integer id);
	public Vnet_image getById(Integer image_id);
	public String getimage_name(Integer id);
}

/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

public interface Vnet_image_instanceDao extends IBaseDao {

	public List<Vnet_image_instance> getAll();
	public Vnet_image_instance getById(int id);
	public int insertImageInstance(Vnet_image_instance ii);
	public int deleleImageInstance(int id);
	public List<Vnet_image_instance> getAllActive(int exe_id);
}

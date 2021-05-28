/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_compute_instance;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

public interface Vnet_compute_instanceDao extends IBaseDao {

	public List<Vnet_compute_instance> getAll();
	public Vnet_compute_instance getById(int id);
	public int insertCompute_instance(Vnet_compute_instance ii);
	public int delById(int id);

}

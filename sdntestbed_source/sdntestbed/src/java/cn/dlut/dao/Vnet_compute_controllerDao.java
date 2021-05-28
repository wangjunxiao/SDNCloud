/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_compute_controller;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_compute;

public interface Vnet_compute_controllerDao extends IBaseDao {

	public List<Vnet_compute_controller> getAll();
	public Vnet_compute_controller getById(int id);
	public int insertCompute_controller(Vnet_compute_controller ii);
	public int delById(int id);
	
	public Vnet_compute_controller getByControllerId(String controller_id);
	

}

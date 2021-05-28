/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_instance_controller;
import cn.dlut.entity.Vnet_ip;


public interface Vnet_instance_controllerDao extends IBaseDao {

	public List<Vnet_instance_controller> getAll();
	
	public Vnet_instance_controller getById(int id);
	
	public int insertInstance_controller(Vnet_instance_controller i);
	
	public int delById(int id);
	
	public String getInstance_id(String controller_id);
	
	public String getController_id(String instance_id);
	
	public List<Vnet_instance_controller> getAllActive(int exe_id);
	
}

/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_instance_controller;
import cn.dlut.entity.Vnet_instance_host;
import cn.dlut.entity.Vnet_ip;


public interface Vnet_instance_hostDao extends IBaseDao {

	public List<Vnet_instance_host> getAll();
	
	public Vnet_instance_host getById(int id);
	
	public int insertInstance_host(Vnet_instance_host ih);
	
	public int delById(int id);
	
	public Vnet_instance_host getByInstance_id(String instance_id);
	
	public Vnet_instance_host getByHost_id(String host_id);
	
	public String getInstance_id(String host_id);
	
	public List<Vnet_instance_host> getAllActive(int exe_id);
	
}

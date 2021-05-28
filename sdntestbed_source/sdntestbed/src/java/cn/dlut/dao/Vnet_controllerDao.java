/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_vnet;


public interface Vnet_controllerDao extends IBaseDao {

	public List<Vnet_controller> getAll();
	
	public Vnet_controller getById(String id);
	
	public List<Vnet_controller> getAllbyStatus(int exe_id);
	
	public int insertController(Vnet_controller c);
	
	public int delById(String id);
	
	public void updateController(Vnet_controller con);
	
	
}

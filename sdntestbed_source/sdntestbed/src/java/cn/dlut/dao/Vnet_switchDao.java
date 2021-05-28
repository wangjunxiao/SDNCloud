/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_switch;


public interface Vnet_switchDao extends IBaseDao {

	public List<Vnet_switch> getAll();
	
	public Vnet_switch getById(String id);
	
	public int insertSwitch(Vnet_switch i);
	
	public int delById(String id);
	
	public int getSwitch_counter(String id);
	
	public int updateSwitch(Vnet_switch ss);
	
	public List<Vnet_switch> getAllbyStatus(int exe_id);
	
}

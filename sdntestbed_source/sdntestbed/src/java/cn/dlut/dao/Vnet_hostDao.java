/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_host;


public interface Vnet_hostDao extends IBaseDao {

	public List<Vnet_host> getAll();
	
	public Vnet_host getById(String id);
	
	public List<Vnet_host> getAllbyStatus(int exe_id);
	
	public int insertHost(Vnet_host h);
	
	public void updateHost(Vnet_host con);
	
	public int delById(String id);
	
}

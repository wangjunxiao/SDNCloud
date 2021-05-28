/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_vnet;


public interface Vnet_vnetDao extends IBaseDao {

	public List<Vnet_vnet> getAll();
	
	public Vnet_vnet getById(String id);
	
	public int insertVnet(Vnet_vnet v);
	
	public int delById(String id);
	
	public int updateVnet(Vnet_vnet temp_vnet);
	
}

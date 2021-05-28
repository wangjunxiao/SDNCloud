/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_flavor_instance;
import cn.dlut.entity.Vnet_instance;


public interface Vnet_flavor_instanceDao extends IBaseDao {

	public List<Vnet_flavor_instance> getAll();
	
	public List<Vnet_flavor_instance> getAllActive(int exe_id);
	
	public Vnet_flavor_instance getById(int id);
	
	public int insertFlavorInstance(Vnet_flavor_instance i);
	
	public int delById(int id);
	
}

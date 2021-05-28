/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.entity.Vnet_oflink;

public interface Vnet_instanceDao extends IBaseDao {

	public List<Vnet_instance> getAll();
	public List<Vnet_instance> getAllbyStatus(int exe_id);
	
	public int insertInstance(Vnet_instance ii);
	
//	public int updateInstance(Vnet_instance ii);
	
	public int updateInstance(Vnet_instance ii);

	public int delById(int id);
	
	public String getInstanceidByosid(String osid);
	
	public Vnet_instance getById(String id);
	
	public Vnet_instance getByHostId(String host_id);
	
	public Vnet_instance getByStackId(Integer stack_id);
	
	public Vnet_instance getByConId(String con_id);
	
}

/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_instance_osport;

public interface Vnet_instance_osportDao extends IBaseDao {

	public List<Vnet_instance_osport> getAll();
	
	public Vnet_instance_osport getById(int id);
	
	public int insertInstanceOsport(Vnet_instance_osport ii);
	
	public int delById(int id);
	
	public String getOsport_id(String instance_id);
	
	public String getOsport_id_by_hostid(String host_id);
	
	

}

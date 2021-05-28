/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_osport;


public interface Vnet_osportDao extends IBaseDao {

	public List<Vnet_osport> getAll();

	public Vnet_osport getById(String id);
	
	public int insertOsport(Vnet_osport f);
	
	public int delById(String id);
	
	public int updateOsport(Vnet_osport osport);
	
	public int updateTypeIP(Vnet_osport osport);
	
	public String getOsportOsid_by_host_id(String host_id);
	
	public Vnet_osport getbyHostid(String host_id);
	
	public Vnet_osport getbyOsid(String osid);
	
	public Vnet_osport getbyConid(String con_id);

}

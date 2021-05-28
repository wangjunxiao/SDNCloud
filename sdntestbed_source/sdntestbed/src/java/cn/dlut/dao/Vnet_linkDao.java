/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_link;
import cn.dlut.entity.Vnet_osport;


public interface Vnet_linkDao extends IBaseDao {

	public List<Vnet_link> getAll();
	
	public List<Vnet_link> getAllbyStatus(int exe_id);

	public Vnet_link getById(String id);
	
	public int insertLink(Vnet_link f);
	
	public int delById(String id);
	
	public int updateLink(Vnet_link l);
	
}

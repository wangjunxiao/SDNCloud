/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_oflink;


public interface Vnet_oflinkDao extends IBaseDao {

	public List<Vnet_oflink> getAll();
	public List<Vnet_oflink> getAllbyStatus(int exe_id);
	
	public Vnet_oflink getById(String id);
	
	public int insertOflink(Vnet_oflink cs);
	
	public int delById(String id);
	
	public int updateOflink(Vnet_oflink oflink);
	
}

/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_oflink;
import cn.dlut.entity.Vnet_ofport;


public interface Vnet_ofportDao extends IBaseDao {

	public List<Vnet_ofport> getAll();
	
	public List<Vnet_ofport> getAllbyStatus();

	public Vnet_ofport getById(String id);
	
	public int insertOfport(Vnet_ofport f);
	
	public int delById(String id);
	
	public int updateOfport(Vnet_ofport o);
	
	public List<Vnet_ofport> getAllByVnet(String vnet_id);
	
}

/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_tunofport;
import cn.dlut.entity.Vnet_vnet;


public interface Vnet_tunofportDao extends IBaseDao {

	public List<Vnet_tunofport> getAll();
	
	public Vnet_tunofport getById(String id);
	
	public int insertTunofport(Vnet_tunofport v);
	
	public int delById(String id);
	
	public int updatePortNum(Vnet_tunofport temp);
	
}

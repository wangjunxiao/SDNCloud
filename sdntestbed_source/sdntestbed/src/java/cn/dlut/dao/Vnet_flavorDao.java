/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;


public interface Vnet_flavorDao extends IBaseDao {

	public List<Vnet_flavor> getAll();
	
	public Vnet_flavor getById(Integer id);
	
	public int insertFlavor(Vnet_flavor f);
	
	public int delById(Integer id);
	
	public String getOsid(Integer id);
	

	public void delAll();

}

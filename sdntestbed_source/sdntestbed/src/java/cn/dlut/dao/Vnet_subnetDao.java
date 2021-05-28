/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.entity.Vnet_subnet;


public interface Vnet_subnetDao extends IBaseDao {

	public List<Vnet_subnet> getAll();
	
	public Vnet_subnet getById(String usersubnet_id);
	
	public int insertUsersubnet(Vnet_subnet v);
	
	public int delById(String usersubnet_id);
	
	public int updateSubnet(Vnet_subnet ii);
}

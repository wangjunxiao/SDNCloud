/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_osport;
import cn.dlut.entity.Vnet_stack;


public interface Vnet_stackDao extends IBaseDao {

	public List<Vnet_stack> getAll();

	public Vnet_stack getById(Integer id);
	
	public int insertStack(Vnet_stack f);
	
	public int updateStack(Vnet_stack s);
	
	public int delById(String id);
	
	public int  insertAndGetId(Vnet_stack ss);
	
	public Vnet_stack getByHostId(String host_id);
	
	public Vnet_stack getByConId(String con_id);
}

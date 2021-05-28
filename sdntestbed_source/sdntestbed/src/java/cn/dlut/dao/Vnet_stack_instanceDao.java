/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_osport;
import cn.dlut.entity.Vnet_stack;
import cn.dlut.entity.Vnet_stack_instance;


public interface Vnet_stack_instanceDao extends IBaseDao {

	public List<Vnet_stack_instance> getAll();

	public Vnet_stack_instance getById(int id);
	
	public int insertStack_instance(Vnet_stack_instance f);
	
	public int delById(int id);
	
	public Vnet_stack_instance getByStackId(int stack_id);
	
	public Vnet_stack_instance getByInstanceId(String instance_id);
	
}

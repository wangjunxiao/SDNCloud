/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import java.util.Map;

import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_executor;


public interface Vnet_executorDao extends IBaseDao {

	public List<Vnet_executor> getAll();
	
	public Vnet_executor getById(Integer id);
	
	public int insertExecutor(Vnet_executor h);
	
	public int delById(Integer id);
	
	public Vnet_executor getByNameAndPassword(Map<String, String> nameAndPassword);
	
}

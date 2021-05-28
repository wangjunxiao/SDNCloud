/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_instance;
import cn.dlut.dao.Vnet_instanceDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_instanceDao")
public class Vnet_instanceDaoImpl extends IBatisEntityDao<Vnet_instance> implements Vnet_instanceDao {
	static Logger logger = Logger.getLogger(Vnet_instanceDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_instance> getAll() {
		return (List<Vnet_instance>) getSqlMapClientTemplate().queryForList(
				"Vnet_instance.getAll");
	}
	
	@Override
	public int delById(int id)
	{
		getSqlMapClientTemplate().delete("Vnet_instance.delById",id);
		return 0;
	}
	
	@Override
	public int updateInstance(Vnet_instance ii)
	{
		getSqlMapClientTemplate().update("Vnet_instance.updateInstance",ii);
		return 0;
	}
	
	@Override
	public int insertInstance(Vnet_instance ii)
	{
		getSqlMapClientTemplate().insert("Vnet_instance.insertInstance",ii);
		return 0;
	}

	@Override
	public String getInstanceidByosid(String osid) {
		return (String) getSqlMapClientTemplate().queryForObject("Vnet_instance.getInstanceidByosid", osid);
		
	}

	@Override
	public Vnet_instance getById(String id) {
		// TODO Auto-generated method stub
		
		return  (Vnet_instance)getSqlMapClientTemplate().queryForObject("Vnet_instance.getById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_instance> getAllbyStatus(int exe_id) {
		return (List<Vnet_instance>) getSqlMapClientTemplate().queryForList(
		"Vnet_instance.getAllbyStatus", exe_id);
	}

	@Override
	public Vnet_instance getByHostId(String host_id) {
		// TODO Auto-generated method stub
		return (Vnet_instance)getSqlMapClientTemplate().queryForObject("Vnet_instance.getByHostId", host_id);
	}

	@Override
	public Vnet_instance getByStackId(Integer stack_id) {
		// TODO Auto-generated method stub
		return (Vnet_instance)getSqlMapClientTemplate().queryForObject("Vnet_instance.getByStackId", stack_id);
	}

	@Override
	public Vnet_instance getByConId(String con_id) {
		// TODO Auto-generated method stub
		return (Vnet_instance)getSqlMapClientTemplate().queryForObject("Vnet_instance.getByConId", con_id);
	}
	
}

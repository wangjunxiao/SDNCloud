/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_osport;
import cn.dlut.entity.Vnet_stack;
import cn.dlut.entity.Vnet_stack_instance;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_osportDao;
import cn.dlut.dao.Vnet_stackDao;
import cn.dlut.dao.Vnet_stack_instanceDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_stack_instanceDao")
public class Vnet_stack_instanceDaoImpl extends IBatisEntityDao<Vnet_stack_instance> implements Vnet_stack_instanceDao {
	static Logger logger = Logger.getLogger(Vnet_stack_instanceDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_stack_instance> getAll() {
		return (List<Vnet_stack_instance>) getSqlMapClientTemplate().queryForList(
				"Vnet_stack_instance.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_stack_instance getById(int id) {
		return (Vnet_stack_instance) getSqlMapClientTemplate().queryForObject(
				"Vnet_stack_instance.getById", id);
	}

	public int insertStack_instance(Vnet_stack_instance f) {
		getSqlMapClientTemplate().insert("Vnet_stack_instance.insertStack_instance", f);
		return 0;
	}
	
	public int delById(int id)
	{
		getSqlMapClientTemplate().delete("Vnet_stack_instance.delById", id);
		return 0;
	}

	@Override
	public Vnet_stack_instance getByStackId(int stack_id) {
		// TODO Auto-generated method stub
		
		return (Vnet_stack_instance)getSqlMapClientTemplate().queryForObject("Vnet_stack_instance.getByStackId",stack_id);
	}

	@Override
	public Vnet_stack_instance getByInstanceId(String instance_id) {
		// TODO Auto-generated method stub
		return (Vnet_stack_instance)getSqlMapClientTemplate().queryForObject("Vnet_stack_instance.getByInstanceId",instance_id);
	}
	
}

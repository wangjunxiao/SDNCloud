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
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_osportDao;
import cn.dlut.dao.Vnet_stackDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_stackDao")
public class Vnet_stackDaoImpl extends IBatisEntityDao<Vnet_stack> implements Vnet_stackDao {
	static Logger logger = Logger.getLogger(Vnet_stackDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_stack> getAll() {
		return (List<Vnet_stack>) getSqlMapClientTemplate().queryForList(
				"Vnet_stack.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_stack getById(Integer id) {
		return (Vnet_stack) getSqlMapClientTemplate().queryForObject(
				"Vnet_stack.getById", id);
	}

	public int insertStack(Vnet_stack f) {
		getSqlMapClientTemplate().insert("Vnet_stack.insertStack", f);
		return 0;
	}
	
	public int delById(String id)
	{
		getSqlMapClientTemplate().delete("Vnet_stack.delById", id);
		return 0;
	}

	@Override
	public int insertAndGetId(Vnet_stack ss) {
		// TODO Auto-generated method stub
		return Integer.parseInt(String.valueOf(getSqlMapClientTemplate().insert("Vnet_stack.insertAndGetId",ss)));
		
	}

	@Override
	public int updateStack(Vnet_stack s) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_stack.updateStack",s);
		return 0;
	}

	@Override
	public Vnet_stack getByHostId(String host_id) {
		// TODO Auto-generated method stub
		return (Vnet_stack) getSqlMapClientTemplate().queryForObject(
				"Vnet_stack.getByHostId", host_id);
	}

	@Override
	public Vnet_stack getByConId(String con_id) {
		// TODO Auto-generated method stub
		return (Vnet_stack) getSqlMapClientTemplate().queryForObject(
				"Vnet_stack.getByConId", con_id);
	}
	
}

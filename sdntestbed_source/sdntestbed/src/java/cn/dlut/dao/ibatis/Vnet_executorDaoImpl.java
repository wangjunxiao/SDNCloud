/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_executor;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.dao.Vnet_executorDao;
import cn.dlut.dao.Vnet_flavorDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_executorDao")
public class Vnet_executorDaoImpl extends IBatisEntityDao<Vnet_executor> implements Vnet_executorDao {
	static Logger logger = Logger.getLogger(Vnet_executorDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_executor> getAll() {
		return (List<Vnet_executor>) getSqlMapClientTemplate().queryForList(
				"Vnet_executor.getAll");
	}
	
//	@SuppressWarnings("unchecked")
//	public Vnet_executor getById(String id) {
//		return (Vnet_executor) getSqlMapClientTemplate().queryForObject(
//				"Vnet_executor.getById", id);
//	}
//
//	public int delById(String id)
//	{
//		getSqlMapClientTemplate().delete("Vnet_executor.delById", id);
//		return 0;
//	}

	@Override
	public int insertExecutor(Vnet_executor h) {
		getSqlMapClientTemplate().insert("Vnet_executor.insertExecutor", h);
		return 0;
	}

	@Override
	public Vnet_executor getByNameAndPassword(Map<String, String> nameAndPassword) {
		return (Vnet_executor) getSqlMapClientTemplate().
			queryForObject("Vnet_executor.getByNameAndPassword", nameAndPassword);
	}

	public Vnet_executor getById(Integer id) {
		return (Vnet_executor) getSqlMapClientTemplate().queryForObject(
			"Vnet_executor.getById", id);
	}

	public int delById(Integer id) {
		getSqlMapClientTemplate().delete("Vnet_executor.delById", id);
		return 0;
	}
}

/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_flavor_instanceDao;
import cn.dlut.entity.Vnet_flavor_instance;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_flavor_instanceDao")
public class Vnet_flavor_instanceDaoImpl extends IBatisEntityDao<Vnet_flavor_instance> implements Vnet_flavor_instanceDao {
	static Logger logger = Logger.getLogger(Vnet_flavor_instanceDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_flavor_instance> getAll() {
		return (List<Vnet_flavor_instance>) getSqlMapClientTemplate().queryForList(
				"Vnet_flavor_instance.getAll");
	}

	@Override
	public Vnet_flavor_instance getById(int id) {
		return (Vnet_flavor_instance) getSqlMapClientTemplate().queryForObject(
				"Vnet_flavor_instance.getById", id);
	}
	@Override
	public int insertFlavorInstance(Vnet_flavor_instance ii)
	{
		getSqlMapClientTemplate().insert("Vnet_flavor_instance.insertFlavorInstance",ii);
		return 0;
	}

	@Override
	public int delById(int id)
	{
		return getSqlMapClientTemplate().delete("Vnet_flavor_instance.delById",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_flavor_instance> getAllActive(int exe_id) {
		return (List<Vnet_flavor_instance>) getSqlMapClientTemplate().queryForList(
		"Vnet_flavor_instance.getAllActive", exe_id);
	}


}

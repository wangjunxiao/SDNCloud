/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_compute_instanceDao;
import cn.dlut.dao.Vnet_image_instanceDao;
import cn.dlut.entity.Vnet_compute_instance;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_compute_instanceDao")
public class Vnet_compute_instanceDaoImpl extends IBatisEntityDao<Vnet_compute_instance> implements Vnet_compute_instanceDao {
	static Logger logger = Logger.getLogger(Vnet_compute_instanceDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_compute_instance> getAll() {
		return (List<Vnet_compute_instance>) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_instance.getAll");
	}

	@Override
	public Vnet_compute_instance getById(int id) {
		return (Vnet_compute_instance) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_instance.getById", id);
	}

	@Override
	public int insertCompute_instance(Vnet_compute_instance ii) {
		getSqlMapClientTemplate().insert("Vnet_compute_instance.insertCompute_instance", ii);
		return 0;
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Vnet_compute_instance.delById", id);
	}

}

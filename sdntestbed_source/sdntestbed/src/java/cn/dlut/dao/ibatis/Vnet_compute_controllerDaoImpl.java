/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_compute_controllerDao;
import cn.dlut.entity.Vnet_compute_controller;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_compute;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_compute_controllerDao")
public class Vnet_compute_controllerDaoImpl extends IBatisEntityDao<Vnet_compute_controller> implements Vnet_compute_controllerDao {
	static Logger logger = Logger.getLogger(Vnet_compute_controllerDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_compute_controller> getAll() {
		return (List<Vnet_compute_controller>) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_controller.getAll");
	}

	@Override
	public Vnet_compute_controller getById(int id) {
		return (Vnet_compute_controller) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_controller.getById", id);
	}

	@Override
	public int insertCompute_controller(Vnet_compute_controller ii) {
		getSqlMapClientTemplate().insert("Vnet_compute_controller.insertCompute_controller", ii);
		return 0;
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Vnet_compute_controller.delById", id);
	}

	@Override
	public Vnet_compute_controller getByControllerId(String controller_id) {
		// TODO Auto-generated method stub
		return (Vnet_compute_controller)getSqlMapClientTemplate().queryForObject(
				"Vnet_compute_controller.getByControllerId", controller_id);
	}

}

/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_instance_controller;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_instance_controllerDao;
import cn.dlut.dao.Vnet_ipDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_instance_controllerDao")
public class Vnet_instance_controllerDaoImpl extends IBatisEntityDao<Vnet_instance_controller> implements Vnet_instance_controllerDao {
	static Logger logger = Logger.getLogger(Vnet_instance_controllerDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_instance_controller> getAll() {
		return (List<Vnet_instance_controller>) getSqlMapClientTemplate().queryForList(
				"Vnet_instance_controller.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_instance_controller getById(int id) {
		return (Vnet_instance_controller) getSqlMapClientTemplate().queryForObject(
				"Vnet_instance_controller.getById", id);
	}

	@Override
	public int insertInstance_controller(Vnet_instance_controller i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_instance_controller.insertInstance_controller", i);
		return 0;
	}

	@Override
	public int delById(int id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_instance_controller.delById", id);
		return 0;
	}

	@Override
	public String getInstance_id(String controller_id) {
		// TODO Auto-generated method stub
		Vnet_instance_controller temp= (Vnet_instance_controller)getSqlMapClientTemplate().queryForObject(
				"Vnet_instance_controller.getInstance_id", controller_id);
		return temp.getInstance_id();
	}

	@Override
	public String getController_id(String instance_id) {
		// TODO Auto-generated method stub
		Vnet_instance_controller temp= (Vnet_instance_controller)getSqlMapClientTemplate().queryForObject(
				"Vnet_instance_controller.getController_id", instance_id);
		return temp.getController_id();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_instance_controller> getAllActive(int exe_id) {
		return (List<Vnet_instance_controller>) getSqlMapClientTemplate().queryForList(
		"Vnet_instance_controller.getAllActive", exe_id);
	}

	
}

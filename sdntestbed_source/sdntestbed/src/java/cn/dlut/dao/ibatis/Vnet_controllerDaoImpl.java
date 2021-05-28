/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_vnet;
import cn.dlut.dao.Vnet_controllerDao;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_vnetDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_controllerDao")
public class Vnet_controllerDaoImpl extends IBatisEntityDao<Vnet_controller> implements Vnet_controllerDao {
	static Logger logger = Logger.getLogger(Vnet_controllerDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_controller> getAll() {
		return (List<Vnet_controller>) getSqlMapClientTemplate().queryForList(
				"Vnet_controller.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_controller getById(String id) {
		return (Vnet_controller) getSqlMapClientTemplate().queryForObject(
				"Vnet_controller.getById", id);
	}

	public int delById(String id)
	{
		getSqlMapClientTemplate().delete("Vnet_controller.delById", id);
		return 0;
	}
	
	public List<Vnet_controller> getAllbyStatus(int exe_id){
		return (List<Vnet_controller>) getSqlMapClientTemplate().queryForList(
				"Vnet_controller.getAllbyStatus", exe_id);
	}

	@Override
	public int insertController(Vnet_controller c) {
		getSqlMapClientTemplate().insert("Vnet_controller.insertController", c);
		return 0;
	}

	@Override
	public void updateController(Vnet_controller con) {
		// TODO Auto-generated method stub
		
		getSqlMapClientTemplate().update("Vnet_controller.updateController",con);
		
	}
	
}

/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_executor_vnet;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_instance_controller;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_vnet;
import cn.dlut.dao.Vnet_executor_vnetDao;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_instance_controllerDao;
import cn.dlut.dao.Vnet_ipDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_executor_vnetDao")
public class Vnet_executor_vnetDaoImpl extends IBatisEntityDao<Vnet_executor_vnet> 
		implements Vnet_executor_vnetDao {
	static Logger logger = Logger.getLogger(Vnet_executor_vnetDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_executor_vnet> getAll() {
		return (List<Vnet_executor_vnet>) getSqlMapClientTemplate().queryForList(
				"Vnet_executor_vnet.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_executor_vnet getById(int id) {
		return (Vnet_executor_vnet) getSqlMapClientTemplate().queryForObject(
				"Vnet_executor_vnet.getById", id);
	}

	@Override
	public int insertExecutor_vnet(Vnet_executor_vnet ev) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_executor_vnet.insertExecutor_vnet", ev);
		return 0;
	}

	@Override
	public int delById(int id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_executor_vnet.delById", id);
		return 0;
	}

	@Override
	public int delByVnet_id(String id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_executor_vnet.delByVnet_id", id);
		return 0;
	}

	@Override
	public Vnet_vnet getVnetByexe_id(int exe_id) {
		// TODO Auto-generated method stub
		return (Vnet_vnet) getSqlMapClientTemplate().queryForObject(
				"Vnet_executor_vnet.getVnetByexe_id", exe_id);
	}

	@Override
	public Vnet_executor_vnet getByexe_id(int exe_id) {
		// TODO Auto-generated method stub
		return (Vnet_executor_vnet) getSqlMapClientTemplate().queryForObject(
				"Vnet_executor_vnet.getByexe_id", exe_id);
	}
	
}

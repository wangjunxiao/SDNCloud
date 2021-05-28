/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_instance_host;
import cn.dlut.dao.Vnet_instance_hostDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_instance_hostDao")
public class Vnet_instance_hostDaoImpl extends IBatisEntityDao<Vnet_instance_host> implements Vnet_instance_hostDao {
	static Logger logger = Logger.getLogger(Vnet_instance_hostDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_instance_host> getAll() {
		return (List<Vnet_instance_host>) getSqlMapClientTemplate().queryForList(
				"Vnet_instance_host.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_instance_host getById(int id) {
		return (Vnet_instance_host) getSqlMapClientTemplate().queryForObject(
				"Vnet_instance_host.getById", id);
	}
	
	@Override
	public int insertInstance_host(Vnet_instance_host i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_instance_host.insertInstance_host", i);
		return 0;
	}

	@Override
	public int delById(int id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_instance_host.delById", id);
		return 0;
	}

	@Override
	public Vnet_instance_host getByInstance_id(String instance_id) {
		// TODO Auto-generated method stub
		Vnet_instance_host temp=(Vnet_instance_host)getSqlMapClientTemplate().queryForObject("Vnet_instance_host.getByInstance_id", instance_id);
		return temp;
	}

	@Override
	public Vnet_instance_host getByHost_id(String host_id) {
		// TODO Auto-generated method stub
		Vnet_instance_host temp=(Vnet_instance_host)getSqlMapClientTemplate().queryForObject("Vnet_instance_host.getByHost_id", host_id);
		return temp;
	}

	@Override
	public String getInstance_id(String host_id) {
		// TODO Auto-generated method stub
		Vnet_instance_host temp=this.getByHost_id(host_id);
		return temp.getInstance_id();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_instance_host> getAllActive(int exe_id) {
		return (List<Vnet_instance_host>) getSqlMapClientTemplate().queryForList(
		"Vnet_instance_host.getAllActive", exe_id);
	}


	
}

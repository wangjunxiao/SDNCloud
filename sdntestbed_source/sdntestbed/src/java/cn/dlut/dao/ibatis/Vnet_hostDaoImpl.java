/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_host;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_hostDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_hostDao")
public class Vnet_hostDaoImpl extends IBatisEntityDao<Vnet_host> implements Vnet_hostDao {
	static Logger logger = Logger.getLogger(Vnet_hostDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_host> getAll() {
		return (List<Vnet_host>) getSqlMapClientTemplate().queryForList(
				"Vnet_host.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_host getById(String id) {
		return (Vnet_host) getSqlMapClientTemplate().queryForObject(
				"Vnet_host.getById", id);
	}

	public int delById(String id)
	{
		getSqlMapClientTemplate().delete("Vnet_host.delById", id);
		return 0;
	}
	
	public List<Vnet_host> getAllbyStatus(int exe_id){
		return (List<Vnet_host>) getSqlMapClientTemplate().queryForList(
		"Vnet_host.getAllbyStatus", exe_id);
	}

	@Override
	public int insertHost(Vnet_host h) {
		getSqlMapClientTemplate().insert("Vnet_host.insertHost", h);
		return 0;
	}
	
	public void updateHost(Vnet_host con) {
		getSqlMapClientTemplate().update("Vnet_host.updateHost",con);
	}
	
}

/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_instance_osport;
import cn.dlut.dao.Vnet_instance_osportDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_instance_osportDao")
public class Vnet_instance_osportDaoImpl extends IBatisEntityDao<Vnet_instance_osport> 
		implements Vnet_instance_osportDao {
	static Logger logger = Logger.getLogger(Vnet_instance_osportDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_instance_osport> getAll() {
		return (List<Vnet_instance_osport>) getSqlMapClientTemplate().queryForList(
				"Vnet_instance_osport.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_instance_osport getById(int id) {
		return (Vnet_instance_osport) getSqlMapClientTemplate().queryForObject(
				"Vnet_instance_osport.getById", id);
	}

	@Override
	public int insertInstanceOsport(Vnet_instance_osport ii) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_instance_osport.insertInstanceOsport", ii);
		return 0;
	}

	@Override
	public int delById(int id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_instance_osport.delById", id);
		return 0;
	}

	@Override
	public String getOsport_id(String instance_id) {
		// TODO Auto-generated method stub
		
		Vnet_instance_osport temp=(Vnet_instance_osport)getSqlMapClientTemplate().queryForObject("Vnet_instance_osport.getByInstance_id",instance_id);
		return temp.getOsport_id();
	}

	@Override
	public String getOsport_id_by_hostid(String host_id) {
		// TODO Auto-generated method stub
		
		return (String)getSqlMapClientTemplate().queryForObject("Vnet_instance_osport.getOsport_id_by_hostid", host_id);
	}

}

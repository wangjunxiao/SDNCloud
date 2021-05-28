/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_osport;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_osportDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_osportDao")
public class Vnet_osportDaoImpl extends IBatisEntityDao<Vnet_osport> implements Vnet_osportDao {
	static Logger logger = Logger.getLogger(Vnet_osportDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_osport> getAll() {
		return (List<Vnet_osport>) getSqlMapClientTemplate().queryForList(
				"Vnet_osport.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_osport getById(String id) {
		return (Vnet_osport) getSqlMapClientTemplate().queryForObject(
				"Vnet_osport.getById", id);
	}

	public int insertOsport(Vnet_osport f) {
		getSqlMapClientTemplate().insert("Vnet_osport.insertOsport", f);
		return 0;
	}
	
	public int delById(String id)
	{
		getSqlMapClientTemplate().delete("Vnet_osport.delById", id);
		return 0;
	}


	@Override
	public int updateOsport(Vnet_osport osport) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_osport.updateOsport",osport);
		return 0;
	}

	@Override
	public int updateTypeIP(Vnet_osport osport) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_osport.updateTypeIP",osport);
		return 0;
	}

	@Override
	public String getOsportOsid_by_host_id(String host_id) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("Vnet_osport.getOsportOsid_by_host_id",host_id) ;
	}

	@Override
	public Vnet_osport getbyHostid(String host_id) {
		// TODO Auto-generated method stub
		return (Vnet_osport)getSqlMapClientTemplate().queryForObject("Vnet_osport.getbyHostid", host_id);
	}

	@Override
	public Vnet_osport getbyOsid(String osid) {
		// TODO Auto-generated method stub
		return (Vnet_osport)getSqlMapClientTemplate().queryForObject("Vnet_osport.getbyOsid", osid);
	}

	@Override
	public Vnet_osport getbyConid(String con_id) {
		// TODO Auto-generated method stub
		return (Vnet_osport)getSqlMapClientTemplate().queryForObject("Vnet_osport.getbyConid", con_id);
	}
	
}

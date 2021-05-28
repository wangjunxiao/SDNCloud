/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_ipDao")
public class Vnet_ipDaoImpl extends IBatisEntityDao<Vnet_ip> implements Vnet_ipDao {
	static Logger logger = Logger.getLogger(Vnet_ipDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_ip> getAll() {
		return (List<Vnet_ip>) getSqlMapClientTemplate().queryForList(
				"Vnet_ip.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_ip getById(int id) {
		return (Vnet_ip) getSqlMapClientTemplate().queryForObject(
				"Vnet_ip.getById", id);
	}

	@Override
	public int insertIp(Vnet_ip i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_ip.insertIp", i);
		return 0;
	}
	
	@Override
	public int updateIp(Vnet_ip i)
	{
		getSqlMapClientTemplate().update("Vnet_ip.updateIp",i);
		return 0;
	}

	@Override
	public int delById(int id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_ip.delById", id);
		return 0;
	}

	@Override
	public Vnet_ip getByAddr(String addr) {
		return (Vnet_ip) getSqlMapClientTemplate().queryForObject(
				"Vnet_ip.getByAddr", addr);
	}

	@Override
	public int updateByIpAddr(Vnet_ip ii) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_ip.updateByIpAddr",ii);
		return 0;
	}

	@Override
	public int updateByIpID(Vnet_ip ii) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_ip.updateByIpID",ii);
		return 0;
	}

	@Override
	public String getInstanceIP(String instance_id) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"Vnet_ip.getInstanceIP", instance_id);
	}



//	@Override
//	public int insertIp(Vnet_ip i) {
//		// TODO Auto-generated method stub
//		getSqlMapClientTemplate().insert("Vnet_ip.insertIp", i);
//		return 0;
//	}
//
//	@Override
//	public int delById(int id) {
//		// TODO Auto-generated method stub
//		getSqlMapClientTemplate().delete("Vnet_ip.delById", id);
//		return 0;
//	}
	
}

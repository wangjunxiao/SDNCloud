/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_userip;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_useripDao;
import cn.dlut.dao.Vnet_subnetDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_useripDao")
public class Vnet_useripDaoImpl extends IBatisEntityDao<Vnet_userip> implements Vnet_useripDao {
	static Logger logger = Logger.getLogger(Vnet_useripDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_userip> getAll() {
		return (List<Vnet_userip>) getSqlMapClientTemplate().queryForList(
				"Vnet_userip.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_userip getById(String userip_id) {
		return (Vnet_userip) getSqlMapClientTemplate().queryForObject(
				"Vnet_userip.getById", userip_id);
	}

	@Override
	public int insertUserip(Vnet_userip i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_userip.insertUserip", i);
		return 0;
	}
	
	

	@Override
	public int delById(String userip_id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_userip.delById",userip_id);
		return 0;
	}

	@Override
	public int updateOsport_id(Vnet_userip v) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_userip.updateOsport_id", v);
		return 0;
	}

	


}

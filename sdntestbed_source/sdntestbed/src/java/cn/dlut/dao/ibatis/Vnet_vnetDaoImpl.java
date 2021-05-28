/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_vnet;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_vnetDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_vnetDao")
public class Vnet_vnetDaoImpl extends IBatisEntityDao<Vnet_vnet> implements Vnet_vnetDao {
	static Logger logger = Logger.getLogger(Vnet_vnetDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_vnet> getAll() {
		return (List<Vnet_vnet>) getSqlMapClientTemplate().queryForList(
				"Vnet_vnet.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_vnet getById(String id) {
		return (Vnet_vnet) getSqlMapClientTemplate().queryForObject(
				"Vnet_vnet.getById", id);
	}

	public int delById(String id)
	{
		getSqlMapClientTemplate().delete("Vnet_vnet.delById", id);
		return 0;
	}

	@Override
	public int insertVnet(Vnet_vnet v) {
		getSqlMapClientTemplate().insert("Vnet_vnet.insertVnet", v);
		return 0;
	}

	@Override
	public int updateVnet(Vnet_vnet temp_vnet) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_vnet.updateVnet",temp_vnet);
		return 0;
	}
	
}

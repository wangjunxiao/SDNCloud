/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_tunofport;
import cn.dlut.entity.Vnet_vnet;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_tunofportDao;
import cn.dlut.dao.Vnet_vnetDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_tunofportDao")
public class Vnet_tunofportDaoImpl extends IBatisEntityDao<Vnet_tunofport> implements Vnet_tunofportDao {
	static Logger logger = Logger.getLogger(Vnet_tunofportDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_tunofport> getAll() {
		return (List<Vnet_tunofport>) getSqlMapClientTemplate().queryForList(
				"Vnet_tunofport.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_tunofport getById(String id) {
		return (Vnet_tunofport) getSqlMapClientTemplate().queryForObject(
				"Vnet_tunofport.getById", id);
	}

	public int delById(String id)
	{
		getSqlMapClientTemplate().delete("Vnet_tunofport.delById", id);
		return 0;
	}

	public int insertTunofport(Vnet_tunofport v) {
		getSqlMapClientTemplate().insert("Vnet_tunofport.insertTunofport", v);
		return 0;
	}

	@Override
	public int updatePortNum(Vnet_tunofport temp) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_tunofport.updatePortNum", temp);
		return 0;
	}
	
}

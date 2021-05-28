/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_ofport;
import cn.dlut.dao.Vnet_ofportDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_ofportDao")
public class Vnet_ofportDaoImpl extends IBatisEntityDao<Vnet_ofport> implements Vnet_ofportDao {
	static Logger logger = Logger.getLogger(Vnet_ofportDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_ofport> getAll() {
		return (List<Vnet_ofport>) getSqlMapClientTemplate().queryForList(
				"Vnet_ofport.getAll");
	}
	
	@Override
	public Vnet_ofport getById(String id)
	{
		return (Vnet_ofport)getSqlMapClientTemplate().queryForObject("Vnet_ofport.getById",id);
	}
	
	@Override
	public int insertOfport(Vnet_ofport f)
	{
		getSqlMapClientTemplate().insert("Vnet_ofport.insertOfport",f);
		return 0;
	}	
	
	@Override
	public int delById(String id)
	{
		getSqlMapClientTemplate().delete("Vnet_ofport.delById",id);
		return 0;
	}

	@Override
	public int updateOfport(Vnet_ofport o) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_ofport.updateOfport",o);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_ofport> getAllbyStatus() {
		return (List<Vnet_ofport>) getSqlMapClientTemplate().queryForList(
			"Vnet_ofport.getAllbyStatus");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_ofport> getAllByVnet(String vnet_id) {
		// TODO Auto-generated method stub
		return (List<Vnet_ofport>) getSqlMapClientTemplate().queryForList(
			"Vnet_ofport.getAllByVnet", vnet_id);
	}	
	
}

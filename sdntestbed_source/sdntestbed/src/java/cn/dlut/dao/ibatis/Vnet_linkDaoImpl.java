/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_link;
import cn.dlut.entity.Vnet_osport;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_linkDao;
import cn.dlut.dao.Vnet_osportDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_linkDao")
public class Vnet_linkDaoImpl extends IBatisEntityDao<Vnet_link> implements Vnet_linkDao {
	static Logger logger = Logger.getLogger(Vnet_linkDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_link> getAll() {
		return (List<Vnet_link>) getSqlMapClientTemplate().queryForList(
				"Vnet_link.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_link getById(String id) {
		return (Vnet_link) getSqlMapClientTemplate().queryForObject(
				"Vnet_link.getById", id);
	}

	public int insertLink(Vnet_link f) {
		getSqlMapClientTemplate().insert("Vnet_link.insertLink", f);
		return 0;
	}
	
	public int delById(String id)
	{
		getSqlMapClientTemplate().delete("Vnet_link.delById", id);
		return 0;
	}

	@Override
	public int updateLink(Vnet_link l) {
		
		getSqlMapClientTemplate().update("Vnet_link.updateLink",l);
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_link> getAllbyStatus(int exe_id) {
		return (List<Vnet_link>) getSqlMapClientTemplate().queryForList(
		"Vnet_link.getAllbyStatus", exe_id);
	}

//	@Override
//	public int insertLink(Vnet_link f) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
}

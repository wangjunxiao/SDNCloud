/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_requestDao;
import cn.dlut.entity.Vnet_image;
import cn.dlut.entity.Vnet_request;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_requestDao")
public class Vnet_requestDaoImpl extends IBatisEntityDao<Vnet_request> implements Vnet_requestDao {
	static Logger logger = Logger.getLogger(Vnet_requestDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_request> getAll() {
		return (List<Vnet_request>) getSqlMapClientTemplate().queryForList(
				"Vnet_request.getAll");
	}

	public Vnet_request getById(int id) {
		return (Vnet_request) getSqlMapClientTemplate().queryForList(
				"Vnet_request.getById", id);
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Vnet_request.delById", id);
	}
	
/*
	@Override
	public int updateRequest(Vnet_request request) {
		getSqlMapClientTemplate().update("Vnet_request.updateRequest", request);
		return 0;
	}*/
	
	
	@Override
	public int insertRequest(Vnet_request ii)
	{
		return Integer.parseInt(String.valueOf(getSqlMapClientTemplate().insert("Vnet_request.insert",ii)));
	}

	@Override
	public int updateRequest(Vnet_request request) {
		// TODO Auto-generated method stub
		
		getSqlMapClientTemplate().update("Vnet_request.update",request);
		return 0;
	}
}

/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.dao.Vnet_flavorDao;

import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_ipDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_floatingipDao")
public class Vnet_floatingipDaoImpl extends IBatisEntityDao<Vnet_floatingip> implements Vnet_floatingipDao {
	static Logger logger = Logger.getLogger(Vnet_floatingipDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_floatingip> getAll() {
		return (List<Vnet_floatingip>) getSqlMapClientTemplate().queryForList(
				"Vnet_floatingip.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_floatingip getById(Integer id) {
		return (Vnet_floatingip) getSqlMapClientTemplate().queryForObject(
				"Vnet_floatingip.getById", id);
	}
	
	@Override
	public Vnet_floatingip getRandomFloatingip(String status) {
		// TODO Auto-generated method stub
		return (Vnet_floatingip) getSqlMapClientTemplate().queryForObject(
				"Vnet_floatingip.getRandomFloatingip",status);
	}

	public int insertFloatingip(Vnet_floatingip i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_floatingip.insertFloatingip", i);
		return 0;
	}

	
	@Override
	public int updateFloatingip(Vnet_floatingip i)
	{
		getSqlMapClientTemplate().update("Vnet_floatingip.updateFloatingip",i);
		return 0;
	}
	
	@Override
	public int updateFloatingipByOsid(Vnet_floatingip ii)
	{
		getSqlMapClientTemplate().update("Vnet_floatingip.updateFloatingipByOsid",ii);
		return 0;
	}
	
	public int delById(Integer id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_floatingip.delById", id);
		return 0;
	}

	@Override
	public void setDownById(int id) {
		// TODO Auto-generated method stub
		
		getSqlMapClientTemplate().update("Vnet_floatingip.setDownById",id);
		
	}

	@Override
	public Vnet_floatingip getbyControllerid(String controller_id) {
		// TODO Auto-generated method stub
		return (Vnet_floatingip)getSqlMapClientTemplate().queryForObject("Vnet_floatingip.getbyControllerid",controller_id) ;
	}

	@Override
	public Vnet_floatingip getByOsid(String osid) {
		// TODO Auto-generated method stub
		return (Vnet_floatingip)getSqlMapClientTemplate().queryForObject("Vnet_floatingip.getByOsid",osid);
	}

	@Override
	public Vnet_floatingip getbyInsid(String ins_id) {
		// TODO Auto-generated method stub
		return (Vnet_floatingip)getSqlMapClientTemplate().queryForObject("Vnet_floatingip.getbyInsid", ins_id) ;
	}

	@Override
	public Vnet_floatingip gethostfloatipbyInsid(String ins_id) {
		// TODO Auto-generated method stub
		return (Vnet_floatingip)getSqlMapClientTemplate().queryForObject("Vnet_floatingip.gethostfloatipbyInsid", ins_id) ;
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

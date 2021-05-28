/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_tun;
import cn.dlut.entity.Vnet_tunofport;
import cn.dlut.entity.Vnet_vnet;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_tunDao;
import cn.dlut.dao.Vnet_tunofportDao;
import cn.dlut.dao.Vnet_vnetDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_tunDao")
public class Vnet_tunDaoImpl extends IBatisEntityDao<Vnet_tun> implements Vnet_tunDao {
	static Logger logger = Logger.getLogger(Vnet_tunDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_tun> getAll() {
		return (List<Vnet_tun>) getSqlMapClientTemplate().queryForList(
				"Vnet_tun.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_tun getById(Integer id) {
		return (Vnet_tun) getSqlMapClientTemplate().queryForObject(
				"Vnet_tun.getById", id);
	}

	public int delById(Integer id)
	{
		getSqlMapClientTemplate().delete("Vnet_tun.delById", id);
		return 0;
	}

	public int insertTun(Vnet_tun v) {
		getSqlMapClientTemplate().insert("Vnet_tun.insertTun", v);
		return 0;
	}

	@Override
	public Vnet_tun getRandom(String status) {
		// TODO Auto-generated method stub
		Vnet_tun temp=(Vnet_tun)getSqlMapClientTemplate().queryForObject("Vnet_tun.getRandom",status);
		return temp;
	}

	@Override
	public int updateTun(Vnet_tun temp) {
		// TODO Auto-generated method stub
		
		getSqlMapClientTemplate().update("Vnet_tun.updateTun", temp);
		return 0;
	}
	
}

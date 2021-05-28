/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.entity.Vnet_usersubnet_userip;
import cn.dlut.entity.Vnet_vnet_userscript;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_subnetDao;
import cn.dlut.dao.Vnet_usersubnet_useripDao;
import cn.dlut.dao.Vnet_vnet_userscriptDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_usersubnet_useripDao")
public class Vnet_usersubnet_useripDaoImpl extends IBatisEntityDao<Vnet_usersubnet_userip> implements Vnet_usersubnet_useripDao {
	static Logger logger = Logger.getLogger(Vnet_usersubnet_useripDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_usersubnet_userip> getAll() {
		return (List<Vnet_usersubnet_userip>) getSqlMapClientTemplate().queryForList(
				"Vnet_usersubnet_userip.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_usersubnet_userip getById(Integer usersubnet_userip_id) {
		return (Vnet_usersubnet_userip) getSqlMapClientTemplate().queryForObject(
				"Vnet_usersubnet_userip.getById", usersubnet_userip_id);
	}

	@Override
	public int insertUsersubnet_userip(Vnet_usersubnet_userip i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_usersubnet_userip.insertUsersubnet_userip", i);
		return 0;
	}
	
	

	@Override
	public int delById(Integer usersubnet_userip_id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_usersubnet_userip.delById", usersubnet_userip_id);
		return 0;
	}

	


}

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
import cn.dlut.entity.Vnet_vnet_userscript;
import cn.dlut.entity.Vnet_vnet_usersubnet;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_subnetDao;
import cn.dlut.dao.Vnet_vnet_userscriptDao;
import cn.dlut.dao.Vnet_vnet_usersubnetDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_vnet_usersubnetDao")
public class Vnet_vnet_usersubnetDaoImpl extends IBatisEntityDao<Vnet_vnet_usersubnet> implements Vnet_vnet_usersubnetDao {
	static Logger logger = Logger.getLogger(Vnet_vnet_usersubnetDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_vnet_usersubnet> getAll() {
		return (List<Vnet_vnet_usersubnet>) getSqlMapClientTemplate().queryForList(
				"Vnet_vnet_usersubnet.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_vnet_usersubnet getById(Integer vnet_usersubnet_id) {
		return (Vnet_vnet_usersubnet) getSqlMapClientTemplate().queryForObject(
				"Vnet_vnet_usersubnet.getById", vnet_usersubnet_id);
	}

	@Override
	public int insertVnet_usersubnet(Vnet_vnet_usersubnet i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_vnet_usersubnet.insertVnet_usersubnet", i);
		return 0;
	}
	
	

	@Override
	public int delById(Integer vnet_usersubnet_id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_vnet_usersubnet.delById", vnet_usersubnet_id);
		return 0;
	}

	


}

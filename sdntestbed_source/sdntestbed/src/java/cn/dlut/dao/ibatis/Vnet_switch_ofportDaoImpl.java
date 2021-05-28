/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_switch;
import cn.dlut.entity.Vnet_switch_ofport;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_switchDao;
import cn.dlut.dao.Vnet_switch_ofportDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_switch_ofportDao")
public class Vnet_switch_ofportDaoImpl extends IBatisEntityDao<Vnet_switch_ofport> implements Vnet_switch_ofportDao {
	static Logger logger = Logger.getLogger(Vnet_switch_ofportDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_switch_ofport> getAll() {
		return (List<Vnet_switch_ofport>) getSqlMapClientTemplate().queryForList(
				"Vnet_switch_ofport.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_switch_ofport getById(int id) {
		return (Vnet_switch_ofport) getSqlMapClientTemplate().queryForObject(
				"Vnet_switch_ofport.getById", id);
	}

	@Override
	public int insertSwitch_ofport(Vnet_switch_ofport i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_switch_ofport.insertSwitch_ofport", i);
		return 0;
	}

	@Override
	public int delById(int id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_switch_ofport.delById", id);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_switch_ofport> getBySwitchId(String switch_id) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("Vnet_switch_ofport.getBySwitchId", switch_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_switch_ofport> getAllByVnet(String vnet_id) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("Vnet_switch_ofport.getAllByVnet", vnet_id);
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

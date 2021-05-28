/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_oflink;
import cn.dlut.entity.Vnet_switch;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_switchDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_switchDao")
public class Vnet_switchDaoImpl extends IBatisEntityDao<Vnet_switch> implements Vnet_switchDao {
	static Logger logger = Logger.getLogger(Vnet_switchDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_switch> getAll() {
		return (List<Vnet_switch>) getSqlMapClientTemplate().queryForList(
				"Vnet_switch.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_switch getById(String id) {
		return (Vnet_switch) getSqlMapClientTemplate().queryForObject(
				"Vnet_switch.getById", id);
	}

	@Override
	public int insertSwitch(Vnet_switch i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_switch.insertSwitch", i);
		return 0;
	}

	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_switch.delById", id);
		return 0;
	}

	@Override
	public int getSwitch_counter(String id) {
		// TODO Auto-generated method stub
		return ((Vnet_switch)getSqlMapClientTemplate().queryForObject(
				"Vnet_switch.getById",id)).getSwitch_counter();
	}

	@Override
	public int updateSwitch(Vnet_switch ss) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_switch.updateSwitch",ss);
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_switch> getAllbyStatus(int exe_id) {
		return (List<Vnet_switch>) getSqlMapClientTemplate().queryForList(
		"Vnet_switch.getAllbyStatus", exe_id);
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

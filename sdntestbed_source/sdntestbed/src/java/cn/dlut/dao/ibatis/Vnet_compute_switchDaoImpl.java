/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_compute_instanceDao;
import cn.dlut.dao.Vnet_compute_switchDao;
import cn.dlut.dao.Vnet_image_instanceDao;
import cn.dlut.entity.Vnet_compute_instance;
import cn.dlut.entity.Vnet_compute_switch;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_compute_switchDao")
public class Vnet_compute_switchDaoImpl extends IBatisEntityDao<Vnet_compute_switch> implements Vnet_compute_switchDao {
	static Logger logger = Logger.getLogger(Vnet_compute_switchDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_compute_switch> getAll() {
		return (List<Vnet_compute_switch>) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_switch.getAll");
	}

	@Override
	public Vnet_compute_switch getById(int id) {
		return (Vnet_compute_switch) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_switch.getById", id);
	}

	@Override
	public int insertCompute_switch(Vnet_compute_switch ii) {
		getSqlMapClientTemplate().insert("Vnet_compute_switch.insertCompute_switch", ii);
		return 0;
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Vnet_compute_switch.delById", id);
	}

	@Override
	public Vnet_compute_switch getBySwitchId(String switch_id) {
		// TODO Auto-generated method stub
		Vnet_compute_switch temp=(Vnet_compute_switch)getSqlMapClientTemplate().queryForObject("Vnet_compute_switch.getBySwitchId", switch_id);
		return temp;
	}

}

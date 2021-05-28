/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_compute;
import cn.dlut.entity.Vnet_image;
import cn.dlut.dao.Vnet_computeDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_computeDao")
public class Vnet_computeDaoImpl extends IBatisEntityDao<Vnet_compute> implements Vnet_computeDao {
	static Logger logger = Logger.getLogger(Vnet_computeDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_compute> getAll() {
		return (List<Vnet_compute>) getSqlMapClientTemplate().queryForList(
				"Vnet_compute.getAll");
	}
	
	@Override
	public Vnet_compute getById(int id) {
		return (Vnet_compute)getSqlMapClientTemplate().queryForObject(
				"Vnet_compute.getById", id);
	}

	@Override
	public int delById(int id)
	{
		getSqlMapClientTemplate().delete("Vnet_compute.delById", id);
		return 0;
	}

	@Override
	public int insertCompute(Vnet_compute c) {
		getSqlMapClientTemplate().insert("Vnet_compute.insertCompute", c);
		return 0;
	}

	@Override
	public Vnet_compute getByHostId(String host_id) {
		// TODO Auto-generated method stub
		return (Vnet_compute)getSqlMapClientTemplate().queryForObject(
				"Vnet_compute.getByHostId", host_id);
	}

	@Override
	public Vnet_compute getBySwitchId(String switch_id) {
		// TODO Auto-generated method stub
		return (Vnet_compute)getSqlMapClientTemplate().queryForObject(
				"Vnet_compute.getBySwitchId", switch_id);
	}

	@Override
	public Vnet_compute getByConId(String con_id) {
		// TODO Auto-generated method stub
		return (Vnet_compute)getSqlMapClientTemplate().queryForObject(
				"Vnet_compute.getByConId", con_id);
	}
	
	public int getByCompute_name(String computer_name) {
		Vnet_compute temp=(Vnet_compute)getSqlMapClientTemplate().queryForObject("Vnet_compute.getByComputer_name",computer_name);
		return temp.getCompute_id();
	}
}

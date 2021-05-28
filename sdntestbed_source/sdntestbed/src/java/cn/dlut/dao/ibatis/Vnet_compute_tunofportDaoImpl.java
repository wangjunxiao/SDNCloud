/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_compute_instanceDao;
import cn.dlut.dao.Vnet_compute_switchDao;
import cn.dlut.dao.Vnet_compute_tunofportDao;
import cn.dlut.dao.Vnet_image_instanceDao;
import cn.dlut.entity.Vnet_compute_instance;
import cn.dlut.entity.Vnet_compute_switch;
import cn.dlut.entity.Vnet_compute_tunofport;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_compute_tunofportDao")
public class Vnet_compute_tunofportDaoImpl extends IBatisEntityDao<Vnet_compute_tunofport> implements Vnet_compute_tunofportDao {
	static Logger logger = Logger.getLogger(Vnet_compute_tunofportDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_compute_tunofport> getAll() {
		return (List<Vnet_compute_tunofport>) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_tunofport.getAll");
	}

	@Override
	public Vnet_compute_tunofport getById(int id) {
		return (Vnet_compute_tunofport) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_tunofport.getById", id);
	}

	@Override
	public int insertCompute_tunofport(Vnet_compute_tunofport ii) {
		getSqlMapClientTemplate().insert("Vnet_compute_tunofport.insertCompute_tunofport", ii);
		return 0;
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Vnet_compute_tunofport.delById", id);
	}

	@Override
	public Vnet_compute_tunofport getByComputeId(Integer compute_id) {
		// TODO Auto-generated method stub
		Vnet_compute_tunofport temp=(Vnet_compute_tunofport)getSqlMapClientTemplate(
				).queryForObject("Vnet_compute_tunofport.getByComputeId", compute_id);
		
		return temp;
	}

}

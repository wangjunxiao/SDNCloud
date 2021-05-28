/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_compute_hostDao;
import cn.dlut.entity.Vnet_compute_host;
import cn.dlut.entity.Vnet_host;
import cn.dlut.entity.Vnet_compute;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_compute_hostDao")
public class Vnet_compute_hostDaoImpl extends IBatisEntityDao<Vnet_compute_host> implements Vnet_compute_hostDao {
	static Logger logger = Logger.getLogger(Vnet_compute_hostDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_compute_host> getAll() {
		return (List<Vnet_compute_host>) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_host.getAll");
	}

	@Override
	public Vnet_compute_host getById(int id) {
		return (Vnet_compute_host) getSqlMapClientTemplate().queryForList(
				"Vnet_compute_host.getById", id);
	}

	@Override
	public int insertCompute_host(Vnet_compute_host ii) {
		getSqlMapClientTemplate().insert("Vnet_compute_host.insertCompute_host", ii);
		return 0;
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Vnet_compute_host.delById", id);
	}

	@Override
	public Vnet_compute_host getByHostId(String host_id) {
		// TODO Auto-generated method stub
		Vnet_compute_host temp=(Vnet_compute_host)getSqlMapClientTemplate().queryForObject("Vnet_compute_host.getByHostId", host_id);
		return temp;
	}

}

/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_subnet;
import cn.dlut.dao.Vnet_subnetDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_subnetDao")
public class Vnet_subnetDaoImpl extends IBatisEntityDao<Vnet_subnet> implements Vnet_subnetDao {
	static Logger logger = Logger.getLogger(Vnet_subnetDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_subnet> getAll() {
		return (List<Vnet_subnet>) getSqlMapClientTemplate().queryForList(
				"Vnet_subnet.getAll");
	}
	
	public Vnet_subnet getById(String usersubnet_id) {
		return (Vnet_subnet) getSqlMapClientTemplate().queryForObject(
				"Vnet_subnet.getById", usersubnet_id);
	}

	@Override
	public int insertUsersubnet(Vnet_subnet i) {
		getSqlMapClientTemplate().insert("Vnet_subnet.insertUsersubnet", i);
		return 0;
	}
	
	

	@Override
	public int delById(String usersubnetid) {
		getSqlMapClientTemplate().delete("Vnet_subnet.delById", usersubnetid);
		return 0;
	}

	@Override
	public int updateSubnet(Vnet_subnet ii) {
		getSqlMapClientTemplate().update("Vnet_subnet.updateSubnet", ii);
		return 0;
	}

	


}

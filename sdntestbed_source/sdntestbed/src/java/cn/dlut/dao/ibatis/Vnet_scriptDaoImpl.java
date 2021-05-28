/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_script;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_scriptDao;
import cn.dlut.dao.Vnet_subnetDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_scriptDao")
public class Vnet_scriptDaoImpl extends IBatisEntityDao<Vnet_script> implements Vnet_scriptDao {
	static Logger logger = Logger.getLogger(Vnet_scriptDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_script> getAll() {
		return (List<Vnet_script>) getSqlMapClientTemplate().queryForList(
				"Vnet_script.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_script getById(String userscript_id) {
		return (Vnet_script) getSqlMapClientTemplate().queryForObject(
				"Vnet_script.getById", userscript_id);
	}

	@Override
	public int insertUserscript(Vnet_script i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_script.insertUserscript", i);
		return 0;
	}
	
	

	@Override
	public int delById(String userscript_id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_script.delById", userscript_id);
		return 0;
	}

	


}

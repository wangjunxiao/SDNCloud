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
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_subnetDao;
import cn.dlut.dao.Vnet_vnet_userscriptDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_vnet_userscriptDao")
public class Vnet_vnet_userscriptDaoImpl extends IBatisEntityDao<Vnet_vnet_userscript> implements Vnet_vnet_userscriptDao {
	static Logger logger = Logger.getLogger(Vnet_vnet_userscriptDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_vnet_userscript> getAll() {
		return (List<Vnet_vnet_userscript>) getSqlMapClientTemplate().queryForList(
				"Vnet_vnet_userscript.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_vnet_userscript getById(Integer vnet_userscript_id) {
		return (Vnet_vnet_userscript) getSqlMapClientTemplate().queryForObject(
				"Vnet_vnet_userscript.getById", vnet_userscript_id);
	}

	@Override
	public int insertVnet_userscript(Vnet_vnet_userscript i) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("Vnet_vnet_userscript.insertVnet_userscript", i);
		return 0;
	}
	
	

	@Override
	public int delById(Integer vnet_userscript_id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("Vnet_vnet_userscript.delById", vnet_userscript_id);
		return 0;
	}

	


}

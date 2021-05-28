/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_instance_controller;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_oflink;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_instance_controllerDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_oflinkDao;
import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_oflinkDao")
public class Vnet_oflinkDaoImpl extends IBatisEntityDao<Vnet_oflink> 
						implements Vnet_oflinkDao {
	static Logger logger = Logger.getLogger(Vnet_oflinkDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_oflink> getAll() {
		return (List<Vnet_oflink>) getSqlMapClientTemplate().queryForList(
				"Vnet_oflink.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_oflink getById(String id) {
		return (Vnet_oflink) getSqlMapClientTemplate().queryForObject(
				"Vnet_oflink.getById", id);
	}

	@Override
	public int insertOflink(Vnet_oflink cs) {
		try{
			getSqlMapClientTemplate().insert("Vnet_oflink.insertOflink", cs);
		} catch(Exception e){
			return 1;
		}
		return 0;
	}

	@Override
	public int delById(String id) {
		try{
			getSqlMapClientTemplate().delete("Vnet_oflink.delById", id);
		} catch(Exception e){
			return 1;
		}
		return 0;
	}

	@Override
	public int updateOflink(Vnet_oflink oflink) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_oflink.updateOflink",oflink);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_oflink> getAllbyStatus(int exe_id) {
		return (List<Vnet_oflink>) getSqlMapClientTemplate().queryForList(
		"Vnet_oflink.getAllbyStatus", exe_id);
	}


}

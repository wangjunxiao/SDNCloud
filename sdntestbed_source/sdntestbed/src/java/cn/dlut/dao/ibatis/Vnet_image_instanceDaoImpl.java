/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_image_instanceDao;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_image_instanceDao")
public class Vnet_image_instanceDaoImpl extends IBatisEntityDao<Vnet_image_instance> implements Vnet_image_instanceDao {
	static Logger logger = Logger.getLogger(Vnet_image_instanceDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_image_instance> getAll() {
		return (List<Vnet_image_instance>) getSqlMapClientTemplate().queryForList(
				"Vnet_image_instance.getAll");
	}

	@Override
	public Vnet_image_instance getById(int id) {
		return (Vnet_image_instance) getSqlMapClientTemplate().queryForList(
				"Vnet_image_instance.getById", id);
	}

	@Override
	public int insertImageInstance(Vnet_image_instance ii) {
		getSqlMapClientTemplate().insert("Vnet_image_instance.insert", ii);
		return 0;
	}

	@Override
	public int deleleImageInstance(int id) {
		return getSqlMapClientTemplate().delete("Vnet_image_instance.delById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vnet_image_instance> getAllActive(int exe_id) {
		return (List<Vnet_image_instance>) getSqlMapClientTemplate().queryForList(
		"Vnet_image_instance.getAllActive", exe_id);
	}

}

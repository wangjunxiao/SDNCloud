package cn.dlut.dao.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.Vnet_vnet_resourceDao;
import cn.dlut.entity.Vnet_vnet_resource;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_vnet_resourceDao")
public class Vnet_vnet_resourceDaoImpl extends IBatisEntityDao<Vnet_vnet_resource> implements Vnet_vnet_resourceDao {
	static Logger logger = Logger.getLogger(Vnet_vnet_resourceDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_vnet_resource> getAll() {
		return (List<Vnet_vnet_resource>) getSqlMapClientTemplate().queryForList(
				"Vnet_vnet_resource.getAll");
	}
	
	@Override
	public Vnet_vnet_resource getById(int id) {
		return (Vnet_vnet_resource) getSqlMapClientTemplate().queryForObject(
				"Vnet_vnet_resource.getById", id);
	}

	@Override
	public int insertVnetResource(Vnet_vnet_resource ii) {
		getSqlMapClientTemplate().insert("Vnet_vnet_resource.insertVnetResource", ii);
		return 0;
	}

	@Override
	public int deleleVnetInstance(int id) {
		return getSqlMapClientTemplate().delete("Vnet_vnet_resource.delById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getOflinkByVnet(String vnet_id) {
		// TODO Auto-generated method stub
		return (ArrayList<String>)getSqlMapClientTemplate().queryForList(
				"Vnet_vnet_resource.getOflinkByVnet",vnet_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getLinkByVnet(String vnet_id) {
		// TODO Auto-generated method stub
		return (ArrayList<String>)getSqlMapClientTemplate().queryForList(
				"Vnet_vnet_resource.getLinkByVnet",vnet_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getSwitchByVnet(String vnet_id) {
		// TODO Auto-generated method stub
		return (ArrayList<String>)getSqlMapClientTemplate().queryForList(
				"Vnet_vnet_resource.getSwitchByVnet",vnet_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getControllerByVnet(String vnet_id) {
		// TODO Auto-generated method stub
		return (ArrayList<String>)getSqlMapClientTemplate().queryForList(
				"Vnet_vnet_resource.getControllerByVnet",vnet_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getHostByVnet(String vnet_id) {
		// TODO Auto-generated method stub
		return (ArrayList<String>)getSqlMapClientTemplate().queryForList(
				"Vnet_vnet_resource.getHostByVnet",vnet_id);
	}

	@Override
	public void setDeleted(String string) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("Vnet_vnet_resource.setDeleted", string);
		
	}


}
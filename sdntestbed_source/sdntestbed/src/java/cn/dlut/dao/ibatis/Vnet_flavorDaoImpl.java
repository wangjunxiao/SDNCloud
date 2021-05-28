/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.dao.Vnet_flavorDao;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("Vnet_flavorDao")
public class Vnet_flavorDaoImpl extends IBatisEntityDao<Vnet_flavor> implements Vnet_flavorDao {
	static Logger logger = Logger.getLogger(Vnet_flavorDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vnet_flavor> getAll() {
		return (List<Vnet_flavor>) getSqlMapClientTemplate().queryForList(
				"Vnet_flavor.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public Vnet_flavor getById(Integer id) {
		return (Vnet_flavor) getSqlMapClientTemplate().queryForObject(
				"Vnet_flavor.getById", id);
	}

	public int insertFlavor(Vnet_flavor f) {
		getSqlMapClientTemplate().insert("Vnet_flavor.insertFlavor", f);
		return 0;
	}
	
	public int delById(Integer id)
	{
		getSqlMapClientTemplate().delete("Vnet_flavor.delById", id);
		return 0;
	}

	@Override
	public String getOsid(Integer id) {
		// TODO Auto-generated method stub
		Vnet_flavor temp=(Vnet_flavor)getSqlMapClientTemplate().queryForObject("Vnet_flavor.getOsid",id);
		return temp.getFlavor_osid();
	}
	

	@Override
	public void delAll() {
		getSqlMapClientTemplate().delete("Vnet_flavor.delAll");
		
	}
	public void print(){
		System.out.println("come out");
	}

}

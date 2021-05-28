/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_tun;
import cn.dlut.entity.Vnet_userip;
import cn.dlut.entity.Vnet_subnet;


public interface Vnet_useripDao extends IBaseDao {

	public List<Vnet_userip> getAll();
	
	public Vnet_userip getById(String userip_id);
	
	public int insertUserip(Vnet_userip v);
	
	public int delById(String userip_id);
	
	public int updateOsport_id(Vnet_userip v);
}

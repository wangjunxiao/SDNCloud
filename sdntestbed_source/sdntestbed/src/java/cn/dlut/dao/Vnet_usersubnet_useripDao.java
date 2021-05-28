/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_tun;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.entity.Vnet_usersubnet_userip;
import cn.dlut.entity.Vnet_vnet_userscript;


public interface Vnet_usersubnet_useripDao extends IBaseDao {

	public List<Vnet_usersubnet_userip> getAll();
	
	public Vnet_usersubnet_userip getById(Integer usersubnet_userip_id);
	
	public int insertUsersubnet_userip(Vnet_usersubnet_userip v);
	
	public int delById(Integer usersubnet_userip_id);
}

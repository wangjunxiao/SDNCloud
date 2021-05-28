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
import cn.dlut.entity.Vnet_vnet_userscript;
import cn.dlut.entity.Vnet_vnet_usersubnet;


public interface Vnet_vnet_usersubnetDao extends IBaseDao {

	public List<Vnet_vnet_usersubnet> getAll();
	
	public Vnet_vnet_usersubnet getById(Integer vnet_usersubnet_id);
	
	public int insertVnet_usersubnet(Vnet_vnet_usersubnet v);
	
	public int delById(Integer vnet_usersubnet_id);
}

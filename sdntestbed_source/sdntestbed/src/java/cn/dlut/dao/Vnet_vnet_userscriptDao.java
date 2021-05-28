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


public interface Vnet_vnet_userscriptDao extends IBaseDao {

	public List<Vnet_vnet_userscript> getAll();
	
	public Vnet_vnet_userscript getById(Integer vnet_userscript_id);
	
	public int insertVnet_userscript(Vnet_vnet_userscript v);
	
	public int delById(Integer vnet_userscript_id);
}

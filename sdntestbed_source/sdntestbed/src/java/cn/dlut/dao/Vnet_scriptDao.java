/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_tun;
import cn.dlut.entity.Vnet_script;
import cn.dlut.entity.Vnet_subnet;


public interface Vnet_scriptDao extends IBaseDao {

	public List<Vnet_script> getAll();
	
	public Vnet_script getById(String userscript_id);
	
	public int insertUserscript(Vnet_script v);
	
	public int delById(String userscript_id);
}

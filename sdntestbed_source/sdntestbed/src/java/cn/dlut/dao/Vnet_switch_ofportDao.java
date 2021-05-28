/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;
import cn.dlut.entity.Vnet_ofport;
import cn.dlut.entity.Vnet_switch;
import cn.dlut.entity.Vnet_switch_ofport;


public interface Vnet_switch_ofportDao extends IBaseDao {

	public List<Vnet_switch_ofport> getAll();
	
	public Vnet_switch_ofport getById(int id);
	
	public int insertSwitch_ofport(Vnet_switch_ofport i);
	
	public int delById(int id);
	
	public List<Vnet_switch_ofport> getBySwitchId(String switch_id);
	
	public List<Vnet_switch_ofport> getAllByVnet(String vnet_id);
	
}

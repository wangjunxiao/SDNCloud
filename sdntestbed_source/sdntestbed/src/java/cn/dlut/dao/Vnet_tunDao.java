/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_tun;
import cn.dlut.entity.Vnet_tunofport;
import cn.dlut.entity.Vnet_vnet;


public interface Vnet_tunDao extends IBaseDao {

	public List<Vnet_tun> getAll();
	
	public Vnet_tun getById(Integer id);
	
	public int insertTun(Vnet_tun v);
	
	public int delById(Integer id);
	
	public Vnet_tun getRandom(String status);
	
	public int updateTun(Vnet_tun temp);
	
}

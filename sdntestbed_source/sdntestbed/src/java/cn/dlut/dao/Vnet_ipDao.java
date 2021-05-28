/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_ip;


public interface Vnet_ipDao extends IBaseDao {

	public List<Vnet_ip> getAll();
	
	public Vnet_ip getById(int id);
	
	public int insertIp(Vnet_ip i);
	
	public int delById(int id);
	
	public int updateIp(Vnet_ip i);
	
	public Vnet_ip  getByAddr(String addr);
	
	public int updateByIpAddr(Vnet_ip ii);
	
	public int updateByIpID(Vnet_ip ii);
	
	public String getInstanceIP(String instance_id);
	
	
	
}

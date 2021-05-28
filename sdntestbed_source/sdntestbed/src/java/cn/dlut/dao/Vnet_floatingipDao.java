/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_floatingip;
import cn.dlut.entity.Vnet_ip;


public interface Vnet_floatingipDao extends IBaseDao {

	public List<Vnet_floatingip> getAll();
	
	public Vnet_floatingip getById(Integer id);
	
	public int insertFloatingip(Vnet_floatingip i);
	
	public int delById(Integer id);
	
	public int updateFloatingip(Vnet_floatingip i);
	
	public int updateFloatingipByOsid(Vnet_floatingip ii);
	
	public Vnet_floatingip getRandomFloatingip(String status);
	
	public void setDownById(int id);
	
	public Vnet_floatingip getbyControllerid(String controller_id);
	
	public Vnet_floatingip getByOsid(String osid);
	
	public Vnet_floatingip getbyInsid(String ins_id);
	
	public Vnet_floatingip gethostfloatipbyInsid(String ins_id);
	
}

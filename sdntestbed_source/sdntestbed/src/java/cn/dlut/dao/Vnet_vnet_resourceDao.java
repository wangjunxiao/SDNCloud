/**
 * 
 */
package cn.dlut.dao;

import java.util.ArrayList;
import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_vnet_resource;

public interface Vnet_vnet_resourceDao extends IBaseDao {

	public List<Vnet_vnet_resource> getAll();
	
	public Vnet_vnet_resource getById(int id);
	
	public int insertVnetResource(Vnet_vnet_resource ii);
	
	public int deleleVnetInstance(int id);

	public ArrayList<String> getOflinkByVnet(String vnet_id);

	public ArrayList<String> getLinkByVnet(String vnet_id);

	public ArrayList<String> getSwitchByVnet(String vnet_id);

	public ArrayList<String> getControllerByVnet(String vnet_id);

	public ArrayList<String> getHostByVnet(String vnet_id);

	public void setDeleted(String string);

	
}

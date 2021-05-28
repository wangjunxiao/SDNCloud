/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_compute;
import cn.dlut.entity.Vnet_host;
import cn.dlut.entity.Vnet_image;

public interface Vnet_computeDao extends IBaseDao {

	public List<Vnet_compute> getAll();
	public Vnet_compute getById(int id);
	public int insertCompute(Vnet_compute c);
	public int delById(int id);
	
	public Vnet_compute getByHostId(String host_id);
	
	public Vnet_compute getBySwitchId(String switch_id);
	
	public Vnet_compute getByConId(String con_id);
	
	public int getByCompute_name(String compute_name);
}

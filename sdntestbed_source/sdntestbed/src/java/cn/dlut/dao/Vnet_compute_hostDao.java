/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_compute_host;
import cn.dlut.entity.Vnet_host;
import cn.dlut.entity.Vnet_compute;

public interface Vnet_compute_hostDao extends IBaseDao {

	public List<Vnet_compute_host> getAll();
	public Vnet_compute_host getById(int id);
	public int insertCompute_host(Vnet_compute_host ii);
	public int delById(int id);
	
	public Vnet_compute_host getByHostId(String host_id);

}

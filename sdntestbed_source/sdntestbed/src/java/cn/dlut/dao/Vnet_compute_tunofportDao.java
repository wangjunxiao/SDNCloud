/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_compute_instance;
import cn.dlut.entity.Vnet_compute_switch;
import cn.dlut.entity.Vnet_compute_tunofport;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

public interface Vnet_compute_tunofportDao extends IBaseDao {

	public List<Vnet_compute_tunofport> getAll();
	public Vnet_compute_tunofport getById(int id);
	public int insertCompute_tunofport(Vnet_compute_tunofport ii);
	public int delById(int id);
	
	public Vnet_compute_tunofport getByComputeId(Integer compute_id);

}

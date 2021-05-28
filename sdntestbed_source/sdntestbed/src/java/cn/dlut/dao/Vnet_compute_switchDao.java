/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_compute_instance;
import cn.dlut.entity.Vnet_compute_switch;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_image_instance;

public interface Vnet_compute_switchDao extends IBaseDao {

	public List<Vnet_compute_switch> getAll();
	public Vnet_compute_switch getById(int id);
	public int insertCompute_switch(Vnet_compute_switch ii);
	public int delById(int id);
	
	public Vnet_compute_switch getBySwitchId(String switch_id);

}

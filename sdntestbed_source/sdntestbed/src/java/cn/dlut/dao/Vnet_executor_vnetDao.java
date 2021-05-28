/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_controller;
import cn.dlut.entity.Vnet_executor_vnet;
import cn.dlut.entity.Vnet_image_instance;
import cn.dlut.entity.Vnet_vnet;

public interface Vnet_executor_vnetDao extends IBaseDao {

	public List<Vnet_executor_vnet> getAll();
	public Vnet_executor_vnet getById(int id);
	public int insertExecutor_vnet(Vnet_executor_vnet ev);
	public int delById(int id);
	public int delByVnet_id(String id);
	public Vnet_vnet getVnetByexe_id(int exe_id);
	public Vnet_executor_vnet getByexe_id(int exe_id);

}

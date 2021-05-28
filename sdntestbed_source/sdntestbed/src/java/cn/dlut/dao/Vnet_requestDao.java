/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.common.dao.IBaseDao;
import cn.dlut.entity.Vnet_request;

public interface Vnet_requestDao extends IBaseDao {

	public List<Vnet_request> getAll();
	public Vnet_request getById(int id);
	public int delById(int id);
	public int updateRequest(Vnet_request request);
	
	public int insertRequest(Vnet_request ii);

}

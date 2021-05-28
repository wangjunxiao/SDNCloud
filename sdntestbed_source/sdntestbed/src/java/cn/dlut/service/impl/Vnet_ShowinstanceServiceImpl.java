package cn.dlut.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.dlut.dao.Vnet_instanceDao;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.service.Vnet_ShowinstanceService;


@Service("Vnet_ShowinstanceService")
public class Vnet_ShowinstanceServiceImpl extends AbstractBaseService  implements Vnet_ShowinstanceService{
	@Autowired
	private Vnet_instanceDao instancedao;
	
	@Override
	public List<Vnet_instance> queryInstance() {
		return this.instancedao.getAll();
	}
}
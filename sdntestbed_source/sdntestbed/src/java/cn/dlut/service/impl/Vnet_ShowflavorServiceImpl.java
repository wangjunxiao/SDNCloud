package cn.dlut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_flavorDao;

import cn.dlut.entity.Vnet_flavor;
import cn.dlut.service.Vnet_ShowflavorService;


@Service("Vnet_ShowflavorService")
public class Vnet_ShowflavorServiceImpl extends AbstractBaseService  implements Vnet_ShowflavorService{
	@Autowired
	private Vnet_flavorDao flavordao;
	
	@Override
	public List<Vnet_flavor> queryFlavor() {
		return this.flavordao.getAll();
	}

}
package cn.dlut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_imageDao;

import cn.dlut.entity.Vnet_image;
import cn.dlut.service.Vnet_ShowimageService;


@Service("Vnet_ShowimageService")
public class Vnet_ShowimageServiceImpl extends AbstractBaseService  implements Vnet_ShowimageService{
	@Autowired
	private Vnet_imageDao imagedao;
	
	@Override
	public List<Vnet_image> queryImage() {
		return this.imagedao.getAll();
	}
}
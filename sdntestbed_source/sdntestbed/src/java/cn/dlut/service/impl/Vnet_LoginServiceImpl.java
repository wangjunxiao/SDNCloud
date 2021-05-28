package cn.dlut.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_executorDao;

import cn.dlut.entity.Vnet_executor;
import cn.dlut.service.Vnet_LoginService;


@Service("Vnet_LoginService")
public class Vnet_LoginServiceImpl extends AbstractBaseService  implements Vnet_LoginService{
	@Autowired
	private Vnet_executorDao executorDao;

	@Override
	public Vnet_executor Auth(String executor_name, String executor_password) {
		Map<String, String> nameAndPassword = new HashMap<String, String>();
		nameAndPassword.put("executor_name", executor_name);
		nameAndPassword.put("executor_password", executor_password);
		return executorDao.getByNameAndPassword(nameAndPassword);
	}
}
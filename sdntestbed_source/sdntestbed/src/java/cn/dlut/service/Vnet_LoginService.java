package cn.dlut.service;

import cn.dlut.entity.Vnet_executor;


public interface Vnet_LoginService {
	/*Return    State
	 * 0        "success"
	 * 1        "failed"
	 * */
	public Vnet_executor Auth(String executor_name,String executor_password);
}
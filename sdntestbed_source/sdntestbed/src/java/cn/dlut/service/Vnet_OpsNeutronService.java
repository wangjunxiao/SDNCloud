package cn.dlut.service;

import java.util.List;

import cn.dlut.entity.Vnet_flavor;

public interface Vnet_OpsNeutronService {
	public String ListSubnet();
	public String ShowSubnet(String subnet_id);
	public  String ListPort();
	public String ListFloatingip();
	public String ListUsedIp();
	public String ListIp();
//	public String getFlavorFromOps();
//	public  String getInstanceFromOps();
//	public  String getImageFromOps();
}
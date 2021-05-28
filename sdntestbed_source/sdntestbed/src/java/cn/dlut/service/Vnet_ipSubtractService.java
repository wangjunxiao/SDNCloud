package cn.dlut.service;

import java.util.ArrayList;




public interface Vnet_ipSubtractService {
/*	public ArrayList<String> subnetlistPara();
	public String floatingipPara();
	public String portlistPara();
	public String res();*/
	
	public void putIpIntoDB();
	public void getIpFromOps() ;
	public void getUsedIpFromOps();
	public void updateUsedIpIntoDB();
	public void getFloatingipFromOps();
	public void updateFloatingipIntoDB();
	public void putFloatingipIntoDB();
	public void InitIp();
}
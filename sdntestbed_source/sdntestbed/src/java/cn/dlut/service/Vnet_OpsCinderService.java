package cn.dlut.service;

public interface Vnet_OpsCinderService {
	
	public int Init();
	//public void InsertCtrlInfo();
	public void InsertFlowInfo();
	public void InsertHostInfo();
	public void InsertLinkInfo();
	public void InsertPortInfo();
	public void InsertPortStats(int num);
	public void InsertSwitchInfo();
	public void FloodInfoRmAndInsert();
	public void InsertCtrlInfo(String ctrl_url,int id);
	
}
package cn.dlut.service;


public interface Vnet_OpsHeatService {
	
	public void CreateStack();
	public String ListStack();
	public String ShowStack(String stackName, String stackID);
	public String ShowStack(String stackName);
	
	public void DeleteStack(String stackName, String stackID);
	public void SuspendStack(String stackName, String stackID);
	public void ResumeStack(String stackName, String stackID);
	
	public int createInstance(String instance_type,String temp_ins_id,String flavor_id,String image_id,String network_id,String subnet_id,String floatingip_id,String compute_zone);
	public Boolean deleteInstance(String stackName,String stackID,String compute_url);
	
}
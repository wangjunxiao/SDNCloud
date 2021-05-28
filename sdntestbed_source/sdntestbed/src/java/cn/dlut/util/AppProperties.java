package cn.dlut.util;

import java.util.ArrayList;

import cn.dlut.util.PropertiesFileLoader;

public class AppProperties {	
	//5.13雷创
	public static String Vnet_tun_size()
	{
		return propertiesFileLoader.get("Vnet_tun_size").toString();
	}
	
	public static String Vnet_floatingip_size()
	{
		return propertiesFileLoader.get("Vnet_floatingip_size").toString();
	}
	public static String Vnet_controller_ip_size()
	{
		return propertiesFileLoader.get("Vnet_controller_ip_size").toString();
	}
	public static String Vnet_host_ip_size()
	{
		return propertiesFileLoader.get("Vnet_host_ip_size").toString();
	}
	
	
	//--
	
	public static String getVnet_flow_table_id()
	{
		return propertiesFileLoader.get("Vnet_flow_table_id").toString();
	}
	
	private static PropertiesFileLoader propertiesFileLoader = new PropertiesFileLoader("constants.properties");
	
	public static String Vnet_floodlight_openflow_port()
	{
		return propertiesFileLoader.get("Vnet_floodlight_openflow_port").toString();
	}
	
	//3.29雷创
	public static String getVnet_controller_network_id()
	{
		return propertiesFileLoader.get("Vnet_controller_network_id").toString();
	}
	
	public static String getVnet_controller_subnet_id()
	{
		return propertiesFileLoader.get("Vnet_controller_subnet_id").toString();
	}
	
	public static String getVnet_host_network_id()
	{
		return propertiesFileLoader.get("Vnet_host_network_id").toString();
	}
	
	public static String getVnet_host_subnet_id()
	{
		return propertiesFileLoader.get("Vnet_host_subnet_id").toString();
	}
	//3.29雷创
	
	public static int Vnet_tun_from()
	{
		return Integer.parseInt(propertiesFileLoader.get("Vnet_tun_from").toString());
	}
	
	public static int Vnet_tun_to()
	{
		return Integer.parseInt(propertiesFileLoader.get("Vnet_tun_to").toString());
	}
	
	
	public static String Vnet_username(){
		return propertiesFileLoader.get("Vnet_username").toString();
	}
	public static String Vnet_userpassword(){
		return propertiesFileLoader.get("Vnet_userpassword").toString();
	}
	public static String Vnet_adminname(){
		return propertiesFileLoader.get("Vnet_adminname").toString();
	}
	public static String Vnet_adminpassword(){
		return propertiesFileLoader.get("Vnet_adminpassword").toString();
	}
	
	
	
	public static String Vnet_subnet_control(){
		return propertiesFileLoader.get("Vnet_subnet_control").toString();
	}
	public static String Vnet_subnet_host(){
		return propertiesFileLoader.get("Vnet_subnet_host").toString();
	}
	
	
	public static String getVnet_subnet_host()
	{
		return propertiesFileLoader.get("Vnet_subnet_host").toString();
	}
	public static String getVnet_subnet_control()
	{
		return propertiesFileLoader.get("Vnet_subnet_control").toString();
	}
	
	public static int getVnet_compute_num()
	{
		return Integer.parseInt(propertiesFileLoader.get("Vnet_compute_num").toString());
	}
	
	public static ArrayList<String> getVnet_details(int Vnet_compute_num)
	{
		ArrayList<String> result=new ArrayList<String>();
		for(int i=1;i<=Vnet_compute_num;i++)
		{
			String compute_name=propertiesFileLoader.get("Vnet_compute" + i + "_name").toString();
			String compute_addr=propertiesFileLoader.get("Vnet_compute" + i + "_addr").toString();
			String compute_ram=propertiesFileLoader.get("Vnet_compute" + i + "_ram").toString();
			String compute_cpu=propertiesFileLoader.get("Vnet_compute" + i + "_cpu").toString();
			result.add(compute_name);
			result.add(compute_addr);
			result.add(compute_ram);
			result.add(compute_cpu);			
		}
		return result;
	}
	
	//get a specific compute node url
	public static String getVnet_compute1_addr()
	{
		return propertiesFileLoader.get("Vnet_compute1_addr").toString();
	}
	
	public static String getVnet_compute1_name()
	{
		return propertiesFileLoader.get("Vnet_compute1_name").toString();
	}
	
	public static String getVnet_br()
	{
		return propertiesFileLoader.get("Vnet_br").toString();
	}

	public static String getVnet_control_addr(){
		return propertiesFileLoader.get("Vnet_control_addr").toString();
	}
	
	public static String getVnet_config_url(){
		return propertiesFileLoader.get("Vnet_config_url").toString();
	}
	
	public static String getVnet_tenant_demo(){
		return propertiesFileLoader.get("Vnet_tenant_demo").toString();
	}
	
	public static String Vnet_compute1_vnet_br(){
		return propertiesFileLoader.get("Vnet_compute1_vnet_br").toString();
	}
	
	public static String Vnet_compute2_vnet_br(){
		return propertiesFileLoader.get("Vnet_compute2_vnet_br").toString();
	}
	
	public static String getVnet_tomcat_server(){
		return propertiesFileLoader.get("Vnet_tomcat_server").toString();
	}
}

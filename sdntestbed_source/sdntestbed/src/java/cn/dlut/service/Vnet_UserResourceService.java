package cn.dlut.service;

import cn.dlut.entity.Vnet_userip;
import cn.dlut.entity.Vnet_script;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.entity.Vnet_usersubnet_userip;
import cn.dlut.entity.Vnet_vnet_userscript;
import cn.dlut.entity.Vnet_vnet_usersubnet;

public interface Vnet_UserResourceService {
	public int usersubnet_FlexToJava(Vnet_subnet item);
	public int userscript_FlexToJava(Vnet_script item);
	public int userip_FlexToJava(Vnet_userip item);	
	public int usersubnet_ipFlexToJava(Vnet_usersubnet_userip item);	
	public int vnet_subnet_FlexToJava(Vnet_vnet_usersubnet item);	
	public int vnet_script_FlexToJava(Vnet_vnet_userscript item);	
	public int usersubnet_Update(Vnet_subnet item);	
	public int usersubnet_Delete(String item);	
}
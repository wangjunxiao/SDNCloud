/**
 * 
 */
package cn.dlut.entity;


public class Vnet_vnet_usersubnet {

	private Integer vnet_usersubnet_id;
	private String vnet_id;
	private String usersubnet_id;
	
	
	public Integer getVnet_usersubnet_id() {
		return vnet_usersubnet_id;
	}
	public void setVnet_usersubnet_id(Integer vnet_usersubnet_id) {
		this.vnet_usersubnet_id = vnet_usersubnet_id;
	}
	public String getVnet_id() {
		return vnet_id;
	}
	public void setVnet_id(String vnet_id) {
		this.vnet_id = vnet_id;
	}
	
	public String getUsersubnet_id() {
		return usersubnet_id;
	}
	public void setUsersubnet_id(String usersubnet_id) {
		this.usersubnet_id = usersubnet_id;
	}
	@Override
	public String toString() {
		return "Vnet_vnet_usersubnet [vnet_usersubnet_id=" + vnet_usersubnet_id
				+ ", vnet_id=" + vnet_id + ", usersubnet_id=" + usersubnet_id
				+ "]";
	}

	

	
	

}

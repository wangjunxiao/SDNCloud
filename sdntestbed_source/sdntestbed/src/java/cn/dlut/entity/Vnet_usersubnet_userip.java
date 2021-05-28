/**
 * 
 */
package cn.dlut.entity;


public class Vnet_usersubnet_userip {

	private Integer usersubnet_userip_id;
	private String userip_id;
	private String usersubnet_id;
	public Integer getUsersubnet_userip_id() {
		return usersubnet_userip_id;
	}
	public void setUsersubnet_userip_id(Integer usersubnet_userip_id) {
		this.usersubnet_userip_id = usersubnet_userip_id;
	}
	public String getUserip_id() {
		return userip_id;
	}
	public void setUserip_id(String userip_id) {
		this.userip_id = userip_id;
	}
	public String getUsersubnet_id() {
		return usersubnet_id;
	}
	public void setUsersubnet_id(String usersubnet_id) {
		this.usersubnet_id = usersubnet_id;
	}
	@Override
	public String toString() {
		return "Vnet_usersubnet_userip [usersubnet_userip_id="
				+ usersubnet_userip_id + ", userip_id=" + userip_id
				+ ", usersubnet_id=" + usersubnet_id + "]";
	}
	
	
	

}

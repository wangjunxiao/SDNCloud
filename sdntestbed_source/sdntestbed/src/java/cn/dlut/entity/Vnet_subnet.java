/**
 * 
 */
package cn.dlut.entity;


public class Vnet_subnet {

	private String usersubnet_id;
	private String usersubnet_name;
	private String usersubnet_type;
	private String usersubnet_addr;
	private String usersubnet_mask;
	
	public Vnet_subnet(){
	}
	public String getUsersubnet_id() {
		return usersubnet_id;
	}
	public void setUsersubnet_id(String usersubnet_id) {
		this.usersubnet_id = usersubnet_id;
	}
	public String getUsersubnet_name() {
		return usersubnet_name;
	}
	public void setUsersubnet_name(String usersubnet_name) {
		this.usersubnet_name = usersubnet_name;
	}
	public String getUsersubnet_type() {
		return usersubnet_type;
	}
	public void setUsersubnet_type(String usersubnet_type) {
		this.usersubnet_type = usersubnet_type;
	}
	public String getUsersubnet_addr() {
		return usersubnet_addr;
	}
	public void setUsersubnet_addr(String usersubnet_addr) {
		this.usersubnet_addr = usersubnet_addr;
	}
	public String getUsersubnet_mask() {
		return usersubnet_mask;
	}
	public void setUsersubnet_mask(String usersubnet_mask) {
		this.usersubnet_mask = usersubnet_mask;
	}
	@Override
	public String toString() {
		return "Vnet_usersubnet [usersubnet_id=" + usersubnet_id
				+ ", usersubnet_name=" + usersubnet_name + ", usersubnet_type="
				+ usersubnet_type + ", usersubnet_addr=" + usersubnet_addr
				+ ", usersubnet_mask=" + usersubnet_mask + "]";
	}
	
	
	
	
}
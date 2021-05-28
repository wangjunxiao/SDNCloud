/**
 * 
 */
package cn.dlut.entity;


public class Vnet_userip {

	private String userip_id;
	private String userip_addr;
	private String userip_mask;
	private String host_id;
	private String osport_id;
	
	
	public String getHost_id() {
		return host_id;
	}
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}
	public String getOsport_id() {
		return osport_id;
	}
	public void setOsport_id(String osport_id) {
		this.osport_id = osport_id;
	}
	public String getUserip_id() {
		return userip_id;
	}
	public void setUserip_id(String userip_id) {
		this.userip_id = userip_id;
	}
	public String getUserip_addr() {
		return userip_addr;
	}
	public void setUserip_addr(String userip_addr) {
		this.userip_addr = userip_addr;
	}
	public String getUserip_mask() {
		return userip_mask;
	}
	public void setUserip_mask(String userip_mask) {
		this.userip_mask = userip_mask;
	}
	@Override
	public String toString() {
		return "Vnet_userip [userip_id=" + userip_id + ", userip_addr="
				+ userip_addr + ", userip_mask=" + userip_mask + "]";
	}
	
	
}

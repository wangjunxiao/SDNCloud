/**
 * 
 */
package cn.dlut.entity;


public class Vnet_vnet {

	private String vnet_id;
	private String vnet_name;
	private String vnet_status;

	
	public Vnet_vnet(){
	}
	
	

	public String getVnet_id() {
		return vnet_id;
	}



	public void setVnet_id(String vnet_id) {
		this.vnet_id = vnet_id;
	}



	public String getVnet_name() {
		return vnet_name;
	}



	public void setVnet_name(String vnet_name) {
		this.vnet_name = vnet_name;
	}


	public String getVnet_status() {
		return vnet_status;
	}



	public void setVnet_status(String vnet_status) {
		this.vnet_status = vnet_status;
	}



	@Override
	public String toString() {
		return "Vnet [vnet_id=" + vnet_id + ", vnet_name="
				+ vnet_name + ", vnet_status=" + vnet_status+ "]";
	}

}

/**
 * 
 */
package cn.dlut.entity;


public class Vnet_instance_host {

	private Integer instance_host_id;
	private String instance_id;
	private String host_id;
	


	
	public Vnet_instance_host() {
		
	}
	



	public Integer getInstance_host_id() {
		return instance_host_id;
	}




	public void setInstance_host_id(Integer instance_host_id) {
		this.instance_host_id = instance_host_id;
	}




	public String getInstance_id() {
		return instance_id;
	}




	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}




	public String getHost_id() {
		return host_id;
	}




	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}




	@Override
	public String toString() {
		return "Instance_host [instance_host_id=" + instance_host_id 
			   + ", instance_id=" + instance_id + ", host_id="
				+ host_id  + "]";
	}

}

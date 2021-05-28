/**
 * 
 */
package cn.dlut.entity;


public class Vnet_host {

	private String host_id;
	private String host_name;
	private Integer host_config;
	private Integer floatingip_id;
	
	public Vnet_host(String host_id,String host_name,Integer host_config,Integer floatingip_id){
		this.host_id = host_id;
		this.host_name = host_name;
		this.host_config = host_config;
		this.floatingip_id = floatingip_id;
	}

	public Vnet_host(){
	}
	
	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	
	public Integer getHost_config(){
		return host_config;
	}
	
	public void setHost_config(Integer host_config){
		this.host_config = host_config;
	}
	
	public Integer getFloatingip_id(){
		return floatingip_id;
	}
	
	public void setFloatingip_id(Integer floatingip_id){
		this.floatingip_id = floatingip_id;
	}

	@Override
	public String toString() {
		return "Host [host_id=" + host_id + ", host_name="
				+ host_name +",host_config=" + host_config + ",floatingip_id=" + floatingip_id + "]";
	}

}

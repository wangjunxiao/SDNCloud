/**
 * 
 */
package cn.dlut.entity;


public class Vnet_compute_host {

	private Integer compute_host_id;
	private Integer compute_id;
	private String host_id;
	
	public Vnet_compute_host(){
		
	}
	
	public Vnet_compute_host(int compute_id,String host_id)
	{
		this.compute_id=compute_id;
		this.host_id=host_id;
	}

	public Integer getCompute_host_id() {
		return compute_host_id;
	}

	public void setCompute_host_id(Integer compute_host_id) {
		this.compute_host_id = compute_host_id;
	}

	public Integer getCompute_id() {
		return compute_id;
	}

	public void setCompute_id(Integer compute_id) {
		this.compute_id = compute_id;
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	@Override
	public String toString() {
		return "Vnet_compute_host [compute_host_id="
				+ compute_host_id + ", compute_id=" + compute_id
				+ ", host_id=" + host_id + "]";
	}

}
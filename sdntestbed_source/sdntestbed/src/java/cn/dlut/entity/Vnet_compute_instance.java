/**
 * 
 */
package cn.dlut.entity;


public class Vnet_compute_instance {

	private Integer compute_instance_id;
	private String compute_id;
	private String instance_id;

	

	public Integer getCompute_instance_id() {
		return compute_instance_id;
	}



	public void setCompute_instance_id(Integer compute_instance_id) {
		this.compute_instance_id = compute_instance_id;
	}



	public String getCompute_id() {
		return compute_id;
	}



	public void setCompute_id(String compute_id) {
		this.compute_id = compute_id;
	}



	public String getInstance_id() {
		return instance_id;
	}



	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}



	@Override
	public String toString() {
		return "Vnet_compute_instance [compute_instance_id="
				+ compute_instance_id + ", compute_id=" + compute_id
				+ ", instance_id=" + instance_id + "]";
	}




}
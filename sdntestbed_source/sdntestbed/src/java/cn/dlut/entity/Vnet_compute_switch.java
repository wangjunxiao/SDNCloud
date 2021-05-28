/**
 * 
 */
package cn.dlut.entity;


public class Vnet_compute_switch {

	private Integer compute_switch_id;
	private Integer compute_id;
	private String switch_id;

	

	public Vnet_compute_switch()
	{
		
	}
	
	public Vnet_compute_switch(int compute_id,String switch_id)
	{
		this.compute_id=compute_id;
		this.switch_id=switch_id;
	}
	
	public Integer getCompute_switch_id() {
		return compute_switch_id;
	}




	public void setCompute_switch_id(Integer compute_switch_id) {
		this.compute_switch_id = compute_switch_id;
	}




	public Integer getCompute_id() {
		return compute_id;
	}




	public void setCompute_id(Integer compute_id) {
		this.compute_id = compute_id;
	}




	public String getSwitch_id() {
		return switch_id;
	}




	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}




	@Override
	public String toString() {
		return "Vnet_compute_switch [compute_switch_id=" + compute_switch_id
				+ ", compute_id=" + compute_id + ", switch_id=" + switch_id
				+ "]";
	}






}
/**
 * 
 */
package cn.dlut.entity;


public class Vnet_compute_controller {

	private Integer compute_controller_id;
	private Integer compute_id;
	private String controller_id;

	public Vnet_compute_controller()
	{
		
	}
	
	public Vnet_compute_controller(int compute_id,String controller_id)
	{
		this.compute_id=compute_id;
		this.controller_id=controller_id;
	}
	
	public Integer getCompute_controller_id() {
		return compute_controller_id;
	}

	public void setCompute_controller_id(Integer compute_controller_id) {
		this.compute_controller_id = compute_controller_id;
	}
	public Integer getCompute_id() {
		return compute_id;
	}

	public void setCompute_id(Integer compute_id) {
		this.compute_id = compute_id;
	}



	public String getController_id() {
		return controller_id;
	}



	public void setController_id(String controller_id) {
		this.controller_id = controller_id;
	}



	@Override
	public String toString() {
		return "Vnet_compute_controller [compute_controller_id="
				+ compute_controller_id + ", compute_id=" + compute_id
				+ ", controller_id=" + controller_id + "]";
	}




}
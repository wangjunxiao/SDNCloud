/**
 * 
 */
package cn.dlut.entity;


public class Vnet_instance_controller {

	private Integer instance_controller_id;
	private String instance_id;
	private String controller_id;
	

	
	public Vnet_instance_controller() {
		
	}


	public Integer getInstance_controller_id() {
		return instance_controller_id;
	}





	public void setInstance_controller_id(Integer instance_controller_id) {
		this.instance_controller_id = instance_controller_id;
	}





	public String getInstance_id() {
		return instance_id;
	}





	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}





	public String getController_id() {
		return controller_id;
	}





	public void setController_id(String controller_id) {
		this.controller_id = controller_id;
	}





	@Override
	public String toString() {
		return "Instance_controller [instance_controller_id=" + instance_controller_id + ", instance_id=" + instance_id + ", controller_id="
				+ controller_id  + "]";
	}

}

/**
 * 
 */
package cn.dlut.entity;


public class Vnet_controller {

	private String controller_id;
	private String controller_name;
	private String controller_type;
	private Integer floatingip_id;

	public Vnet_controller(String controller_id, String controller_name,String controller_type, Integer floatingip_id)
	{
		this.controller_id=controller_id;
		this.controller_name=controller_name;
		this.controller_type=controller_type;
		this.floatingip_id=floatingip_id;
	}
	
	public Vnet_controller() {
		
	}
	

	public Integer getFloatingip_id() {
		return floatingip_id;
	}


	public void setFloatingip_id(Integer floatingip_id) {
		this.floatingip_id = floatingip_id;
	}


	public String getController_id() {
		return controller_id;
	}



	public void setController_id(String controller_id) {
		this.controller_id = controller_id;
	}



	public String getController_name() {
		return controller_name;
	}



	public void setController_name(String controller_name) {
		this.controller_name = controller_name;
	}





	public String getController_type() {
		return controller_type;
	}



	public void setController_type(String controller_type) {
		this.controller_type = controller_type;
	}



	@Override
	public String toString() {
		return "Controller [controller_id=" + controller_id + ", controller_name=" + controller_name
		        + ", controller_type=" + controller_type 
		        + ", floatingip_id=" + floatingip_id + "]";
	}

}

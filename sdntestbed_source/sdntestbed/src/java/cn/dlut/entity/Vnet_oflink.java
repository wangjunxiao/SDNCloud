/**
 * 
 */
package cn.dlut.entity;


public class Vnet_oflink {

	private String oflink_id;
	private String switch_id;
	private String controller_id;
	private String oflink_status;
	public String getOflink_id() {
		return oflink_id;
	}
	public void setOflink_id(String oflink_id) {
		this.oflink_id = oflink_id;
	}
	public String getSwitch_id() {
		return switch_id;
	}
	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}
	public String getController_id() {
		return controller_id;
	}
	public void setController_id(String controller_id) {
		this.controller_id = controller_id;
	}
	public String getOflink_status() {
		return oflink_status;
	}
	public void setOflink_status(String oflink_status) {
		this.oflink_status = oflink_status;
	}
	@Override
	public String toString() {
		return "Vnet_oflink [oflink_id=" + oflink_id + ", switch_id="
				+ switch_id + ", controller_id=" + controller_id
				+ ", oflink_status=" + oflink_status + "]";
	}

	



}

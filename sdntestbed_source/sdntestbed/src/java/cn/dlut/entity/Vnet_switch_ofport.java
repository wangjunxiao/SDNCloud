/**
 * 
 */
package cn.dlut.entity;


public class Vnet_switch_ofport {

	private Integer switch_ofport_id;
	private String switch_id;
	private String ofport_id;
	public Integer getSwitch_ofport_id() {
		return switch_ofport_id;
	}
	public void setSwitch_ofport_id(Integer switch_ofport_id) {
		this.switch_ofport_id = switch_ofport_id;
	}
	public String getSwitch_id() {
		return switch_id;
	}
	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}
	public String getOfport_id() {
		return ofport_id;
	}
	public void setOfport_id(String ofport_id) {
		this.ofport_id = ofport_id;
	}
	@Override
	public String toString() {
		return "Vnet_switch_ofport [switch_ofport_id=" + switch_ofport_id
				+ ", switch_id=" + switch_id + ", ofport_id=" + ofport_id + "]";
	}
	
	



}

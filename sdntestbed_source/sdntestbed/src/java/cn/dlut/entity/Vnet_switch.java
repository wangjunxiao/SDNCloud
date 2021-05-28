/**
 * 
 */
package cn.dlut.entity;


public class Vnet_switch {

	private String switch_id;
	private String switch_name;
	private Integer switch_counter;
	private String switch_status;
	
	public String getSwitch_status() {
		return switch_status;
	}

	public void setSwitch_status(String switch_status) {
		this.switch_status = switch_status;
	}

	public Vnet_switch()
	{}
	
	public Vnet_switch(String switch_id,String switch_name,Integer switch_counter)
	{
		this.switch_id=switch_id;
		this.switch_name=switch_name;
		this.switch_counter=switch_counter;
	}
	
	public Vnet_switch(String switch_id,String switch_name,Integer switch_counter,String switch_status)
	{
		this.switch_id=switch_id;
		this.switch_name=switch_name;
		this.switch_counter=switch_counter;
		this.switch_status=switch_status;
	}
	

	public String getSwitch_id() {
		return switch_id;
	}



	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}



	public String getSwitch_name() {
		return switch_name;
	}



	public void setSwitch_name(String switch_name) {
		this.switch_name = switch_name;
	}


	public Integer getSwitch_counter() {
		return switch_counter;
	}



	public void setSwitch_counter(Integer switch_counter) {
		this.switch_counter = switch_counter;
	}



	@Override
	public String toString() {
		return "Vnet_switch [switch_id=" + switch_id + ", switch_name="
				+ switch_name + ", switch_counter=" + switch_counter + "]";
	}

	
}

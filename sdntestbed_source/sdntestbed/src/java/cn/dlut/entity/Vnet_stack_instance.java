/**
 * 
 */
package cn.dlut.entity;


public class Vnet_stack_instance {

	private Integer stack_instance_id;
	private Integer stack_id;
	private String instance_id;
	public Vnet_stack_instance(){}
	
	public Vnet_stack_instance(Integer stack_id,String instance_id)
	{
		this.stack_id=stack_id;
		this.instance_id=instance_id;
	}
	
	public Integer getStack_instance_id() {
		return stack_instance_id;
	}
	public void setStack_instance_id(Integer stack_instance_id) {
		this.stack_instance_id = stack_instance_id;
	}
	public Integer getStack_id() {
		return stack_id;
	}
	public void setStack_id(Integer stack_id) {
		this.stack_id = stack_id;
	}
	public String getInstance_id() {
		return instance_id;
	}
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}
	@Override
	public String toString() {
		return "Vnet_stack_instance [stack_instance_id=" + stack_instance_id
				+ ", stack_id=" + stack_id + ", instance_id=" + instance_id
				+ "]";
	}
	

}

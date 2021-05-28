/**
 * 
 */
package cn.dlut.entity;


public class Vnet_stack {


	private Integer stack_id;
	private String stack_osid;
	private String stack_name;
	private String stack_type;
	private String stack_status;
	
	public Vnet_stack(){}
	
	public Vnet_stack(Integer stack_id,String stack_osid,String stack_name,String stack_type,String stack_status)
	{
		this.stack_id=stack_id;
		this.stack_osid=stack_osid;
		this.stack_name=stack_name;
		this.stack_type=stack_type;
		this.stack_status=stack_status;
	}
	
	
	
	public Integer getStack_id() {
		return stack_id;
	}
	
	
	public void setStack_id(Integer stack_id) {
		this.stack_id = stack_id;
	}
	public String getStack_osid() {
		return stack_osid;
	}
	public void setStack_osid(String stack_osid) {
		this.stack_osid = stack_osid;
	}
	public String getStack_name() {
		return stack_name;
	}
	public void setStack_name(String stack_name) {
		this.stack_name = stack_name;
	}
	public String getStack_type() {
		return stack_type;
	}
	public void setStack_type(String stack_type) {
		this.stack_type = stack_type;
	}
	public String getStack_status() {
		return stack_status;
	}
	public void setStack_status(String stack_status) {
		this.stack_status = stack_status;
	}
	@Override
	public String toString() {
		return "Vnet_stack [stack_id=" + stack_id + ", stack_osid="
				+ stack_osid + ", stack_name=" + stack_name + ", stack_type="
				+ stack_type + ", stack_status=" + stack_status + "]";
	}
	
	




}

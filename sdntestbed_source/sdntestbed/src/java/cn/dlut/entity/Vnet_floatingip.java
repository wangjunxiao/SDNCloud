/**
 * 
 */
package cn.dlut.entity;


public class Vnet_floatingip {

	private Integer floatingip_id;
	private String floatingip_addr;
	private String floatingip_status;
	private String floatingip_osid;
	
	
	
	public Vnet_floatingip(){}

	public Vnet_floatingip(String floatingip_addr,String floatingip_osid,String floatingip_status)
	{
		this.floatingip_addr=floatingip_addr;
		this.floatingip_status=floatingip_status;
		this.floatingip_osid=floatingip_osid;
	}
	public Vnet_floatingip(String floating_ip_address, String status) {
		this.floatingip_addr=floating_ip_address;
		this.floatingip_status=status;
	
		
		// TODO Auto-generated constructor stub
	}


	public Integer getFloatingip_id() {
		return floatingip_id;
	}


	public void setFloatingip_id(Integer floatingip_id) {
		this.floatingip_id = floatingip_id;
	}


	public String getFloatingip_addr() {
		return floatingip_addr;
	}


	public void setFloatingip_addr(String floatingip_addr) {
		this.floatingip_addr = floatingip_addr;
	}


	public String getFloatingip_status() {
		return floatingip_status;
	}


	public void setFloatingip_status(String floatingip_status) {
		this.floatingip_status = floatingip_status;
	}


	@Override
	public String toString() {
		return "Floatingip [floatingip_id=" + floatingip_id + ", floatingip_addr=" + floatingip_addr+  ", floatingip_status=" + floatingip_status + "]";
	}


	public void setFloatingip_osid(String floatingip_osid) {
		this.floatingip_osid = floatingip_osid;
	}


	public String getFloatingip_osid() {
		return floatingip_osid;
	}

}

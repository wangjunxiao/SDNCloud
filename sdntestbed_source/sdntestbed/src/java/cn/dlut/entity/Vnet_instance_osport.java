package cn.dlut.entity;

public class Vnet_instance_osport {

	private Integer instance_osport_id;
	private String instance_id;
	private String osport_id;
	public void setInstance_osport_id(Integer instance_osport_id) {
		this.instance_osport_id = instance_osport_id;
	}
	public Integer getInstance_osport_id() {
		return instance_osport_id;
	}
	
	public Vnet_instance_osport(String osport_id,String instance_id)
	{
		this.instance_id=instance_id;
		this.osport_id=osport_id;
	}
	
	public Vnet_instance_osport()
	{
		
	}
	
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}
	public String getInstance_id() {
		return instance_id;
	}
	public void setOsport_id(String osport_id) {
		this.osport_id = osport_id;
	}
	public String getOsport_id() {
		return osport_id;
	}
	
	@Override
	public String toString() {
		return "Instance_osport =" + instance_osport_id 
			   + ", instance_id=" + instance_id + ", osport_id="
				+ osport_id  + "]";
	}
	
}

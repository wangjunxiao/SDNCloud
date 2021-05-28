/**
 * 
 */
package cn.dlut.entity;


public class Vnet_instance {

	private String instance_id;
	private String instance_osid;
	private String instance_name;
	private String instance_type;
	private String instance_zone;
	private String instance_status;
	
	public Vnet_instance(){
	}
	public Vnet_instance(String instance_id,String instance_osid,String instance_status)
	{
		this.instance_id=instance_id;
		this.instance_osid=instance_osid;
		this.instance_status=instance_status;
	}
	
	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String id) {
		this.instance_id = id;
	}

	public String getInstance_osid() {
		return instance_osid;
	}

	public void setInstance_osid(String osid) {
		this.instance_osid = osid;
	}

	public String getInstance_name() {
		return instance_name;
	}

	public void setInstance_name(String instance_name) {
		this.instance_name = instance_name;
	}
	
	public String getInstance_type() {
		return instance_type;
	}

	public void setInstance_type(String instance_type) {
		this.instance_type = instance_type;
	}

	public String getInstance_zone() {
		return instance_zone;
	}

	public void setInstance_zone(String instance_zone) {
		this.instance_zone = instance_zone;
	}

	public String getInstance_status() {
		return instance_status;
	}

	public void setInstance_status(String instance_status) {
		this.instance_status = instance_status;
	}
	
	@Override
	public String toString() {
		return "Instance [instance_id=" + instance_id + ", instance_osid=" + instance_osid + ", instance_name="
				+ instance_name + ", instance_type=" + instance_type + ", instance_zone=" + instance_zone +
				", instance_status=" + instance_status + "]";
	}

}

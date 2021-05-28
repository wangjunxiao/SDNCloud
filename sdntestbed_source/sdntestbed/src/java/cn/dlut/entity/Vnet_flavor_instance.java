package cn.dlut.entity;

public class Vnet_flavor_instance {

	private Integer flavor_instance_id;
	private Integer flavor_id;
	private String instance_id;
	public void setFlavor_id(Integer flavor_id) {
		this.flavor_id = flavor_id;
	}
	public Integer getFlavor_id() {
		return flavor_id;
	}
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}
	public String getInstance_id() {
		return instance_id;
	}
	public void setFlavor_instance_id(Integer flavor_instance_id) {
		this.flavor_instance_id = flavor_instance_id;
	}
	public Integer getFlavor_instance_id() {
		return flavor_instance_id;
	}
	
	@Override
	public String toString()
	{
		return "Flavor_instance [flavor_instance_id=" + flavor_instance_id + 
		", flavor_id=" + flavor_id + ", instance_id=" + instance_id + "]";
	}
	

}

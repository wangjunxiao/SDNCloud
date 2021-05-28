/**
 * 
 */
package cn.dlut.entity;


public class Vnet_flavor {


	private Integer flavor_id;
	private String flavor_osid;
	private String flavor_name;
	private Integer flavor_vcpus;
	private Integer flavor_ram;
	private Integer flavor_rootdisk;
	private Integer flavor_ephemeraldisk;
	private Integer flavor_swapdisk;
	
	public Integer getFlavor_rootdisk() {
		return flavor_rootdisk;
	}
	public Vnet_flavor(String name)
	{
		this.flavor_name=name;
		this.flavor_osid="osid";
		//this.flavor_name=f.flavor_name;
		this.flavor_vcpus=11;
		this.flavor_ram=11;
		this.flavor_rootdisk=11;
		this.flavor_ephemeraldisk=11;
		this.flavor_swapdisk=11;
	}
	public Vnet_flavor(Vnet_flavor f)
	{
		this.flavor_osid=f.flavor_osid;
		this.flavor_name=f.flavor_name;
		this.flavor_vcpus=f.flavor_vcpus;
		this.flavor_ram=f.flavor_ram;
		this.flavor_rootdisk=f.flavor_rootdisk;
		this.flavor_ephemeraldisk=f.flavor_ephemeraldisk;
		this.flavor_swapdisk=f.flavor_swapdisk;
	}
	public Vnet_flavor(String name,String osid)
	{
		this.flavor_name=name;
		this.flavor_osid=osid;
	}

	public void setFlavor_rootdisk(Integer flavor_rootdisk) {
		this.flavor_rootdisk = flavor_rootdisk;
	}

	public Integer getFlavor_ephemeraldisk() {
		return flavor_ephemeraldisk;
	}

	public void setFlavor_ephemeraldisk(Integer flavor_ephemeraldisk) {
		this.flavor_ephemeraldisk = flavor_ephemeraldisk;
	}
	
	public Vnet_flavor(){
	}
	
	public Integer getFlavor_id() {
		return flavor_id;
	}

	public void setFlavor_id(Integer flavor_id) {
		this.flavor_id = flavor_id;
	}

	public String getFlavor_osid() {
		return flavor_osid;
	}

	public void setFlavor_osid(String flavor_osid) {
		this.flavor_osid = flavor_osid;
	}

	public String getFlavor_name() {
		return flavor_name;
	}

	public void setFlavor_name(String flavor_name) {
		this.flavor_name = flavor_name;
	}

	public Integer getFlavor_vcpus() {
		return flavor_vcpus;
	}

	public void setFlavor_vcpus(Integer flavor_vcpus) {
		this.flavor_vcpus = flavor_vcpus;
	}

	public Integer getFlavor_ram() {
		return flavor_ram;
	}

	public void setFlavor_ram(Integer flavor_ram) {
		this.flavor_ram = flavor_ram;
	}

	public Integer getFlavor_swapdisk() {
		return flavor_swapdisk;
	}

	public void setFlavor_swapdisk(Integer flavor_swapdisk) {
		this.flavor_swapdisk = flavor_swapdisk;
	}	
	
	@Override
	public String toString() {
		return "Flavor " + flavor_id + " " + flavor_osid + " " + flavor_name + " "
		+ flavor_vcpus + " " + flavor_ram + " " + flavor_rootdisk + " " + 
		flavor_ephemeraldisk + " " + flavor_swapdisk ;
	}


}

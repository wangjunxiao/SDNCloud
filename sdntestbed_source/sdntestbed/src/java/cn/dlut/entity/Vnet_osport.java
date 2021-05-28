/**
 * 
 */
package cn.dlut.entity;


public class Vnet_osport {


	private String osport_id;
	private String osport_osid;
	private String osport_name;
	private String osport_type;
	private Integer ip_id;
	private String osport_status;
	public String getOsport_id() {
		return osport_id;
	}
	public Vnet_osport(String osport_id,String osport_osid,String osport_name,String osport_type,String osport_status,Integer ip_id)
	{
		
		this.osport_id=osport_id;
		this.osport_osid=osport_osid;
		this.osport_name=osport_name;
		this.osport_type=osport_type;
		this.osport_status=osport_status;
		this.ip_id=ip_id;
		
	}
	
	public Vnet_osport()
	{
		
	}
	
	public void setOsport_id(String osport_id) {
		this.osport_id = osport_id;
	}
	public String getOsport_osid() {
		return osport_osid;
	}
	public void setOsport_osid(String osport_osid) {
		this.osport_osid = osport_osid;
	}
	public String getOsport_name() {
		return osport_name;
	}
	public void setOsport_name(String osport_name) {
		this.osport_name = osport_name;
	}
	public String getOsport_type() {
		return osport_type;
	}
	public void setOsport_type(String osport_type) {
		this.osport_type = osport_type;
	}
	public Integer getIp_id() {
		return ip_id;
	}
	public void setIp_id(Integer ip_id) {
		this.ip_id = ip_id;
	}
	public String getOsport_status() {
		return osport_status;
	}
	public void setOsport_status(String osport_status) {
		this.osport_status = osport_status;
	}
	@Override
	public String toString() {
		return "Vnet_osport [osport_id=" + osport_id + ", osport_osid="
				+ osport_osid + ", osport_name=" + osport_name
				+ ", osport_type=" + osport_type + ", ip_id=" + ip_id
				+ ", osport_status=" + osport_status + "]";
	}
	
	
}

package cn.dlut.entity;

import java.sql.Timestamp;
public class Vnet_vnet_resource {

	private Integer vnet_resource_id;
	private String vnet_id;
	private String resource_id;
	private String resource_type;
	private String resource_status;
	private Timestamp resource_timestamp;
	public void setVnet_resource_id(Integer vnet_resource_id) {
		this.vnet_resource_id = vnet_resource_id;
	}
	public Integer getVnet_resource_id() {
		return vnet_resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setVnet_id(String vnet_id) {
		this.vnet_id = vnet_id;
	}
	public String getVnet_id() {
		return vnet_id;
	}
	@Override
	public String toString() {
		return "vnet_resource [vnet_resource_id=" + vnet_resource_id + ", vnet_id=" + vnet_id
		       + ", resource_id=" + resource_id + ", resource_type" + resource_type + 
		       ", resource_timestamp" + resource_timestamp + "]";
	}
	public void setResource_status(String resource_status) {
		this.resource_status = resource_status;
	}
	public String getResource_status() {
		return resource_status;
	}
	public void setResource_timestamp(Timestamp resource_timestamp) {
		this.resource_timestamp = resource_timestamp;
	}
	public Timestamp getResource_timestamp() {
		return resource_timestamp;
	}
}

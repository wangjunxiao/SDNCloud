/**
 * 
 */
package cn.dlut.entity;

public class Vnet_request {

	private Integer request_id;
	private String request_type;
	private String request_name;
	private String content_id;
	private String request_status;
	private String vnet_id;
	private String request_group;
	private String request_timestamp;
	
	public Vnet_request() {
		
	}
	

	public Integer getRequest_id() {
		return request_id;
	}

	public void setRequest_id(Integer request_id) {
		this.request_id = request_id;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getRequest_name() {
		return request_name;
	}

	public void setRequest_name(String request_name) {
		this.request_name = request_name;
	}

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}


	public String getVnet_id() {
		return vnet_id;
	}


	public void setVnet_id(String vnet_id) {
		this.vnet_id = vnet_id;
	}


	public String getRequest_group() {
		return request_group;
	}


	public void setRequest_group(String request_group) {
		this.request_group = request_group;
	}


	@Override
	public String toString() {
		return "Vnet_request [request_id=" + request_id + ", request_type="
				+ request_type + ", request_name=" + request_name
				+ ", content_id=" + content_id + ", request_status="
				+ request_status + ", vnet_id=" + vnet_id + ", request_group="
				+ request_group + ", request_timestamp=" + request_timestamp + "]";
	}


	public void setRequest_timestamp(String request_timestamp) {
		this.request_timestamp = request_timestamp;
	}


	public String getRequest_timestamp() {
		return request_timestamp;
	}


}

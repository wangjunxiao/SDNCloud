/**
 * 
 */
package cn.dlut.entity;


public class Vnet_ip {

	private Integer ip_id;
	private String ip_net;
	private String ip_status;
	private String ip_addr;
	
	public Vnet_ip(String ip_net,String ip_status,String ip_addr)
	{
		this.ip_net=ip_net;
		this.ip_status=ip_status;
		this.ip_addr=ip_addr;
	}
	
	public Vnet_ip(String ip_addr,String ip_status)
	{
	
		this.ip_addr=ip_addr;
		this.ip_status=ip_status;
	}
	
	
	public Vnet_ip() {
		// TODO Auto-generated constructor stub
	}


	public Integer getIp_id() {
		return ip_id;
	}

	public void setIp_id(Integer ip_id) {
		this.ip_id = ip_id;
	}



	public String getIp_status() {
		return ip_status;
	}

	public void setIp_status(String ip_status) {
		this.ip_status = ip_status;
	}

	public String getIp_addr() {
		return ip_addr;
	}

	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

	@Override
	public String toString() {
		return "Ip [ip_id=" + ip_id + ", ip_net=" + ip_net + ", ip_addr="
				+ ip_addr + ", ip_status=" + ip_status + "]";
	}

	public void setIp_net(String ip_net) {
		this.ip_net = ip_net;
	}

	public String getIp_net() {
		return ip_net;
	}

}

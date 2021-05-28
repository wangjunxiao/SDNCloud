/**
 * 
 */
package cn.dlut.entity;


public class Vnet_tun {

	private Integer tun_id;
	private String tun_tag;
	private String tun_status;
	public Vnet_tun(){
		
	}
	public Vnet_tun(String tun_tag,String tun_status){
		this.tun_tag=tun_tag;
		this.tun_status=tun_status;
		
	}
	public String getTun_status() {
		return tun_status;
	}
	public void setTun_status(String tun_status) {
		this.tun_status = tun_status;
	}
	public Integer getTun_id() {
		return tun_id;
	}
	public void setTun_id(Integer tun_id) {
		this.tun_id = tun_id;
	}
	public String getTun_tag() {
		return tun_tag;
	}
	public void setTun_tag(String tun_tag) {
		this.tun_tag = tun_tag;
	}
	@Override
	public String toString() {
		return "Vnet_tun [tun_id=" + tun_id + ", tun_tag=" + tun_tag
				+ ", tun_status=" + tun_status + "]";
	}
	
	

}

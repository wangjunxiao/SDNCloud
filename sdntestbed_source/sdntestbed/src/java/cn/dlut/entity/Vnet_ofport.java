package cn.dlut.entity;

public class Vnet_ofport {

	private String ofport_id;
	private Integer ofport_portnum;
	private String ofport_status;
	
	public Vnet_ofport(){
		
	}
	
	
	
	
	public String getOfport_id() {
		return ofport_id;
	}




	public void setOfport_id(String ofport_id) {
		this.ofport_id = ofport_id;
	}




	public Integer getOfport_portnum() {
		return ofport_portnum;
	}




	public void setOfport_portnum(Integer ofport_portnum) {
		this.ofport_portnum = ofport_portnum;
	}




	public String getOfport_status() {
		return ofport_status;
	}




	public void setOfport_status(String ofport_status) {
		this.ofport_status = ofport_status;
	}




	@Override
	public String toString() {
		return "Vnet_ofport [ofport_id=" + ofport_id + ", ofport_portnum="
				+ ofport_portnum + ", ofport_status=" + ofport_status + "]";
	}
	

}

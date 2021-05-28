/**
 * 
 */
package cn.dlut.entity;


public class Vnet_tunofport {

	private String tunofport_id;
	private Integer tunofport_portnum;
	private String tunofport_status;
	
	public Vnet_tunofport()
	{}
	
	public Vnet_tunofport(String tunofport_id,Integer tunofport_portnum)
	{
		this.tunofport_id=tunofport_id;
		this.tunofport_portnum=tunofport_portnum;
	}
	
	public String getTunofport_id() {
		return tunofport_id;
	}
	public void setTunofport_id(String tunofport_id) {
		this.tunofport_id = tunofport_id;
	}
	public Integer getTunofport_portnum() {
		return tunofport_portnum;
	}
	public void setTunofport_portnum(Integer tunofport_portnum) {
		this.tunofport_portnum = tunofport_portnum;
	}

	@Override
	public String toString() {
		return "Vnet_tunofport [tunofport_id=" + tunofport_id
				+ ", tunofport_portnum=" + tunofport_portnum
				+ ", tunport_status=" + tunofport_status + "]";
	}
	public void setTunofport_status(String tunofport_status) {
		this.tunofport_status = tunofport_status;
	}
	public String getTunofport_status() {
		return tunofport_status;
	}

	
	
}

/**
 * 
 */
package cn.dlut.entity;


public class Vnet_compute_tunofport {

	private Integer compute_tunofport_id;
	private Integer compute_id;
	private String tunofport_id;
	public Integer getCompute_tunofport_id() {
		return compute_tunofport_id;
	}
	public void setCompute_tunofport_id(Integer compute_tunofport_id) {
		this.compute_tunofport_id = compute_tunofport_id;
	}

	public Integer getCompute_id() {
		return compute_id;
	}
	public void setCompute_id(Integer compute_id) {
		this.compute_id = compute_id;
	}
	public String getTunofport_id() {
		return tunofport_id;
	}
	public void setTunofport_id(String tunofport_id) {
		this.tunofport_id = tunofport_id;
	}
	@Override
	public String toString() {
		return "Vnet_compute_tunofport [compute_tunofport_id="
				+ compute_tunofport_id + ", compute_id=" + compute_id
				+ ", tunofport_id=" + tunofport_id + "]";
	}

}
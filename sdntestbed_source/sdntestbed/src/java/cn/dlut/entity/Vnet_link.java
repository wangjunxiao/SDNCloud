/**
 * 
 */
package cn.dlut.entity;


public class Vnet_link {


	private String link_id;
	private Integer link_bandwidth;
	private String link_src_id;
	private String  link_src_type;
	private String link_dst_id;
	private String  link_dst_type;
	private String link_ofport_src;
	private String link_ofport_dst;
	private Integer link_iscross;
	private Integer link_tunofport_src;
	private Integer link_tunofport_dst;
	private Integer tun_id;
	private String  link_status;
	
	public Vnet_link()
	{
		
	}
	public Vnet_link(String link_id,Integer tun_id)
	{
		this.link_id=link_id;
		this.tun_id=tun_id;
	}
	
	
	
	public String getLink_status() {
		return link_status;
	}
	public void setLink_status(String link_status) {
		this.link_status = link_status;
	}
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	public Integer getLink_bandwidth() {
		return link_bandwidth;
	}
	public void setLink_bandwidth(Integer link_bandwidth) {
		this.link_bandwidth = link_bandwidth;
	}
	public String getLink_src_id() {
		return link_src_id;
	}
	public void setLink_src_id(String link_src_id) {
		this.link_src_id = link_src_id;
	}
	public String getLink_src_type() {
		return link_src_type;
	}
	public void setLink_src_type(String link_src_type) {
		this.link_src_type = link_src_type;
	}
	public String getLink_dst_id() {
		return link_dst_id;
	}
	public void setLink_dst_id(String link_dst_id) {
		this.link_dst_id = link_dst_id;
	}
	public String getLink_dst_type() {
		return link_dst_type;
	}
	public void setLink_dst_type(String link_dst_type) {
		this.link_dst_type = link_dst_type;
	}
	public String getLink_ofport_src() {
		return link_ofport_src;
	}
	public void setLink_ofport_src(String link_ofport_src) {
		this.link_ofport_src = link_ofport_src;
	}
	public String getLink_ofport_dst() {
		return link_ofport_dst;
	}
	public void setLink_ofport_dst(String link_ofport_dst) {
		this.link_ofport_dst = link_ofport_dst;
	}
//	public Integer getIscross() {
//		return iscross;
//	}
//	public void setIscross(Integer iscross) {
//		this.iscross = iscross;
//	}

	public Integer getLink_iscross() {
		return link_iscross;
	}
	public Integer getLink_tunofport_src() {
		return link_tunofport_src;
	}
	public void setLink_tunofport_src(Integer link_tunofport_src) {
		this.link_tunofport_src = link_tunofport_src;
	}
	public Integer getLink_tunofport_dst() {
		return link_tunofport_dst;
	}
	public void setLink_tunofport_dst(Integer link_tunofport_dst) {
		this.link_tunofport_dst = link_tunofport_dst;
	}
	public void setLink_iscross(Integer link_iscross) {
		this.link_iscross = link_iscross;
	}

	public Integer getTun_id() {
		return tun_id;
	}
	public void setTun_id(Integer tun_id) {
		this.tun_id = tun_id;
	}
	@Override
	public String toString() {
		return "Vnet_link [link_id=" + link_id + ", link_bandwidth="
				+ link_bandwidth + ", link_src_id=" + link_src_id
				+ ", link_src_type=" + link_src_type + ", link_dst_id="
				+ link_dst_id + ", link_dst_type=" + link_dst_type
				+ ", link_ofport_src=" + link_ofport_src + ", link_ofport_dst="
				+ link_ofport_dst + ", link_iscross=" + link_iscross
				+ ", link_tunofport_src=" + link_tunofport_src
				+ ", link_tunofport_dst=" + link_tunofport_dst + ", tun_id="
				+ tun_id + ", link_status=" + link_status + "]";
	}


	
}

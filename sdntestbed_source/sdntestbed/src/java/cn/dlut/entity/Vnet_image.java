/**
 * 
 */
package cn.dlut.entity;


public class Vnet_image {

	private Integer image_id;
	private String image_osid;
	private String image_name;
	private String image_format;
	private String image_size;
	
	public Vnet_image() {
		
	}

	public Integer getImage_id() {
		return image_id;
	}


	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}


	public String getImage_osid() {
		return image_osid;
	}


	public void setImage_osid(String image_osid) {
		this.image_osid = image_osid;
	}


	public String getImage_name() {
		return image_name;
	}


	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}


	public String getImage_format() {
		return image_format;
	}


	public void setImage_format(String image_format) {
		this.image_format = image_format;
	}


	public String getImage_size() {
		return image_size;
	}


	public void setImage_size(String image_size) {
		this.image_size = image_size;
	}


	@Override
	public String toString() {
		return "image [image_id=" + image_id + ", image_osid=" + image_osid + ", image_name="
				+ image_name + ", image_format=" + image_format + ", image_size=" + image_size + "]";
	}

}

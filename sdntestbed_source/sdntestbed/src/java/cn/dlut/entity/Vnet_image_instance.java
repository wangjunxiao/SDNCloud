/**
 * 
 */
package cn.dlut.entity;


public class Vnet_image_instance {

	private Integer image_instance_id;
	private Integer image_id;
	private String instance_id;


	public Vnet_image_instance() {
		
	}
	
	public Integer getImage_instance_id() {
		return image_instance_id;
	}

	public void setImage_instance_id(Integer image_instance_id) {
		this.image_instance_id = image_instance_id;
	}

	public Integer getImage_id() {
		return image_id;
	}

	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	@Override
	public String toString() {
		return "image_instance [image_instance_id=" + image_instance_id + ", image_id=" + image_id + ", instance_id="
				+ instance_id + "]";
	}


}
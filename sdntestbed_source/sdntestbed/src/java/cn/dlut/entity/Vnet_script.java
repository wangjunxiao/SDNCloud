/**
 * 
 */
package cn.dlut.entity;


public class Vnet_script {

	private String userscript_id;
	private String userscript_name;
	private String userscript_type;
	private String userscript_content;
	
	public Vnet_script(){
		
	}
	public String getUserscript_id() {
		return userscript_id;
	}
	public void setUserscript_id(String userscript_id) {
		this.userscript_id = userscript_id;
	}
	public String getUserscript_name() {
		return userscript_name;
	}
	public void setUserscript_name(String userscript_name) {
		this.userscript_name = userscript_name;
	}
	public String getUserscript_type() {
		return userscript_type;
	}
	public void setUserscript_type(String userscript_type) {
		this.userscript_type = userscript_type;
	}
	public String getUserscript_content() {
		return userscript_content;
	}
	public void setUserscript_content(String userscript_content) {
		this.userscript_content = userscript_content;
	}
	@Override
	public String toString() {
		return "Vnet_userscript [userscript_id=" + userscript_id
				+ ", userscript_name=" + userscript_name + ", userscript_type="
				+ userscript_type + ", userscript_content="
				+ userscript_content + "]";
	}
	
	
}

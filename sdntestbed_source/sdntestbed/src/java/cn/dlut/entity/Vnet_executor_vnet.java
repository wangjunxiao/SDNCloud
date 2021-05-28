/**
 * 
 */
package cn.dlut.entity;


public class Vnet_executor_vnet {

	private Integer executor_vnet_id;
	private Integer executor_id;
	private String vnet_id;

	public Vnet_executor_vnet() {
		
	}
	
	
	
	public Integer getExecutor_vnet_id() {
		return executor_vnet_id;
	}



	public void setExecutor_vnet_id(Integer executor_vnet_id) {
		this.executor_vnet_id = executor_vnet_id;
	}



	public Integer getExecutor_id() {
		return executor_id;
	}



	public void setExecutor_id(Integer executor_id) {
		this.executor_id = executor_id;
	}



	public String getVnet_id() {
		return vnet_id;
	}



	public void setVnet_id(String vnet_id) {
		this.vnet_id = vnet_id;
	}



	@Override
	public String toString() {
		return "executor_vnet [executor_vnet_id=" + executor_vnet_id 
				+ ", executor_id=" + executor_id + ", vnet_id="+ vnet_id + "]";
	}

}
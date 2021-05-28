/**
 * 
 */
package cn.dlut.entity;


public class Vnet_executor {


	private Integer executor_id;
	private String executor_name;
	private String executor_password;
	private Integer executor_quota_host;
	private Integer executor_quota_controller;
	private Integer executor_quota_switch;
	private Integer executor_quota_vnet;
	private String executor_role;
	
	public Vnet_executor()
	{
		
	}
	
	
	
	public Integer getExecutor_id() {
		return executor_id;
	}



	public void setExecutor_id(Integer executor_id) {
		this.executor_id = executor_id;
	}



	public String getExecutor_name() {
		return executor_name;
	}



	public void setExecutor_name(String executor_name) {
		this.executor_name = executor_name;
	}



	public String getExecutor_password() {
		return executor_password;
	}



	public void setExecutor_password(String executor_password) {
		this.executor_password = executor_password;
	}



	public Integer getExecutor_quota_host() {
		return executor_quota_host;
	}



	public void setExecutor_quota_host(Integer executor_quota_host) {
		this.executor_quota_host = executor_quota_host;
	}



	public Integer getExecutor_quota_controller() {
		return executor_quota_controller;
	}



	public void setExecutor_quota_controller(Integer executor_quota_controller) {
		this.executor_quota_controller = executor_quota_controller;
	}



	public Integer getExecutor_quota_switch() {
		return executor_quota_switch;
	}



	public void setExecutor_quota_switch(Integer executor_quota_switch) {
		this.executor_quota_switch = executor_quota_switch;
	}



	public Integer getExecutor_quota_vnet() {
		return executor_quota_vnet;
	}



	public void setExecutor_quota_vnet(Integer executor_quota_vnet) {
		this.executor_quota_vnet = executor_quota_vnet;
	}



	public String getExecutor_role() {
		return executor_role;
	}



	public void setExecutor_role(String executor_role) {
		this.executor_role = executor_role;
	}



	@Override
	public String toString() {
		return "Executor " + executor_id.toString() + " " + executor_name.toString() + " " + executor_password.toString() + " "
		+ executor_quota_host.toString() + " " + executor_quota_controller.toString() + " " + executor_quota_switch.toString() + " " + 
		executor_quota_vnet.toString() + " " + executor_role.toString() ;
	}


}

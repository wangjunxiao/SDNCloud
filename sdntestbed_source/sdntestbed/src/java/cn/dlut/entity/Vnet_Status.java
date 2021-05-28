package cn.dlut.entity;

public class Vnet_Status {
	
	private boolean hostStatus = false;
	private boolean linkStatus = false;
	private boolean switchStatus = false;
	
	
	public boolean isHostStatus() {
		return hostStatus;
	}
	public void setHostStatus(boolean hostStatus) {
		this.hostStatus = hostStatus;
	}
	public boolean isLinkStatus() {
		return linkStatus;
	}
	public void setLinkStatus(boolean linkStatus) {
		this.linkStatus = linkStatus;
	}
	public boolean isSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(boolean switchStatus) {
		this.switchStatus = switchStatus;
	}
	
	
}

/**
 * 
 */
package cn.dlut.entity;

import java.util.Date;

public class Vnet_WSRequest {

	private String json_id;
	private String type;  //ping ifconfig rc.local
	private Date start; //job start time
	private Date stop; //job stop time
	private String args1;
	private String args2;
	private String size;
	

	public Vnet_WSRequest(String id,String type,Date start,Date stop,String args1,String args2,String size) {
		this.json_id = id;
		this.type = type;
		this.start = start;
		this.stop = stop;
		this.args1 = args1;
		this.args2 = args2;
		this.size = size;
	}
	


	public String getArgs1() {
		return args1;
	}



	public void setArgs1(String args1) {
		this.args1 = args1;
	}



	public String getArgs2() {
		return args2;
	}



	public void setArgs2(String args2) {
		this.args2 = args2;
	}



	public String getJson_id() {
		return json_id;
	}



	public void setJson_id(String json_id) {
		this.json_id = json_id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Date getStart() {
		return start;
	}



	public void setStart(Date start) {
		this.start = start;
	}



	public Date getStop() {
		return stop;
	}



	public void setStop(Date stop) {
		this.stop = stop;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	@Override
	public String toString() {
		return "Vnet_request [json_id=" + json_id + ", type="
				+ type + ", start=" + start.toString() + ", stop="
				+ stop.toString() + ", args1=" + args1 + ", args2="
				+ args2 + ", size=" + size + "]";
	}



}

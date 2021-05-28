/**
 * 
 */
package cn.dlut.entity;


public class Vnet_compute {

	private Integer compute_id;
	private String compute_name;
	private String compute_addr;
	private String compute_ram;
	private String compute_cpu;
	
	public Vnet_compute() {
		
	}
	public Vnet_compute(Integer id, String compute_name,String compute_addr,String compute_ram,String compute_cpu)
	{
		this.compute_id = id;
		this.compute_name=compute_name;
		this.compute_addr=compute_addr;
		this.compute_ram=compute_ram;
		this.compute_cpu=compute_cpu;
	}



	public Integer getCompute_id() {
		return compute_id;
	}



	public void setCompute_id(Integer compute_id) {
		this.compute_id = compute_id;
	}



	public String getCompute_name() {
		return compute_name;
	}



	public void setCompute_name(String compute_name) {
		this.compute_name = compute_name;
	}



	public String getCompute_addr() {
		return compute_addr;
	}



	public void setCompute_addr(String compute_addr) {
		this.compute_addr = compute_addr;
	}



	public String getCompute_ram() {
		return compute_ram;
	}



	public void setCompute_ram(String compute_ram) {
		this.compute_ram = compute_ram;
	}



	public String getCompute_cpu() {
		return compute_cpu;
	}



	public void setCompute_cpu(String compute_cpu) {
		this.compute_cpu = compute_cpu;
	}



	@Override
	public String toString() {
		return "Compute [compute_id=" + compute_id + ", compute_addr=" + compute_addr 
		       + ", compute_name=" + compute_name + ", compute_ram=" + compute_ram
		       + ", compute_cpu=" + compute_cpu + "]";
	}

}

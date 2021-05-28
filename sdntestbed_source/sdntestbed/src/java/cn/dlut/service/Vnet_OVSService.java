package cn.dlut.service;

import java.util.ArrayList;


public interface Vnet_OVSService {

	String showovs(String compute_url);
	String del_br(String compute_url, String br);
	String add_br(String compute_url,String br) ;
	String set_vmlink(String compute_url, String br0, String port);
	String del_vmlink(String compute_url, String br0, String port);
	void del_switchlink(String compute_url1, String compute_url2, String br0,
			String br1, String port0, String port1);
	String del_controller(String  compute_url,String br,String ip) ;
	String set_controller(String compute_url,String br,String ip,String port);
	
	String get_portnumber(String compute_url,String port_name);
	
	//setup links across br-tun bridge
	//setup links between two switches
	//Main parameters: source switch's switchname, source switch's compute node url
	// 				   dst switch's switchname, dst switch's compute node url
	//String set_tunswitchlink();
	
	//setup links between switch and host
	
	//flow entries installation
	String set_switchlink(String compute_url, String br0, String br1,
			Integer flag);
	String set_flow(String compute_url, String tun_tag, String inport_id,String outport_id);
	String del_flow(String compute_url, String tun_tag, String inport_id,String outport_id);
}
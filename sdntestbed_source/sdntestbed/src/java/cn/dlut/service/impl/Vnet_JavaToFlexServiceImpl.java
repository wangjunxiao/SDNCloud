package cn.dlut.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.Vnet_controllerDao;

import cn.dlut.entity.Vnet_controller;

import cn.dlut.entity.Vnet_executor;
import cn.dlut.entity.Vnet_flavor;
import cn.dlut.entity.Vnet_flavor_instance;
import cn.dlut.entity.Vnet_image;
import cn.dlut.entity.Vnet_image_instance;
import cn.dlut.entity.Vnet_instance;
import cn.dlut.entity.Vnet_instance_controller;
import cn.dlut.entity.Vnet_instance_host;
import cn.dlut.entity.Vnet_link;
import cn.dlut.entity.Vnet_host;
import cn.dlut.entity.Vnet_oflink;
import cn.dlut.entity.Vnet_ofport;
import cn.dlut.entity.Vnet_switch_ofport;
import cn.dlut.entity.Vnet_userip;
import cn.dlut.entity.Vnet_script;
import cn.dlut.entity.Vnet_subnet;
import cn.dlut.entity.Vnet_usersubnet_userip;
import cn.dlut.entity.Vnet_vnet;
import cn.dlut.service.Vnet_JavaToFlexService;

import cn.dlut.entity.Vnet_switch;
import cn.dlut.dao.Vnet_executor_vnetDao;
import cn.dlut.dao.Vnet_flavorDao;
import cn.dlut.dao.Vnet_flavor_instanceDao;
import cn.dlut.dao.Vnet_floatingipDao;
import cn.dlut.dao.Vnet_hostDao;
import cn.dlut.dao.Vnet_imageDao;
import cn.dlut.dao.Vnet_image_instanceDao;
import cn.dlut.dao.Vnet_instanceDao;
import cn.dlut.dao.Vnet_instance_controllerDao;
import cn.dlut.dao.Vnet_instance_hostDao;
import cn.dlut.dao.Vnet_ipDao;
import cn.dlut.dao.Vnet_linkDao;
import cn.dlut.dao.Vnet_oflinkDao;
import cn.dlut.dao.Vnet_ofportDao;
import cn.dlut.dao.Vnet_switchDao;
import cn.dlut.dao.Vnet_switch_ofportDao;
import cn.dlut.dao.Vnet_useripDao;
import cn.dlut.dao.Vnet_scriptDao;
import cn.dlut.dao.Vnet_subnetDao;
import cn.dlut.dao.Vnet_usersubnet_useripDao;



@Service("Vnet_JavaToFlexService")
public class Vnet_JavaToFlexServiceImpl extends AbstractBaseService  implements Vnet_JavaToFlexService{
	
	@Autowired
	private Vnet_instanceDao instancedao;

	@Autowired
	private Vnet_controllerDao controllerdao;
	
	@Autowired
	private Vnet_switchDao switchdao;

	@Autowired
	private Vnet_hostDao hostdao;
	
	@Autowired
	private Vnet_oflinkDao oflinkdao;
	
	@Autowired
	private Vnet_linkDao linkdao;
	
	@Autowired
	private Vnet_executor_vnetDao e_vdao;
	
	@Autowired
	private Vnet_ofportDao ofportdao;
	
	@Autowired
	private Vnet_switch_ofportDao switch_ofportdao;
	
	@Autowired
	private Vnet_imageDao imagedao;
	
	@Autowired
	private Vnet_flavorDao flavordao;
	
	@Autowired
	private Vnet_image_instanceDao image_instancedao;
	
	@Autowired
	private Vnet_flavor_instanceDao flavor_instancedao;
	
	@Autowired
	private Vnet_instance_hostDao instance_hostDao;
	
	@Autowired
	private Vnet_instance_controllerDao instance_condao;
	
	@Autowired
	private Vnet_subnetDao user_subnetdao;
	
	@Autowired
	private Vnet_useripDao user_ipDao;
	
	@Autowired
	private Vnet_usersubnet_useripDao subnet_ipdao;
	
	@Autowired
	private Vnet_scriptDao scriptDao;
	
	@Autowired
	private Vnet_ipDao ipdao;
	
	@Autowired
	private Vnet_floatingipDao floatipdao;
	
	@Override
	public List<Vnet_instance> instanceJavaToFlex(Vnet_executor exe) {
		List<Vnet_instance> instancelist = instancedao.getAllbyStatus(exe.getExecutor_id());
		return instancelist;
	}
	
	//no one call
	@Override
	public List<Vnet_controller> controllerJavaToFlex(Vnet_executor exe) {
		List<Vnet_controller> controllerlist = controllerdao.getAllbyStatus(exe.getExecutor_id());
		return controllerlist;
	}
	
	@Override
	public List<Vnet_switch> switchJavaToFlex(Vnet_executor exe) {
		 List<Vnet_switch> switchlist = switchdao.getAllbyStatus(exe.getExecutor_id());
		 return switchlist;
	}
	
	//no one call
	@Override
	public List<Vnet_host> hostJavaToFlex(Vnet_executor exe) {
		List<Vnet_host> hostlist = hostdao.getAllbyStatus(exe.getExecutor_id());
		System.out.println("##########"+hostlist);
		return hostlist;
	}
	
	@Override
	public List<Vnet_oflink> oflinkJavaToFlex(Vnet_executor exe) {
		List<Vnet_oflink> oflinklist =	oflinkdao.getAllbyStatus(exe.getExecutor_id());
		return oflinklist;
	}
	
	@Override
	public List<Vnet_link> linkJavaToFlex(Vnet_executor exe) {
		List<Vnet_link> linklist = linkdao.getAllbyStatus(exe.getExecutor_id());
		return linklist;
	}
	
	@Override
	public Vnet_vnet e_vJavaToFlex(Vnet_executor exe) {
		Vnet_vnet myvnet = e_vdao.getVnetByexe_id(exe.getExecutor_id());
		if(myvnet == null){
			return null;
		}
		return myvnet;
	}
	
	@Override
	public List<Vnet_ofport> ofportJavaToFlex(Vnet_executor exe) {
		List<Vnet_ofport> ofportlist = new ArrayList<Vnet_ofport>();
		Vnet_vnet myvnet = e_vdao.getVnetByexe_id(exe.getExecutor_id());
		if(myvnet == null){
			return ofportlist;
		}
		ofportlist = ofportdao.getAllByVnet(myvnet.getVnet_id());
		return ofportlist;
	}
	
	@Override
	public List<Vnet_switch_ofport> switch_ofportJavaToFlex(Vnet_executor exe) {
		List<Vnet_switch_ofport> switch_ofportlist = new ArrayList<Vnet_switch_ofport>();
		Vnet_vnet myvnet = e_vdao.getVnetByexe_id(exe.getExecutor_id());
		if(myvnet == null){
			return switch_ofportlist;
		}
		switch_ofportlist = switch_ofportdao.getAllByVnet(myvnet.getVnet_id());
		return switch_ofportlist;
	}
	
	@Override
	public List<Vnet_image> imageJavaToFlex() {
		List<Vnet_image> imagelist = imagedao.getAll();
		return imagelist;
	}
	
	@Override
	public List<Vnet_flavor> flavorJavaToFlex() {
		List<Vnet_flavor> flavorlist = flavordao.getAll();
		return flavorlist;
	}
	
	@Override
	public List<Vnet_instance_host> instance_hostJavaToFlex(Vnet_executor exe) {
		List<Vnet_instance_host> instance_hostlist = instance_hostDao.getAllActive(exe.getExecutor_id());
		return instance_hostlist;
	}
	@Override
	public List<Vnet_instance_controller> instance_controller(Vnet_executor exe) {
		List<Vnet_instance_controller> instance_controllerlist = instance_condao.getAllActive(exe.getExecutor_id());
		return instance_controllerlist;
	}
	
	@Override
	public List<Vnet_image_instance> image_instanceJavaToFlex(Vnet_executor exe) {
		List<Vnet_image_instance> image_inslist = image_instancedao.getAllActive(exe.getExecutor_id());
		return image_inslist;
	}
	
	@Override
	public List<Vnet_flavor_instance> flavor_instanceJavaToFlex(Vnet_executor exe) {
		List<Vnet_flavor_instance> flavor_inslist = flavor_instancedao.getAllActive(exe.getExecutor_id());
		return flavor_inslist;
	}
	
	@Override
	public List<Vnet_subnet> usersubnet_JavaToFlex() {
		List<Vnet_subnet> subnet_list = user_subnetdao.getAll();
//		System.out.println(subnet_list.size() + " #############");
//		List<Vnet_usersubnet> result = new ArrayList<Vnet_usersubnet>();
		for (int i = 0; i < subnet_list.size(); i++) {
			System.out.println(subnet_list.get(i).toString());
		}
//		System.out.println(result.size() + " %%%%%%%%%%");
//		Vnet_usersubnet a = new Vnet_usersubnet();
//		result.add(a);
		return subnet_list;
//		return result;
	}
	
	@Override
	public List<Vnet_userip> userip_JavaToFlex() {
		List<Vnet_userip> userip_list = user_ipDao.getAll();
		return userip_list;
	}
	@Override
	public List<Vnet_usersubnet_userip> subnet_ipJavaToFlex() {
		List<Vnet_usersubnet_userip> subnet_iplist = subnet_ipdao.getAll();
		return subnet_iplist;
	}
	@Override
	public List<Vnet_script> script_JavaToFlex() {
		List<Vnet_script> script_list = scriptDao.getAll();
		return script_list;
	}
//	@Override
//	public List<Vnet_flavor> usersubnet_JavaToFlex() {
//		List<Vnet_flavor> subnet_list = flavordao.getAll();
//		return subnet_list;
//	}

	@Override
	public List<Map<String, String>> instanceIP_JavaToFlex(int exe_id) {
		List<Map<String, String>> res = new ArrayList<Map<String,String>>();
		List<Vnet_instance> instance_list = instancedao.getAllbyStatus(exe_id);
		for (int i = 0; i < instance_list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("instance_id", instance_list.get(i).getInstance_id());
			if(instance_list.get(i).getInstance_type().equals("Controller")){
				map.put("ip_addr", floatipdao.getbyInsid(instance_list.get(i).getInstance_id()).getFloatingip_addr());
			}
			else{
				map.put("ip_addr", ipdao.getInstanceIP(instance_list.get(i).getInstance_id()));
			}
			res.add(map);
		}
		return res;
	}

	@Override
	public Boolean queryVnet(Vnet_executor exe) throws InterruptedException {
		Vnet_vnet myvnet;
		Boolean res = false;
		while (true) {
			myvnet= e_vdao.getVnetByexe_id(exe.getExecutor_id());
			if(myvnet == null){
				res = true;
				break;
			}
			else {
				if(myvnet.getVnet_status().equals("wait")){
					Thread.sleep(1000);
				}
				else{
					res = true;
					break;
				}
			}
		}
		return res;
	}
	
	
}
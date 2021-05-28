package cn.dlut.service;

import java.util.ArrayList;
import java.util.Map;

public interface Vnet_OpsNovaService
{
	public ArrayList<Map<String, String>>  getSeriallist();
	public ArrayList<Map<String, String>> getvnclist();
	public String getVncUrl(String sql_id);
	public String getSerialUrl(String sql_id);
	public String getSerialUrlByosid(String osid);

}
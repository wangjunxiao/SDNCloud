package cn.dlut.service;

public interface Vnet_OpsIdentityService {
	
	public void CreateToken();
//	POST, /v2.0/tokens
	public void ValidateToken(String HEADER_VALUE);
}
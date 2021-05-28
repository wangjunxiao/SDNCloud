package cn.dlut.service;

import java.util.ArrayList;




public interface Vnet_InitFromOpsService {

	
    public String InitFlavor();
    public void InitImage();
    public void InitInstance();
    public void InitCompute();
    public void InitAll();
    public void InitFloatingIp();

}
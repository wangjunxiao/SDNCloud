package cn.dlut.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dlut.service.Vnet_InitFromOpsService;
import cn.dlut.service.Vnet_ipSubtractService;
import cn.dlut.service.impl.Vnet_InitFromOpsServiceImpl;


@Component
public class Vnet_InitJob implements Job {
    public static int timer=0; //define a global variable
    @Autowired
	private Vnet_InitFromOpsService cs; //automatic calibration scheme for unifying the mismatch entries between sdntestbed database and ops database
    @Autowired
	private Vnet_ipSubtractService cs1;
    @Override  
    public void execute(JobExecutionContext arg0) throws JobExecutionException {  
        // TODO Auto-generated method stub  
    }  
      
    public void executeInit() throws JobExecutionException {  
    	 if(timer==0){
    	  timer++;
//    	 cs.InitFlavor();

//    	 cs.InitImage();
    	  
//    	 cs.InitAll();

//     	 cs.InitFloatingIp();

//    	 cs1.InitIp();
  
    	
    	 }
    }  
}
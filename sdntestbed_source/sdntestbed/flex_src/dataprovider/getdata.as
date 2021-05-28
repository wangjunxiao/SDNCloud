import vo.Vnet_executor;

private var app_iscomplete:Boolean = false;
private var topo_iscomplete:Boolean = false;
private var queryRes:Boolean = false;

private var counter:int = 0;
private var istopo_groupcomplete:Boolean = false;
private var timer:Timer;


private function init():void{
	
	/*****************     get the parameters sent from Vnet_Login.html                  ***********************/	
	var browser_url:String = ExternalInterface.call('window.location.href.toString');
	//Parse parameters, username and passwd
	var param_arr:Array = browser_url.split("#");
	if(param_arr.length != 1) {
		var params:Object = URLUtil.stringToObject(decryption(param_arr[1].toString()),"&");
		//get user info and save at frontend
		executor = new Vnet_executor();
		executor.executor_name = params.executor_name;
		executor.executor_id = params.executor_id;
		executor_name = executor.executor_name;
		executor_id = executor.executor_id;
	}
	else {
		Alert.show("Login Again Please !");
	}
	
	//get user's Vnet state
	Vnet_JavaToFlexService.queryVnet(executor);
	
	//load gif and start timer
	timer=new Timer(10000,2);
	timer.addEventListener(TimerEvent.TIMER,onTime);
	loading("images/loading.gif");
	timer.start();
	
	createLibrary(); //create menu bar
		
}


private function onTime(event:TimerEvent):void{
	if(timer.currentCount==1){
		if(!topo_iscomplete){
//			Alert.show("you have waited for 8 s");
		}
	}
	if(timer.currentCount==2){
		if(!topo_iscomplete && queryRes){
			Alert.show("Check Network Connectivity Please !");
		}
	}
}

protected function queryVnetResultHandler(event:ResultEvent):void{
	queryRes = event.result as Boolean;
	if(queryRes){
		//Vnet state has been gotten, and get topo info
		Vnet_JavaToFlexService.instanceJavaToFlex(executor);
		Vnet_JavaToFlexService.switchJavaToFlex(executor);
		Vnet_JavaToFlexService.hostJavaToFlex(executor);
		Vnet_JavaToFlexService.controllerJavaToFlex(executor);
		Vnet_JavaToFlexService.oflinkJavaToFlex(executor);
		Vnet_JavaToFlexService.linkJavaToFlex(executor);
		Vnet_JavaToFlexService.e_vJavaToFlex(executor);
		Vnet_JavaToFlexService.ofportJavaToFlex(executor);
		Vnet_JavaToFlexService.switch_ofportJavaToFlex(executor);
		Vnet_JavaToFlexService.imageJavaToFlex();
		Vnet_JavaToFlexService.flavorJavaToFlex();
		Vnet_JavaToFlexService.image_instanceJavaToFlex(executor);
		Vnet_JavaToFlexService.flavor_instanceJavaToFlex(executor);
		Vnet_JavaToFlexService.instance_hostJavaToFlex(executor);
		Vnet_JavaToFlexService.instance_controller(executor);
		Vnet_JavaToFlexService.instanceIP_JavaToFlex(executor_id);
	}
}

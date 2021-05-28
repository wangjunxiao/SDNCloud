#Prerequisites

1. you need to install an eclipse IDE.
2. insert a java web plugin into your eclipse.
3. insert a flex builder plugin into your eclipse.
4. you need to install jdk1.7 32bit version.

#Credits

1. eclipse  2. flex builder 3. jdk

#Source Code Description

flex_src: web frontend project  
A request process of sending data back to backend:  
1. call frontend's actionscript eventHandler  
2. find out backend's java service handler by remoting-config.xml. After getting the service done, return to frontend  
3. asynchronously resolve the return by frontend's actionscript resultHandler  

com.hurlant: encrypt packets for sending password when users log in  
FTreeTest.xml: define a k=4 fattree topology  
flexiframe: window framework used for access to virtual host's console   
org.bytearray.gif: loading gif  
skins: frontend's ui style  
src/conf: project's configuration file  
ibatis: define sql related operation  
spring-job: timed task's configuration file  
config.properties: database connection's configuration file  
test: junit test case  
Vnet_Workbench.mxml: project's main framework  
Vnet_JavaToFlexService: response to frontend's query operation  
Vnet_FlexToJavaService: response to frontend's update operation according to request type
    
    Request Type      Meaning          vnet ID      Content ID
	1               Create Vnet           *              -
	2               Delete Vnet           *              -
	3               Create Vhost          *           Host ID
	4               Delete Vhost          *           Host ID
	5               Create Vswitch        *           Switch ID
	6               Delete Vswitch        *           Switch ID
	7               Create Vcontroller    *           Controller ID
	8               Delete Vcontroller    *           Controller ID
	9               Create Vlink          *           Link ID
	10              Delete Vlink          *           Link ID
	11              Create oflink         *           oflink ID
	12              Delete oflink         *           oflink ID
	
and establish a resource mapping between virtual resource and physical resource according to a dedicated load balance strategy. The resource orchestration order as:  
1. receive all request and write into database
2. conduct creation, updating and destroying operation by request type
3. creation:  
	(a) create all instances, including host and controller  
	(b) check instance finish booting, i.e., have gotten assigned ip addr  
	(c) create all switch  
	(d) create all link, including the link between switch and host as well between two switches  
	(e) create all oflink  
4. Destroying:  
	(a) recycle all link, including the link between switch and host as well between two switches  
	(b) recycle all oflink  
	(c) recycle all switch  
	(d) recycle all instance, including host and controller  
Vnet_InitFromOpsService: conduct init job and write Ops database into project's database  
Vnet_ipSubtractService: define ip pool related operation  
Vnet_LoginService: define loging in and loging out related operation  
Vnet_OpsCinderService: define OpenStack's storage node related operation  
Vnet_OpsComputeService: define OpenStack's compute node related operation  
Vnet_OpsFlavorService: define image related operation  
Vnet_OpsHeatService: define stack related operation, including stack creation, stack querying, stack destroying and stack hanging  
Vnet_OpsIdentityService: define token related operation, including token creation and token validation  
Vnet_OpsNeutronService: define neutron related operation  
Vnet_OpsNovaService: define vnc and serial related operation  
Vnet_OVSService: define ovs related operation, including switch creation, link creation, oflink creation. json formulated as: 
	
    {
		"id": "1",
		"jsonrpc": "2.0",
		"method": "cmd",
		"params": 
		{
			"cmd": "ovs-vsctl show"
		}
	}

Vnet_UserResourceService: user subnet and user script  
Vnet_WebSocketService: check OpenStack instance whether failed to boot and conduct predefined boot script  


#SDN Testbed Guide Book

1. Topology:  
	1.1 Create, Update, Delete  
	1.2 After clicking the button of "submit", the location of all components will be fixed  
2. Controller:  
	2.1 The backend only offers the support for Floodlight so far, the type of Ryu will be considered in the future work  
	2.2 The default ip addr of controller is 10.3.0.0/16, a reachable network connection is required for access to its gui page  
3. Switch:  
	3.1 The backend only offers the support for secure mode ovs so far, the type of standalone mode ovs will be considered in the future work  
4. Host:  
	4.1 A customized ip addr will be considered in the future work  
5. Link:  
	5.1 Orange Line: The link between Controller and Swtich  
	5.2 Blue Line: The link between Switch and Host, as well two Switch. Show OpenFlow port num by clicking on, can be canceled by clicking any other component   
6. User Subnet:  
	6.1 Create, Update, Delete  
7. User Script:  
	7.1 Currently unavailable  
8. Test Button:  
	8.1 Create a simple FatTree topology for the user whose Vnet is empty  
9. Menu Bar:  
	9.1 Currently unavailable except for Management and Help  
10. Icon:  
	10.1 The row of icon below the menu bar is currently unavailable
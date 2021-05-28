# Prerequisites

#### Minimal Runtime Environment  
1. Hardware:  

(a) x86 Server * 3 with OpenStack nodes deployed, in which 2 server with more memory and cpu capacity as compute nodes, 1 server with more nic and disk capacity as the control node, network node and storage node  

(b) Web Server * 1 with sdntestbed project and project's database deployed  

(c) Commodity L2 Switch * 1 for connecting all servers together  

2. Software:  

(a) Database: Mysql Server, create project's database by vnetdb.sql

(b) Web: Jre and Tomcat  

(c) OpenStack: kilo version  

3. Script:  

(a) deleteopenstack_heat.sql: clear zombie stack in OpenStack database  

(b) deleteopenstack_nova.sql: clear zombie instance in OpenStack database  

(c) clear /var/lib/nova/instances/{Instance ID} in compute node  

(d) deleteSDNall.txt: clear project's database  

# Configuration

apache-tomcat-6.0.44-green\webapps\sdn\WEB-INF\classes\config.properties:

    vnetlabdb.driver=com.mysql.jdbc.Driver
    #database connection URL
    vnetlabdb.url=jdbc\:mysql\://10.1.0.3\:3306/sdn?noAccessToProcedureBodies\=true
    #database user and password
    vnetlabdb.user=root
    vnetlabdb.password=root
    #c3p0 connection pool
    c3p0.initialPoolSize=5
    c3p0.minPoolSize=1
    c3p0.maxPoolSize=3
    c3p0.acquireIncrement=1
    c3p0.maxIdleTime=30
    c3p0.maxStatements=0
    c3p0.maxStatementsPerConnection=0
    c3p0.idleConnectionTestPeriod=30

apache-tomcat-6.0.44-green\webapps\sdn\WEB-INF\classes\constans.properties:

    #OpenStack subnet name
    Vnet_subnet_host=host-iface-subnet
    Vnet_subnet_control=control-iface-subnet
    #OpenStack compute node num
    Vnet_compute_num=2
    #OpenStack compute node available zone
    Vnet_compute1_name=zone-compute1
    #compute node ip addr
    Vnet_compute1_addr=10.2.0.1
    #compute node CPU and memory capacity
    Vnet_compute1_ram=10
    Vnet_compute1_cpu=4
    #compute node's GRE tunnel IP
    Vnet_compute1_vnet_br=30.0.0.1
    #more compute node
    Vnet_compute2_name=zone-compute2
    Vnet_compute2_addr=10.2.0.2
    Vnet_compute2_ram=10
    Vnet_compute2_cpu=4
    Vnet_compute2_vnet_br=30.0.0.2
    #bridge v
    Vnet_compute_vnet_br_number=2
    Vnet_compute1_tunofport_num=1
    Vnet_compute1_tunofport_1=zone-compute2
    Vnet_compute2_tunofport_num=1
    Vnet_flow_table_id=40
    #OpenStack admin token
    Vnet_username=demo
    Vnet_userpassword=0000
    Vnet_adminname=admin
    Vnet_adminpassword=0000
    Vnet_tun_from=1
    Vnet_tun_to=2000
    #OpenStack host-iface network ID
    Vnet_host_network_id=df906846-97b8-4428-abe6-67e23cc0246c
    #OpenStack host-iface subnet ID
    Vnet_host_subnet_id=11aae3da-a997-4060-9c7e-2027ed63a842
    #OpenStack control-iface
    Vnet_controller_network_id=450ef80a-b46b-4b9f-a620-1ac6d8a62202
    Vnet_controller_subnet_id=85c7635d-92a3-48ac-9ec9-9239fa10f020
    #floodlight ofport
    Vnet_floodlight_openflow_port=6653
    #tunnel bridge v
    Vnet_br=v
    #config instance URL
    Vnet_config_url=10.3.0.30\:56789
    #OpenStack control node IP
    Vnet_control_addr=10.2.0.3
    #OpenStack tenant ID
    Vnet_tenant_demo=2379e521097a4f7986f8f7dde862d922
    #OpenStack floatingip pool capacity
    Vnet_floatingip_size=150
    #controller internal ip pool capacity 
    Vnet_controller_ip_size=400
    #host internal ip pool capacity
    Vnet_host_ip_size=400
    Vnet_tun_size=400
    Vnet_tomcat_server=0



# Start Up

1. run rpcserver.py in python/novaconsole-master/ at all OpenStack compute node as agent deamon to interact with ovs

    screen python rpcserver.py –host=local ip addr –port=12345

2. create config instance in OpenStack, run sshserver.py in novaconsole-master/novacondole/ at config instance as agent deamon to interact with created host

    screen python sshserver.py –host=local ip addr –port=56789

3. creating bridge v as tunnel between two compute node, check the bridge v whether have been created by conducting command of ovs-vsctl show in compute node

    ovs-vsctl del-br v
    ovs-vsctl add-br v
    ovs-vsctl add-port v v-tun -- set interface v-tun type=gre options:df_default=true options:in_key=flow options:local_ip=src_ip options:out_key=flow options:remote_ip=dst_ip

    ovs-vsctl del-br v
    ovs-vsctl add-br v
    ovs-vsctl add-port v v-tun -- set interface v-tun type=gre options:df_default=true options:in_key=flow options:local_ip=dst_ip options:out_key=flow options:remote_ip=src_ip


# Credits

1. apache tomcat 2. blazeds 3. OpenStack

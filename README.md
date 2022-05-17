### Overview


![overview](overview.PNG =100x)


The sdntestbed is a OpenStack based sdn testbed platform, developed by vnetlab team in dlut. The platform is used for network business simulation, to build a mirror testbed of exsiting in-product business. The experiment conducted in the shadow environment will not only safeguard existing business, but also be migrated into product environment conveniently.

You can deploy the sdntestbed on a OpenStack cluster with nodes connected by a simple L2 commodity switch. On the platform, each user can define a vnet, consists of virtual sdn controllers, sdn switches, links and hosts with isolated operating systems.

### Features

1. create and manage custom virtual sdn network by GUI operation. 
2. multiple isolated virtual sdn network can co-exist in a cluster with server nodes connected by a simple L2 commodity switch.

### Catalog

1. sdntestbed_deployable: providing compiled deployable version.
2. sdntestbed_source: providing version of source code, can be re compiled.

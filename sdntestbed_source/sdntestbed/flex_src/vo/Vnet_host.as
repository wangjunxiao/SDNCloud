package vo
{
	import mx.core.FlexGlobals;
	
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_host")]
	public class Vnet_host
	{
		public var host_id:String;
		public var host_name:String;
		public var host_config:int;
		public var floatingip_id:int;
		
		public static function vnet_delete(id:String):void{
			for(var i:int = 0;i < FlexGlobals.topLevelApplication.H_obj.length;i++){
				if(FlexGlobals.topLevelApplication.H_obj[i].getHost_ID() == id){
					if(FlexGlobals.topLevelApplication.vnet_vnet != null){
						var is_new:Boolean = true;
						for(var j:int=0; j<FlexGlobals.topLevelApplication.vnet_add_hosts.length; j++){
							if(FlexGlobals.topLevelApplication.vnet_add_hosts[j].host_id == id){
								FlexGlobals.topLevelApplication.vnet_add_hosts.removeItemAt(j);
								is_new = false;
								break;
							}
						}
						if(is_new){
							var host:Vnet_host = new Vnet_host();
							host.host_id = id;
							FlexGlobals.topLevelApplication.vnet_del_hosts.addItem(host);
						}
					}
					FlexGlobals.topLevelApplication.H_obj.removeItemAt(i);
					break;
				}
			}
		}
	}
}
package vo
{
	import Components.Controller;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.core.FlexGlobals;

	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_controller")]
	public class Vnet_controller
	{
		public var controller_id:String;
		public var controller_name:String;
		public var controller_type:String;
		public var floatingip_id:int;
		
		public static function vnet_delete(id:String):void {
			
			for(var i:int = 0;i < FlexGlobals.topLevelApplication.C_obj.length;i++){
				if(FlexGlobals.topLevelApplication.C_obj[i].getCon_id() == id){
					if(FlexGlobals.topLevelApplication.vnet_vnet != null){
						var is_new:Boolean = true;
						for(var j:int=0; j<FlexGlobals.topLevelApplication.vnet_add_cons.length; j++){
							if(FlexGlobals.topLevelApplication.vnet_add_cons[j].controller_id == id){
								FlexGlobals.topLevelApplication.vnet_add_cons.removeItemAt(j);
								is_new = false;
								break;
							}
						}
						if(is_new){
							var con:Vnet_controller = new Vnet_controller();
							con.controller_id = id;
							con.controller_type = FlexGlobals.topLevelApplication.C_obj[i].getCon_type();
							FlexGlobals.topLevelApplication.vnet_del_cons.addItem(con);
						}
					}
					FlexGlobals.topLevelApplication.C_obj.removeItemAt(i);
					break;
				}
			}
		}
	}
}
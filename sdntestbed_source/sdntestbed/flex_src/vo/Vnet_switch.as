package vo
{
	import mx.core.FlexGlobals;
	
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_switch")]
	public class Vnet_switch
	{
		public var switch_id:String;
		public var switch_name:String;
		
		public static function vnet_delete(id:String):void{
			for(var i:int = 0;i < FlexGlobals.topLevelApplication.S_obj.length;i++){ 
				if(FlexGlobals.topLevelApplication.S_obj[i].getSwitch_id() == id){
					if(FlexGlobals.topLevelApplication.vnet_vnet != null){
						var is_new:Boolean = true;
						for(var j:int=0; j<FlexGlobals.topLevelApplication.vnet_add_swis.length; j++){
							if(FlexGlobals.topLevelApplication.vnet_add_swis[j].switch_id == id){
								FlexGlobals.topLevelApplication.vnet_add_swis.removeItemAt(j);
								is_new = false;
								break;
							}
						}
						if(is_new){
							var swi:Vnet_switch = new Vnet_switch();
							swi.switch_id = id;
							FlexGlobals.topLevelApplication.vnet_del_swis.addItem(swi);
						}
					}
					FlexGlobals.topLevelApplication.S_obj.removeItemAt(i);
					break;
				}
			}
		}
	}
}
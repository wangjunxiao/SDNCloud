package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_vnet_userscript")]
	public class Vnet_vnet_userscript
	{
		public var vnet_userscript_id:String;
		public var vnet_id:String;
		public var userscript_id:String;
		
		public function Vnet_vnet_userscript(vnet_id:String, script_id:String):void{
			this.vnet_id = vnet_id;
			this.userscript_id = script_id;
		}
	}
}

package vo
{
	import mx.core.FlexGlobals;
	
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_instance_controller")]
	public class Vnet_instance_controller
	{
		public var instance_controller_id:int;
		public var instance_id:String;
		public var controller_id:String;
	}
}
package vo
{
	import mx.core.FlexGlobals;
	
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_flavor_instance")]
	public class Vnet_flavor_instance
	{
		public var flavor_instance_id:int;
		public var flavor_id:int;
		public var instance_id:String;
	}
}
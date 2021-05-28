package vo
{
	import mx.core.FlexGlobals;
	
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_instance_host")]
	public class Vnet_instance_host
	{
		public var instance_host_id:int;
		public var instance_id:String;
		public var host_id:String;
	}
}
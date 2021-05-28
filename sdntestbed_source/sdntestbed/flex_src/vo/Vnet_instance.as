package vo
{
	import mx.core.FlexGlobals;
	
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_instance")]
	public class Vnet_instance
	{
		public var instance_id:String;
		public var instance_osid:String;
		public var instance_name:String;
		public var instance_type:String;
		public var instance_zone:String;
		public var instance_status:String;
	}
}
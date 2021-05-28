package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_vnet_resource")]
	public class Vnet_vnet_resource
	{
		public var vnet_resource:int;
		public var vnet_id:String;
		public var resource_id:String;
		public var resource_type:String;
		public var resource_status:String;
		public var resource_timestamp:String;
	}
}
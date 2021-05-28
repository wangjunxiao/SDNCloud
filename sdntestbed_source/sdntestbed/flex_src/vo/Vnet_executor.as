package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_executor")]
	public class Vnet_executor
	{
		public var executor_id:int;
		public var executor_name:String = "";
		public var executor_password:String = "";
		public var executor_quota_host:int;
		public var executor_quota_controller:int;
		public var executor_quota_switch:int;
		public var executor_quota_vnet:int;
		public var executor_role:String = "";
	}
}
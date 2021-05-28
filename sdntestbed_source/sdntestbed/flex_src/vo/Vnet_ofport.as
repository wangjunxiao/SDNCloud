package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_ofport")]
	public class Vnet_ofport
	{
		public var ofport_id:String;
		public var ofport_portnum:int;
		public var ofport_status:String;
	}
}
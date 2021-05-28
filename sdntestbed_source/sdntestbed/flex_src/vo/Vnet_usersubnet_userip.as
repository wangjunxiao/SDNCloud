package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_usersubnet_userip")]
	public class Vnet_usersubnet_userip
	{
		public var uersubnet_userip_id:int;
		public var usersubnet_id:String="";
		public var userip_id:String="";
		
		public function Vnet_usersubnet_userip(subnet:String, ip:String):void{
			this.usersubnet_id = subnet;
			this.userip_id = ip;
		}
	}
}

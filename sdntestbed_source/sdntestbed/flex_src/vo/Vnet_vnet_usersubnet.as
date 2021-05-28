package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_vnet_usersubnet")]
	public class Vnet_vnet_usersubnet
	{
		public var vnet_usersubnet_id:int;
		public var vnet_id:String;
		public var usersubnet_id:String;
		
		public function Vnet_vnet_usersubnet(vnet_id:String, subnet_id:String):void{
			this.vnet_id = vnet_id;
			this.usersubnet_id = subnet_id;
		}
	}
}

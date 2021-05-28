package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_link")]
	public class Vnet_link
	{
		public var link_id:String;
		public var link_bandwidth:int;
		public var link_src_id:String;
		public var link_src_type:String="";
		public var link_dst_id:String;
		public var link_dst_type:String="";
		public var link_ofport_src:String;
		public var link_ofport_dst:String;
		public var link_iscross:int;
		public var link_tunofport_src:String;
		public var link_tunofport_dst:String;
		public var tun_id:int;
		public var link_status:String="";	
		
		
		public function Delete(link_id: String):void{
			
		}
	}
}
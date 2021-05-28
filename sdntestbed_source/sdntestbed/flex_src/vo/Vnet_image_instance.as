package vo
{
	import mx.core.FlexGlobals;
	
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Vnet_image_instance")]
	public class Vnet_image_instance
	{
		public var image_instance_id:int;
		public var image_id:int;
		public var instance_id:String;
	}
}
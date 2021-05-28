package utils
{
	import mx.controls.Label; 
	import flash.display.Loader;
	import flash.net.URLRequest;
	//Custom background properties
	[Style(name="imgPath", type="String", inherit="no")]  
	
	public class LabelImage extends Label
	{
		private var _loader:Loader = new Loader();
		
		public function LabelImage()
		{
			super();
		}
		
		override protected function updateDisplayList(w:Number, h:Number):void
		{
			super.updateDisplayList(w, h);
			_loader.load(new URLRequest(getStyle('imgPath')));
			addChild(_loader);
			_loader.x = -15;
			setChildIndex(_loader, 0);
		}
	}
}
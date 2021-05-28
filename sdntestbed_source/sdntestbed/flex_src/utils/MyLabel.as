package utils
{
	import mx.controls.Label;
	
	//Customize 
	[Style(name="borderColor", type="uint", format="Color", inherit="no")]  
	[Style(name="borderWidth", type="Number", format="Length", inherit="no")]  
	[Style(name = "borderAlpha", type = "Number", format = "Length", inherit = "no")] 
	
	
	public class MyLabel extends Label
	{	
		public function MyLabel()
		{
			super();
		}
		
		override protected function updateDisplayList(w:Number, h:Number):void
		{
			super.updateDisplayList(w, h);
			graphics.clear();
			graphics.lineStyle(getStyle('borderWidth'), getStyle('borderColor'), getStyle('borderAlpha'), false);
			var x:Number = getStyle('borderWidth') / 2;
			var y:Number = getStyle('borderWidth') / 2;
			var width:Number = this.width - getStyle('borderWidth');
			var height:Number = this.height - getStyle('borderWidth');
			graphics.drawRect(x, y, width, height);
		}
	}
}
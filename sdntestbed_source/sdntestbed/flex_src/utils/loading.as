// ActionScript file
import mx.controls.Image;
import mx.managers.PopUpManager;

import org.bytearray.gif.events.*;
import org.bytearray.gif.player.GIFPlayer; 

private var mygifplayer:GIFPlayer;//new a GIF player		
public var img:Image;	//new a image container as the container of GIF player	

public function loading(url:String):void{
	img = new Image();
	mygifplayer = new GIFPlayer();
	var request:URLRequest = new URLRequest(url);//md.gif
	mygifplayer.load(request);  //GIF download
	img.addChild(mygifplayer);//img as container, add the GIF player
	mygifplayer.addEventListener(GIFPlayerEvent.COMPLETE, onCompleteGIF);//callback after loading GIF		  	
	
	//the pop-up window, the second line below show the middle pop-up, is only for parent container.
	PopUpManager.addPopUp(img,this,true);
	PopUpManager.centerPopUp(img); 				
}

public function onCompleteGIF(event:GIFPlayerEvent):void{
	mygifplayer.play(); //play GIF
}

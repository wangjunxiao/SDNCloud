import flash.display.MovieClip;
import flash.events.Event;
import flash.net.URLLoader;
import flash.net.URLRequest;

import mx.collections.ArrayCollection;


private function xmlLoaded(event:Event):void
{
	var myLoader:URLLoader = event.target as URLLoader;
	var myXML:XML = new XML(myLoader.data);  
	initdata(myXML);
}

private function initdata(myXML:XML):void{
	
	var nodeData:ArrayCollection = new ArrayCollection();
	var linkData:ArrayCollection = new ArrayCollection();
	var Node_obj:ArrayCollection = new ArrayCollection();
	
	// iterate node
	var nodeNum:int = myXML.child("node").length();    
	for( var i:int = 0; i < nodeNum; i++ )                     
	{ 
		var node_item:Object = new Object(); 
		
		// iterate properties 
		var node_attNum:int = myXML.node[i].attributes().length();                    
		for (var j:int=0; j<node_attNum; j++) 
		{ 
			var node_attName:String = myXML.node[i].attributes()[j].name(); 
			var node_attValue:String = myXML.node[i].attribute(node_attName); 
			
			// set Object properties 
			node_item[node_attName] =node_attValue; 
		}   
		
		// add into ArrayCollection 
		nodeData.addItem(node_item); 
	}
	
	//iterate link
	var linkNum:int = myXML.child("link").length();
	for( var i1:int = 0; i1 < linkNum; i1++ )                     
	{ 
		var link_item:Object = new Object(); 
		
		// iterate properties  
		var link_attNum:int = myXML.link[i1].attributes().length();                    
		for (var j1:int=0; j1<link_attNum; j1++) 
		{ 
			var link_attName:String = myXML.link[i1].attributes()[j1].name(); 
			var link_attValue:String = myXML.link[i1].attribute(link_attName); 
			
			// set Object properties 
			link_item[link_attName] = link_attValue; 
		}   
		
		// add into ArrayCollection
		linkData.addItem(link_item); 
	} 
	
	draw_Fattree(nodeData, linkData, Node_obj);
}
	

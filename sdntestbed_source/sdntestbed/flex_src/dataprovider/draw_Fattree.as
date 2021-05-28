import Components.Controller;
import Components.Switch;

import mx.collections.ArrayCollection;
import mx.controls.Alert;

private function draw_Fattree(nodeData:ArrayCollection, linkData:ArrayCollection, Node_obj:ArrayCollection):void {
	vnet_counter = 19;
	
	for(var num_node:int=0;num_node<nodeData.length;num_node++){
		if(nodeData.getItemAt(num_node)["type"]=="controller") {
			fatTreeAddController(int (nodeData.getItemAt(num_node)["id"]),nodeData.getItemAt(num_node)["name"], Node_obj);
		}
		if(nodeData.getItemAt(num_node)["type"]=="switch"){
			fatTreeAddSwitch(int (nodeData.getItemAt(num_node)["id"]),nodeData.getItemAt(num_node)["name"], Node_obj);
		}
		if(nodeData.getItemAt(num_node)["type"]=="host"){
			fatTreeAddHost(int (nodeData.getItemAt(num_node)["id"]),nodeData.getItemAt(num_node)["name"], Node_obj);
		}
	}
	for(var num_link:int=0;num_link<linkData.length;num_link++){
		var from_id:int = int (linkData.getItemAt(num_link)["fromNode"]);
		var to_id:int = int (linkData.getItemAt(num_link)["toNode"])				
		fatTreeAddLink(Node_obj[from_id-1],Node_obj[to_id-1]);
	}
}

private function fatTreeAddController(id:int,name:String, Node_obj:ArrayCollection):void{
	var date:Date = new Date();
	var fatTree_c_item:Controller = new Controller();				
	config(fatTree_c_item, 470, 30, "c"+String(id-1));	
	fatTree_c_item.setTime(date.toString());
	fatTree_c_item.setCon_id("c~" + String(id-1) + "~" + date.toString() +"~"+ executor_name+"~"+470+"~"+30);
	fatTree_c_item.setCon_name("c~" + String(id-1));
	C_obj.addItem(fatTree_c_item);
	Node_obj.addItem(fatTree_c_item);
	fatTree_c_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
	fatTree_c_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
	fatTree_c_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
	network_group.addElement(fatTree_c_item);
}

private function fatTreeAddSwitch(id:int,name:String, Node_obj:ArrayCollection):void{
	var date:Date = new Date();
	var fatTree_s_item:Switch = new Switch();
	var relativeX:Number;
	var relativeY:Number;
	if(id > 16){
		relativeX = 335+((id-17)*237);
		relativeY = 145;
	}
	else if(id < 13){
		relativeX = 275+((id-9)*120);
		relativeY = 335;
	}
	else{
		relativeX = 275+((id-13)*120);
		relativeY = 235;
	}
	config(fatTree_s_item, relativeX, relativeY, "s"+String(id-1));
	fatTree_s_item.setTime(date.toString());
	fatTree_s_item.setSwitch_id("s~" + String(id-1) + "~" + date.toString() +"~"+ executor_name+"~"+relativeX+"~"+relativeY);					
	fatTree_s_item.setSwitch_name("s~" + String(id-1));
	S_obj.addItem(fatTree_s_item);
	Node_obj.addItem(fatTree_s_item);
	fatTree_s_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
	fatTree_s_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
	fatTree_s_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
	network_group.addElement(fatTree_s_item);	
}

private function fatTreeAddHost(id:int,name:String, Node_obj:ArrayCollection):void{
	var date:Date = new Date();
	var fatTree_h_item:Host = new Host();
	var relativeX:Number = 255+((id-1)*63);
	var relativeY:Number = 435;
	config(fatTree_h_item, relativeX, relativeY, "h"+String(id-1));
	fatTree_h_item.setTime(date.toString());
	fatTree_h_item.setHost_ID("h~" + String(id-1) + "~" + date.toString() +"~"+ executor_name+"~"+relativeX+"~"+relativeY);
	fatTree_h_item.setHost_Name("h~"+String(id-1)); 		
	H_obj.addItem(fatTree_h_item);
	Node_obj.addItem(fatTree_h_item);
	fatTree_h_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
	fatTree_h_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
	fatTree_h_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
	network_group.addElement(fatTree_h_item);
}

private function fatTreeAddLink(F_obj:Item_image,T_obj:Item_image):void{
	var obj:ArrayCollection = new ArrayCollection();
	obj.addItem(F_obj);
	obj.addItem(T_obj);
	var link:Link = new Link(obj);
	var childrenLinks:ArrayCollection =F_obj.getChildrenLinks();
	childrenLinks.addItem(link);					
	var parentLink:ArrayCollection = T_obj.getParentLink();
	parentLink.addItem(link); 
	var adjLinks_from:ArrayCollection = F_obj.getadjLinks();
	adjLinks_from.addItem(link);
	var adjLinks_to:ArrayCollection = T_obj.getadjLinks();
	adjLinks_to.addItem(link);
	var uiObj:UIComponent = new UIComponent(); 
	uiObj.addChild(link);
	network_group.addElement(uiObj);
}
package Components
{
	import flash.display.DisplayObject;
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.geom.Point;
	import flash.media.Video;
	import flash.net.URLRequest;
	import flash.utils.Timer;
	import flash.utils.setInterval;
	import flash.utils.setTimeout;
	
	import mx.binding.utils.BindingUtils;
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.controls.Menu;
	import mx.core.FlexBitmap;
	import mx.core.FlexGlobals;
	import mx.events.MenuEvent;
	import mx.managers.CursorManager;
	import mx.managers.PopUpManager;
	import mx.messaging.AbstractConsumer;
	import mx.netmon.NetworkMonitor;
	
	import org.bytearray.gif.events.GIFPlayerEvent;
	import org.bytearray.gif.player.GIFPlayer;
	
	import spark.effects.Animate;
	import spark.effects.Move;
	
	import view.L_SimpleTitleWindowExample;
	
	import vo.Vnet_link;
	

	public class Link extends Sprite implements Component
	{
		public  var fromObj:Item_image;		
		public  var toObj:Item_image;
		public var link_id:String;
		private var myMenu:Menu;
		[Bindable]
		public var f_port:int = -1;
		[Bindable]
		public var t_port:int = -1;		
		private var point:Point = new Point();	
		public var Bandwidth:int;
		public var time:String;
		public var status:String; //add or del
		public var fop:Label = new Label();
		public var top:Label = new Label();
		private var fop_isexist:Boolean = false;
		private var top_isexist:Boolean = false;

		
/*****************mouse click and show menu *****************************/
/*************************menu data********************************/		
				
	    private var menuData:Array=
		[{label:'Properties'},{label:'Delete'}];
/*****************pop up menu, when select menu item*****************************/		
      public function showMenu(event:MouseEvent):void{
		  	
			myMenu= Menu.createMenu(this,menuData, false); 			
			//click event
			myMenu.addEventListener("itemClick", menuHandler); 
			//pop up menu 
			point.x=event.stageX; 
			point.y=event.stageY;  
			myMenu.show(point.x , point.y); 
			
		}
/**********************click menu event ***************************************/	  
	  private function menuHandler(event:MenuEvent):void {		 
		  // trace(event.item["label"]);
		  if(event.item.label=="Properties")
		  {
			  showDialogue();//
		  }
		  if(event.item.label=="Delete") 
		  { 			
			  Delete(); 
		  } 
		  
	  }
/******************pop up dialogue*****************************************/
	  public function showDialogue():void{
		  /*  pop up myPanel, judge if can interact with parent component. when true, 
			blurring the background, and can not interact with parent component */				  
		  FlexGlobals.topLevelApplication.activeLink = this;
		  
		  //  this.parent = this.host_IP;
		  var myPanel:L_SimpleTitleWindowExample = 
			  L_SimpleTitleWindowExample(PopUpManager.createPopUp(this, L_SimpleTitleWindowExample, true)); 
		  
		  myPanel.x = 420;
		  
		  myPanel.y = 170;			
	  }
 /*****************selected Link Bold line*****************************/
	  public function bold(event:MouseEvent):void{
		  if(FlexGlobals.topLevelApplication.selectedObj){
			  FlexGlobals.topLevelApplication.selectedObj.filters=[];
			  FlexGlobals.topLevelApplication.selectedObj.recover();
		  }		
		  FlexGlobals.topLevelApplication.selectedObj = this;		 
		  this.graphics.clear();
		  if(this.fromObj.className == "Switch"){
			  this.graphics.moveTo(this.fromObj.x + 39, this.fromObj.y + 8);
		  }
		  else if(this.fromObj.className == "Controller"){
			  this.graphics.moveTo(this.fromObj.x + 20, this.fromObj.y + 26);
		  }
		  else{
			  this.graphics.moveTo(this.fromObj.x + 24, this.fromObj.y + 16);
		  }
		  
		  this.graphics.lineStyle(7, 0xFF0000 , 1);
		  
		  if(this.toObj.className == "Switch"){
			  this.graphics.lineTo(this.toObj.x + 39, this.toObj.y + 8);
		  }
		  else if(this.toObj.className == "Controller"){
			  this.graphics.lineTo(this.toObj.x + 20, this.toObj.y + 26);
		  }
		  else{
			  this.graphics.lineTo(this.toObj.x + 24, this.toObj.y + 16);
		  }
		  if(this.fromObj.className != "Controller" && this.toObj.className != "Controller"){
			  showofport();
		  }
	  }
	  
 /*****************selected Link Bold line recover*****************************/
	  public function recover():void{
		  this.graphics.clear();
		  if(this.fromObj.className == "Switch"){
			  this.graphics.moveTo(this.fromObj.x + 39, this.fromObj.y + 8);
		  }
		  else if(this.fromObj.className == "Controller"){
			  this.graphics.moveTo(this.fromObj.x + 20, this.fromObj.y + 26);
		  }
		  else{
			  this.graphics.moveTo(this.fromObj.x + 24, this.fromObj.y + 16);
		  }
		  if(this.toObj.className == "Controller" || this.fromObj.className == "Controller"){
			  this.graphics.lineStyle(3, 0xFFA500, 1);
		  }
		  else{
			  this.graphics.lineStyle(3, 0x0000FF, 1);
		  }
		  if(this.toObj.className == "Switch"){
			  this.graphics.lineTo(this.toObj.x + 39, this.toObj.y + 8);
		  }
		  else if(this.toObj.className == "Controller"){
			  this.graphics.lineTo(this.toObj.x + 20, this.toObj.y + 26);
		  }
		  else{
			  this.graphics.lineTo(this.toObj.x + 24, this.toObj.y + 16);
		  }
		  
		  if(this.fop_isexist == true){
			  FlexGlobals.topLevelApplication.network_group.removeElement(this.fop);
			  this.fop_isexist = false;
		  }
		  if(this.top_isexist == true){
			  FlexGlobals.topLevelApplication.network_group.removeElement(this.top);
			  this.top_isexist = false;
		  }
	  }
	  
	  
	  
	  
/**************************menu END***********************************************/
	  
	  public function showofport():void{
		  fop.text = this.f_port + "";
		  top.text = this.t_port + "";
		  fop.x = fromObj.x;
		  fop.y = fromObj.y;
		  top.x = toObj.x;
		  top.y = toObj.y;
		  fop.scaleX = 3;
		  fop.scaleY = 3;
		  top.scaleX = 3;
		  top.scaleY = 3;
		  if(this.fromObj.className == "Switch" && this.toObj.className == "Switch"){
			  FlexGlobals.topLevelApplication.network_group.addElement(fop);
			  FlexGlobals.topLevelApplication.network_group.addElement(top);
			  this.fop_isexist = true;
			  this.top_isexist = true;
		  }
		  if(this.fromObj.className == "Switch" && this.toObj.className == "Host"){
			  FlexGlobals.topLevelApplication.network_group.addElement(fop);
			  this.fop_isexist = true;
		  }
		  if(this.fromObj.className == "Host" && this.toObj.className == "Switch"){
			  FlexGlobals.topLevelApplication.network_group.addElement(top);
			  this.top_isexist = true;
		  }
	  }
	  
/**************************structure***********************************************/
	  	
	  	public function Link(obj:ArrayCollection){
			var date:Date = new Date();
			if(obj.length==2) {  //draw line
				super();
				this.buttonMode=true;
				this.useHandCursor=true;
				this.doubleClickEnabled=true;
				this.addEventListener(MouseEvent.CLICK,bold);
				this.addEventListener(MouseEvent.DOUBLE_CLICK,showMenu);
				this.fromObj = Item_image(obj[0]);
				this.toObj = Item_image(obj[1]);	
				this.time = date.toString();
				if(this.fromObj.className == "Controller" || this.toObj.className == "Controller"){
					this.link_id = "oflink~"+FlexGlobals.topLevelApplication.link_num+"~"+this.time+"~"+FlexGlobals.topLevelApplication.executor_name;		
				}
				else {
					this.link_id = "link~"+FlexGlobals.topLevelApplication.link_num+"~"+this.time+"~"+FlexGlobals.topLevelApplication.executor_name	
				}
				FlexGlobals.topLevelApplication.link_num++;
				drawLine();	
				//setTimeout(timerMethod,5000); 
			} 
			else if(obj.length==3) {  //refresh
				this.fromObj = obj[0];
				this.toObj = obj[1];	
				this.status = obj[2];
				this.time = date.toString();
			}  
			else if(obj.length == 4){  //get
				this.fromObj = obj[0];
				this.toObj = obj[1];
				this.link_id = obj[2];
				this.buttonMode=true;
				this.useHandCursor=true;
				this.doubleClickEnabled=true;
				this.addEventListener(MouseEvent.CLICK,bold);
				this.addEventListener(MouseEvent.DOUBLE_CLICK,showMenu);
				setSrcDstPort();
				drawLine();	
			}
		}
	  
		public function setSrcDstPort():void{
			
			var vnet_links:ArrayCollection = FlexGlobals.topLevelApplication.vnet_links;
			var vnet_ofports:ArrayCollection = FlexGlobals.topLevelApplication.vnet_ofports;
			
			if(this.fromObj.className == "Switch" && this.toObj.className == "Switch"){
				for(var i1:int=0; i1<vnet_links.length; i1++){
					if(Switch(this.fromObj).getSwitch_id() == vnet_links[i1].link_src_id &&
						Switch(this.toObj).getSwitch_id() == vnet_links[i1].link_dst_id){
						for(var j1:int=0; j1< vnet_ofports.length; j1++){
							if(vnet_links[i1].link_ofport_src == vnet_ofports[j1].ofport_id){
								f_port = vnet_ofports[j1].ofport_portnum;
							}
							if(vnet_links[i1].link_ofport_dst == vnet_ofports[j1].ofport_id){
								t_port = vnet_ofports[j1].ofport_portnum;
							}
						}
						break;
					}
				}
			}
			
			if(this.fromObj.className == "Switch" && this.toObj.className == "Host"){
				for(var i2:int=0; i2<vnet_links.length; i2++){
					if(Switch(this.fromObj).getSwitch_id() == vnet_links[i2].link_src_id &&
						Host(this.toObj).getHost_ID() == vnet_links[i2].link_dst_id){
						for(var j2:int=0; j2< vnet_ofports.length; j2++){
							if(vnet_links[i2].link_ofport_src == vnet_ofports[j2].ofport_id){
								f_port = vnet_ofports[j2].ofport_portnum;
							}
						}
					}
				}
			}
			
			if(this.fromObj.className == "Host" && this.toObj.className == "Switch"){
				for(var i3:int=0; i3<vnet_links.length; i3++){
					if(Host(this.fromObj).getHost_ID() == vnet_links[i3].link_src_id &&
						Switch(this.toObj).getSwitch_id() == vnet_links[i3].link_dst_id){
						for(var j3:int=0; j3< vnet_ofports.length; j3++){
							if(vnet_links[i3].link_ofport_dst == vnet_ofports[j3].ofport_id){
								t_port = vnet_ofports[j3].ofport_portnum;
							}
						}
					}
				}
			}
			
		}
		
		public function drawLine():void {
			if(this.fromObj.className == "Switch"){
				this.graphics.moveTo(this.fromObj.x + 39, this.fromObj.y + 8);
			}
			else if(this.fromObj.className == "Controller"){
				this.graphics.moveTo(this.fromObj.x + 20, this.fromObj.y + 26);
			}
			else{
				this.graphics.moveTo(this.fromObj.x + 24, this.fromObj.y + 16);
			}
			
			if(this.toObj.className == "Controller" || this.fromObj.className == "Controller"){
				this.graphics.lineStyle(3, 0xFFA500, 1);
			}
			else{
				this.graphics.lineStyle(3, 0x0000FF, 1);
			}
			
			if(this.toObj.className == "Switch"){
				this.graphics.lineTo(this.toObj.x + 39, this.toObj.y + 8);
			}
			else if(this.toObj.className == "Controller"){
				this.graphics.lineTo(this.toObj.x + 20, this.toObj.y + 26);
			}
			else{
				this.graphics.lineTo(this.toObj.x + 24, this.toObj.y + 16);
			}
		}
		
		public function redraw():void {			
			if(fromObj != null && toObj != null) {
				this.graphics.clear();
				drawLine();
			}			
		}	 	
		
		public function Deleteadj():void{
			if(fromObj != null && toObj != null) {
				//get deleted link info
				if(FlexGlobals.topLevelApplication.vnet_vnet != null){
					var isnew:Boolean = true;
					for(var l_num:int=0; l_num<FlexGlobals.topLevelApplication.vnet_updatelinks.length; l_num++){
						if(FlexGlobals.topLevelApplication.vnet_updatelinks[l_num].link_id == this.link_id){
							isnew = false;
							break;
						}
					}
					if(isnew){
						var updateobj:ArrayCollection = new ArrayCollection();
						updateobj.addItem(fromObj);
						updateobj.addItem(toObj);
						updateobj.addItem("del");
						var updatelink:Link = new Link(updateobj);
						updatelink.link_id = this.link_id;
						FlexGlobals.topLevelApplication.vnet_updatelinks.addItem(updatelink);
					}
					else{  //when update topology, add first delete then
						for(var l_num2:int=0; l_num2<FlexGlobals.topLevelApplication.vnet_updatelinks.length; l_num2++){
							if(FlexGlobals.topLevelApplication.vnet_updatelinks[l_num2].link_id == this.link_id){
								FlexGlobals.topLevelApplication.vnet_updatelinks.removeItemAt(l_num2);
								break;
							}
						}
					}
				}
				
				if(this.fop_isexist == true){
					FlexGlobals.topLevelApplication.network_group.removeElement(fop);
					this.fop_isexist = false;
				}
				if(this.top_isexist == true){
					FlexGlobals.topLevelApplication.network_group.removeElement(top);
					this.top_isexist = false;
				}
				this.graphics.clear();
//				fromObj=null;
//				toObj=null;
				FlexGlobals.topLevelApplication.selectedObj=null;
				delete this;
		   }
		}
		
		public function Delete():void{
			if(this.fromObj != null && this.toObj != null) {
				//get deleted link info
				if(FlexGlobals.topLevelApplication.vnet_vnet != null){
					var isnew:Boolean = true;
					for(var l_num:int=0; l_num<FlexGlobals.topLevelApplication.vnet_updatelinks.length; l_num++){
						if(FlexGlobals.topLevelApplication.vnet_updatelinks[l_num].link_id == this.link_id){
							isnew = false;
							break;
						}
					}
					if(isnew){
						var updateobj:ArrayCollection = new ArrayCollection();
						updateobj.addItem(fromObj);
						updateobj.addItem(toObj);
						updateobj.addItem("del");
						var updatelink:Link = new Link(updateobj);
						updatelink.link_id = this.link_id;
						FlexGlobals.topLevelApplication.vnet_updatelinks.addItem(updatelink);
					}
					else{  //when update topology, add first delete then, delete from updatelinks list without redundant request
						for(var l_num2:int=0; l_num2<FlexGlobals.topLevelApplication.vnet_updatelinks.length; l_num2++){
							if(FlexGlobals.topLevelApplication.vnet_updatelinks[l_num2].link_id == this.link_id){
								FlexGlobals.topLevelApplication.vnet_updatelinks.removeItemAt(l_num2);
								break;
							}
						}
					}
				}
				//delete fromObj's childrenlink, adjlink
				var childlinks:ArrayCollection = fromObj.getChildrenLinks();
				var adjlinks:ArrayCollection = fromObj.getadjLinks();
				for(var i:int=0;i<childlinks.length;i++){
					if(childlinks[i] == this){
						childlinks.removeItemAt(i);
						break;
					}
				}
				for(var i2:int=0;i2<adjlinks.length;i2++){
					if(adjlinks[i2] == this){
						adjlinks.removeItemAt(i2);
						break;
					}
				}
				
				//delete toObj's parentlink, adjlink
				var parentlinks:ArrayCollection = toObj.getParentLink();
				var adjlinks_2:ArrayCollection = toObj.getadjLinks();
				for(var j:int=0;j<parentlinks.length;j++){
					if(parentlinks[j] == this){
						parentlinks.removeItemAt(j);
						break;
					}
				}
				for(var j2:int=0;j2<adjlinks_2.length;j2++){
					if(adjlinks_2[j2] == this){
						adjlinks_2.removeItemAt(j2);
						break;
					}
				}
				
				if(this.fop_isexist == true){
					FlexGlobals.topLevelApplication.network_group.removeElement(fop);
					this.fop_isexist = false;
				}
				if(this.top_isexist == true){
					FlexGlobals.topLevelApplication.network_group.removeElement(top);
					this.top_isexist = false;
				}
				this.graphics.clear();
//				fromObj=null;
//				toObj=null;
				FlexGlobals.topLevelApplication.selectedObj=null;
				delete this;
			}
		}
		
		public function getBandwidth():int{
			return this.Bandwidth;
		}
		
		public  function setBandwidth(Bandwidth:int):void{
			this.Bandwidth = Bandwidth;
		}
		
	}
}
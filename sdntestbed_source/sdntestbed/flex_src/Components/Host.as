package Components
{
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import mx.controls.Alert;
	import mx.controls.Menu;
	import mx.core.FlexGlobals;
	import mx.events.MenuEvent;
	import mx.managers.PopUpManager;
	
	import skins.Item_images_skin;
	
	import view.Vnet_hostConsoleWindow;
	import view.Vnet_hostPropertiesWindow;
	
	import vo.Vnet_host;

	public class Host extends Item_image
	{
		[Bindable]
		public var host_ID:String;
		
		[Bindable]
		public var host_Name:String;
		
		[Bindable]
		public var host_config:int = -1;
				
		[Bindable]
		public var host_IP:String;
		
		[Bindable]
		public var floating_ip:String;
		
		[Bindable]
		public var host_Subnet:String;
		
		[Bindable]
		public var switch_ID:String;
		
		[Bindable]
		public var Mac_Add:String;
		
		[Bindable]
		public var flavor:int = -1;
		
		[Bindable]
		public var image:int = -1;
		
		[Bindable]
		public var console_url:String;
		
		private var point:Point = new Point();
		
		/*****************mouse click and pop up menu*****************************/
		/*************************menu data********************************/	
		private var menuData:Array=
			[{label:'Console'},{label:'Properties'},{label:'Delete'}];
		private var myMenu:Menu;
		
		
	   public function showDialogue():void{
			/*  pop up myPanel, judge if can interact with parent component. when true, 
			blurring the background, and can not interact with parent component */		  
		   FlexGlobals.topLevelApplication.activeItem = this;
		   
		 //  this.parent = this.host_IP;
				var myPanel:Vnet_hostPropertiesWindow = 
					Vnet_hostPropertiesWindow(PopUpManager.createPopUp(this, Vnet_hostPropertiesWindow, true)); 
				
				myPanel.x = 420;
				
				myPanel.y = 170;			
	   }
	   
	   /**********************click menu event ***************************************/	  
	   private function menuHandler(event:MenuEvent):void {	
			
		   if(event.item.label=="Console") 
		   { 		
			   FlexGlobals.topLevelApplication.activeItem = this;
			   FlexGlobals.topLevelApplication.showConsole();
		   }
		   
		   if(event.item.label=="Properties") 
		   { 			
			   showDialogue();
		   } 	
		   // trace(event.item["label"]);
		   
		   if(event.item.label=="Delete") 
		   { 			
			   this.Delete();
			   Vnet_host.vnet_delete(this.getHost_ID());
		   } 		
	   }
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
	   public function Host()
	   {		
		   super();
		   this.source = "images/host_mini.jpg";
		   setStyle("skinClass", skins.Item_images_skin);
		   //enable double click(default as unable)	
		   this.doubleClickEnabled = true;
		   this.addEventListener(MouseEvent.DOUBLE_CLICK,showMenu);
	   }
		
/************************get and set host_id******************************/
		public function getHost_ID():String{
			return this.host_ID;
		}
		public override function setHost_ID(_id:String):void{
			this.host_ID=_id;
		}

/************************get and set host_subnet*****************************/
		public override function getHost_Subnet():String{
			return this.host_Subnet;
		}
		public override function setHost_Subnet(_add:String):void{
			this.host_Subnet=_add;
		}
		
/************************get and set host_name*****************************/
		public function getName():String{
			return this.host_Name;
		}
		public override function setHost_Name(_name:String):void{
			this.host_Name=_name;
		}
		
/***********************get and set host_IP********************************/
		public override function getHost_IP():String{
			return this.host_IP;
		}
		public override function setHost_IP(_ip:String):void{
			this.host_IP=_ip;
		}
		
/***********************set host_config********************************/
		public override function getHost_Config():int{
			return this.host_config;
		}
		public override function setHost_Config(_config:int):void{
			this.host_config=_config;
		}
		
/***********************get and set Switch_ID*******************************/
		public function getSwitch_ID():String{
			return this.switch_ID;
		}
		public override function setSwitch_ID(s_id:String):void{
			this.switch_ID=s_id;
		}
		
/***********************get and set Mac_Add*********************************/
		public function getMac_Add():String{
			return this.Mac_Add;
		}
		public override function setMac_Add(_add:String):void{
			this.Mac_Add=_add;
		}
		
/***********************get and set flavor*********************************/
public override function getflavor():int{
	return this.flavor;
}
public override function setflavor(_add:int):void{
	this.flavor=_add;
}

/***********************get and set image*********************************/
public override function getimage():int{
	return this.image;
}
public override function setimage(_add:int):void{
	this.image=_add;
}
/**************************end***********************************************/
	}
}
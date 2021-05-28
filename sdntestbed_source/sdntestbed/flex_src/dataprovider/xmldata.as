

// ActionScript file
private var menubarXML:XMLList =
	<>
          <menu label="File">
					<item label = "new"/>
					<item label = "Open From"/>
					<item label = "Save As"/>
					<menu label = "Export">
						<menu label = "Export Mininet SCript...">
	                       <item label = "Export topology in Topo"/>
						   <item label = "Export topology in CreateTopo"/>
                        </menu>
						<item label = "Export NS3 Script..."/>
						<item label = "Export NSDL File..."/>
						<item label = "Export openflow Script..."/>
						<item label = "Export Qos Script..."/>
					</menu>
					<item label = "Print Network Scheme"/>
			</menu>
		    <menu label="Device Information">
			   <item label = "Show Images"/>
			   <item label = "Show Instances"/>
			   <item label = "Show Flavors"/>
			   <item label="Add Controller"/>
			   <item label="Delete Controller"/>
			   <item label = "Controller"/>
			   <item label = "Switch"/>
			   <item label = "Port"/>
			   <item label = "Link"/>
			   <item label = "Flow Table"/>
			   <item label = "Host"/>
		    </menu>
			<menu label="Management">
				<item label = "Subnet Management"/>
				<item label = "Script Management"/>
			</menu>
			<menu label="Statistics">
				<item label = "TOP10 Switch"/>
				<item label = "TOP10 Flow Entries"/>
				<item label = "TOP10 Host"/>
			</menu>
			<menu label="Help">
				<item label = "Guide Book"/>
				<item label = "About"/>
			</menu>				
	</>;   //menu data

private var typicalTopoXML:XMLList =
	<>		
				<items label="Classic Topology">
					<item label = "FatTree"/>
					<item label = "BCube"/>
					<item label = "Clear"/>
				</items>
	</>;   //menu data

private var treeXML:XMLList =
	<>
				<nodes label="nodes">
					<node label="computer" preview_url="images/host.jpg"/>
					<node label="controllerOpenflow" preview_url="images/controller.jpg"/>
					<node label="switchOpenflow" preview_url="images/switch.jpg"/>
				</nodes>
				<links label="links">
					<link label="ethernet" preview_url="images/ethernet.jpg"/>
				</links>
				<topos label="topos">
					<topo label="fattree" preview_url="images/fattree.jpg"/>
					<topo label="bcube" preview_url="images/bcube.jpg"/>
				</topos>
	</>;

[Bindable]
public var menuBarCollection:XMLListCollection;  

[Bindable]
public var library:XMLListCollection;  

[Bindable]
public var typicalTopo:XMLListCollection;

public function createLibrary():void{
	menuBarCollection = new XMLListCollection(menubarXML);
	library = new XMLListCollection(treeXML);
	typicalTopo = new XMLListCollection(typicalTopoXML);
}

	
	
 //flash finish loading and XMLListCollection is built by XMLList 
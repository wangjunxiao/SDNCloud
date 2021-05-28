package cn.dlut.util;

import cn.dlut.entity.Vnet_ofport;
import java.util.Comparator;

public class Vnet_ofportComparator implements Comparator<Vnet_ofport> {

	@Override
	public int compare(Vnet_ofport ofport1,Vnet_ofport ofport2)
	{
		if(ofport1.getOfport_portnum()>ofport2.getOfport_portnum())
			return 1;
		else if(ofport1.getOfport_portnum()==ofport2.getOfport_portnum())
			return 0;
		else
			return -1;
	}
	
	
}

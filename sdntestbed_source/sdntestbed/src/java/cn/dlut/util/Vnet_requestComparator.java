package cn.dlut.util;

import java.util.Comparator;
import cn.dlut.entity.Vnet_request;

public class Vnet_requestComparator implements Comparator<Vnet_request> {

	
	@Override
	public int compare(Vnet_request r1,Vnet_request r2)
	{
		if(r1.getRequest_group().compareTo(r2.getRequest_group())>0)
			return 1;
		else if(r1.getRequest_group().compareTo(r2.getRequest_group())==0)
		{
			if(Integer.parseInt(r1.getRequest_type())>Integer.parseInt(r2.getRequest_type()))
				return 1;
			else if(Integer.parseInt(r1.getRequest_type())==Integer.parseInt(r2.getRequest_type()))
				return 0;
			else
				return -1;
		}
		else
			return -1;
		
		
	}
	
	
}

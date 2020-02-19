package Petrinet_with_GUI.Petrinet_with_GUI;

import java.util.Comparator;

public class SampleTimeComparator implements Comparator<SampleTime>{
	public int compare(SampleTime t1, SampleTime t2)
	{
		if(t1.getTime() < t2.getTime())
		{
			return -1;
		}
		if(t1.getTime() > t2.getTime())
		{
			return 1;
		}
		return 0;
	}
}

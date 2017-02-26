package org.usfirst.frc.team303.robot;


import java.util.Comparator;
import org.opencv.core.Rect;

public class ContourAreaComparator implements Comparator<Rect>{

	@Override
	public int compare(Rect arg0, Rect arg1) {
		double firstRectArea = arg0.area();
		double secondRectArea = arg1.area();
		
		   if (firstRectArea > secondRectArea){ //first is greater, shift it left
               return -1;
           }else if (firstRectArea < secondRectArea){ //first is less, shift it right
               return +1;
           }else{
               return 0; //first and second are equal, do not shift
           }
	}

}

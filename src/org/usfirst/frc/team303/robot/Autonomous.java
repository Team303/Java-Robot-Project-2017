package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	
	public Autonomous() {
		
		assembleAutonomousOne();
		arr.add(new ActionWait(999999999));
	}
	
	public void run(){
		if(arr.size()>taskNum){
			if(arr.get(taskNum).isFinished())
				taskNum++;
			arr.get(taskNum).run();
		}
	}
	
	public void assembleAutonomousOne() {
		
		arr.add(new ActionDriveStraightByEncoders(100));
		
	}
	
} 

package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

public class Autonomous {
	
	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	
	public Autonomous() {
		arr.add(new ActionWait(999999999));
		assembleAutonomousOne();
	}
	
	public void run(){
		if(arr.size()<taskNum){
			if(arr.get(taskNum).isFinished())
				taskNum++;
			arr.get(taskNum).run();
		}
	}
	
	public void assembleAutonomousOne() {
		ArrayList<Action> conditionalAction = new ArrayList<Action>();
		conditionalAction.add(new ActionDriveStraightByEncoders(500));
		ArrayList<Action> nonconditionalAction = new ArrayList<Action>();
		nonconditionalAction.add(new ActionWait(20));
		arr.add(new ActionParallelAction(conditionalAction, nonconditionalAction));
		
	}
	
} 

package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

public class Autonomous {
	
	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	
	public Autonomous() {
		
		assembleAutonomousOne();
		arr.add(new ActionWait(999999999));
	}
	
	public void run(){
		if(arr.size()>=taskNum){
			if(arr.get(taskNum).isFinished()) {
				taskNum++;
			}
			arr.get(taskNum).run();
		}
	}
	
	public void assembleAutonomousOne() {
		//arr.add(new ActionDriveStraightByEncoders(20000));
		//arr.add(new ActionWait(3));
		//arr.add(new ActionDriveStraightByEncoders(-20000));
		arr.add(new ActionDriveStraightByEncoders(10000));
		arr.add(new ActionTurnToAngle(62, true, 6));
		arr.add(new ActionWait(3));
		arr.add(new ActionTurnToGoal());
		arr.add(new ActionWait(5));
		arr.add(new ActionDriveToGoalByArea());
		//ArrayList<Action> nonConAction2 = new ArrayList<Action>();
		//nonConAction2.add(new ActionNacRac(false));
		//ArrayList<Action> conAction2 = new ArrayList<Action>();
		//conAction2.add(new ActionWait(5));
		//arr.add(new ActionParallelAction(conAction2, nonConAction2));
		//arr.add(new ActionWait(5));
		//arr.add(new ActionNacRac(true));
		//arr.add(new ActionNacRac(false));
		//arr.add(new ActionNacRac(true));
		//arr.add(new ActionWait(3));
		//arr.add(new ActionNacRac(false));
		//arr.add(new ActionWait(2));
		
		//ArrayList<Action> nonConAction1 = new ArrayList<Action>();
		//nonConAction1.add(new ActionShooter(true));
		//ArrayList<Action> conAction1 = new ArrayList<Action>();
		//conAction1.add(new ActionWait(15));
		//arr.add(new ActionParallelAction(conAction1, nonConAction1));
		//arr.add(new ActionShooter(false));
		
	}
	
} 

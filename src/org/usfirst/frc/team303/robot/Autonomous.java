package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

public class Autonomous {
	
	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	
	public Autonomous() {
		
		assembleGearFromRightStation();
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
	
	public void assembleGearFromRightStation() {
		arr.add(new ActionDriveStraightByEncoders(10000));
		arr.add(new ActionTurnToAngle(62, true, 6));
		arr.add(new ActionWait(3)); 
		arr.add(new ActionTurnToGoal());
		arr.add(new ActionWait(3)); 
		arr.add(new ActionDriveToGoalByArea());
		arr.add(getParallelAction(new ActionWait(1), new ActionNacRac(false)));
		arr.add(getParallelAction(new ActionDriveStraightByEncoders(-10000), new ActionNacRac(true)));
		
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
	
	public ActionParallelAction getParallelAction(ActionAbstract con, ActionAbstract nonCon) {
		ArrayList<Action> nonConAction = new ArrayList<Action>();
		ArrayList<Action> conAction = new ArrayList<Action>();
		return new ActionParallelAction(conAction, nonConAction);
		
	}
	
} 

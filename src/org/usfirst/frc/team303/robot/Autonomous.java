package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	
	public Autonomous() {
		
		//followArea();
		assembleGearFromNearStation();
		arr.add(new ActionWait(999999999));
	}
	
	public void run(){
		if(arr.size()>=taskNum){
			if(arr.get(taskNum).isFinished()) {
				taskNum++;
			}
			arr.get(taskNum).run();
		}
		
		SmartDashboard.putNumber("taskNum", taskNum);
	}
	
	public void assembleGearFromFarStation() {

		
	}
	
	public void assembleShooting() {
		arr.add(makeSimpleParallelAction(new ActionWait(15), new ActionShooter(true)));
		
		
	}
	
	public void assembleGearFromNearStation() { //this one works
		arr.add(new ActionDriveStraightByEncoders(9600)); 
		arr.add(new ActionTurnToAngle(-61, true, 3)); 
		arr.add(new ActionDriveToGoalByArea(11500));

		arr.add(new ActionDriveStraightByEncoders(2000)); 
		arr.add(makeSimpleParallelAction(new ActionWait(0.5), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-8000), new ActionNacRac(false)));

	}
	
	public void followArea() {
		arr.add(new ActionDriveToGoalByArea(20000));
	}
	
	public ActionParallelAction makeSimpleParallelAction(Action con, Action nonCon) {
		ArrayList<Action> nonConAction = new ArrayList<Action>();
		nonConAction.add(nonCon);
		ArrayList<Action> conAction = new ArrayList<Action>();
		conAction.add(con);
		return new ActionParallelAction(conAction, nonConAction);
		
	}
	
} 

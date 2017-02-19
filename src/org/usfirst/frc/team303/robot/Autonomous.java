package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	
	public Autonomous() {
		
	}
	
	enum AutoStates {
		Default, NearStation, FarStation, CenterStation, Boiler;
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
	
	public void assembleGearFromBoiler() {
		arr.add(makeSimpleParallelAction(new ActionWait(2), new ActionShooter(true)));
		arr.add(new ActionShooter(false));
		arr.add(new ActionDriveStraightByEncoders(-3500));
		arr.add(new ActionTurnToAngle(-200, true, 3, true));
		scoreGear();
	}
	public void assembleGearFromFarStation() {
		arr.add(new ActionDriveStraightByEncoders(9600)); 
		arr.add(new ActionTurnToAngle(61, true, 3, false)); 
		scoreGear();
	}
	
	public void assembleGearFromCenterStation() {
		scoreGear();
	}
	
	public void assembleGearFromNearStation() { //this one works
		arr.add(new ActionDriveStraightByEncoders(9600)); 
		arr.add(new ActionTurnToAngle(-61, true, 3, false)); 
		scoreGear();
	}
	
	public void scoreGear() {
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveToGoalByArea(11500));
		arr.add(new ActionDriveStraightByEncoders(3400, 0.5)); 
		arr.add(makeSimpleParallelAction(new ActionWait(1), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-6000), new ActionNacRac(false)));

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

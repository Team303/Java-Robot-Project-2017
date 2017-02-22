package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	
	public Autonomous() {
		
	}
	
	enum AutoStates {
		Default, RightPeg, LeftPeg, MidPeg, rBoiler, bBoiler;
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
	
	public void assembleGearFromRBoiler() {
		arr.add(makeSimpleParallelAction(new ActionWait(4.5), new ActionShooter(true)));
		arr.add(new ActionShooter(false));
		arr.add(new ActionDriveStraightByEncoders(-3400));
		arr.add(new ActionTurnToAngle(-190, true, 3, true, 0.3, false ));
		scoreGearBoiler();
	}
	
	public void assembleGearFromBBoiler() {
		//arr.add(makeSimpleParallelAction(new ActionWait(1), new ActionShooter(true)));
		//arr.add(new ActionShooter(false));
		//arr.add(new ActionDriveStraightByEncoders(-2500)); 
		arr.add(new ActionTurnToAngle(60, true, 3, true, 0.02, true));
		arr.add(new ActionDriveStraightByEncoders(6000));
		arr.add(new ActionTurnToAngle(90, true, 3, true, 1, true));
		scoreGearBoiler();
	}
	
	public void assembleGearFromLeftPeg() {
		arr.add(new ActionDriveStraightByEncoders(9600)); 
		arr.add(new ActionTurnToAngle(61, true, 3, false, 1, false)); 
		scoreGear();
	}
	
	public void assembleGearFromMidPeg() {
		scoreGear();
	}
	
	public void assembleGearFromRightPeg() { //this one works
		arr.add(new ActionDriveStraightByEncoders(9600)); 
		arr.add(new ActionTurnToAngle(-61, true, 3, false, 1, false)); 
		scoreGear();
	}
	
	public void scoreGearBoiler() {
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveToGoalByArea(13500));
		arr.add(makeSimpleParallelAction(new ActionDrive(), new ActionWait(1)));
		arr.add(makeSimpleParallelAction(new ActionWait(0.75), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-6000), new ActionNacRac(false)));

	}
	
	public void scoreGear() {
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveToGoalByArea(13500));
		arr.add(makeSimpleParallelAction(new ActionDrive(), new ActionWait(1)));
		arr.add(makeSimpleParallelAction(new ActionWait(0.75), new ActionNacRac(true)));
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

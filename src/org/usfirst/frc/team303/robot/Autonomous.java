package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {

	ArrayList<Action> arr = new ArrayList<Action>();
	int taskNum = 0;

	public Autonomous() {

	}

	enum AutoStates {
		Default, RightPeg, LeftPeg, MidPeg, rBoiler, bBoiler, rHopper, rShootAlign, shoot, bHopper, bBoilerAutoline, rBoilerAutoline, rCenterShoot, bCenterShoot,scoreOpRight, scoreOpLeft, scoreOpRightCent, scoreOpLeftCent, bShootAlign;
	}

	public void run() {
		if (arr.size() >= taskNum) {
			arr.get(taskNum).run();
			if (arr.get(taskNum).isFinished()) {
				taskNum++;
			}
		}

		SmartDashboard.putNumber("taskNum", taskNum);
	}

	public void assembleGearFromRBoiler() {
		arr.add(new ActionZero());
		arr.add(makeSimpleParallelAction(new ActionWait(3.5), new ActionShooter(true, 20250)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(-3400));
		arr.add(new ActionTurnToAngle(-190, true, 3, true, 0.3, false));
		arr.add(new ActionDriveStraightByEncoders(3000));
		scoreGearBoiler();
	}

	public void assembleGearFromBBoiler() {
		arr.add(new ActionZero());
		arr.add(makeSimpleParallelAction(new ActionWait(3.5), new ActionShooter(true, 20250)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionTurnToAngle(-170, true, 15, true, 0, true));
		//arr.add(new ActionTurnAngleUntilCollision(-160, false, 6, true, 0.4, true, 1));
		arr.add(new ActionDriveStraightByEncoders(3000));
		scoreGearBoiler();
	}

	public void assembleGearFromLeftPeg() {
		arr.add(new ActionDriveStraightByEncoders(9600));
		arr.add(new ActionTurnToAngle(61, true, 3));
		scoreGear();
	}

	public void assembleGearFromMidPeg() {
		arr.add(new ActionDriveStraightByEncoders(2500));
		scoreGear();
	}

	public void assembleGearFromRightPeg() {
		arr.add(new ActionDriveStraightByEncoders(9600));
		arr.add(new ActionTurnToAngle(-61, true, 3));
		scoreGear();
		arr.add(new ActionZero());
		arr.add(new ActionTurnToAngle(-119, true, 3));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(-25000));
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

	public void assembleHopperFromRedAllianceStation() {
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(9500));
		arr.add(new ActionTurnToAngle(-90, true, 3)); //turn to the hopper
		arr.add(new ActionDriveStraightByCollision(-.6, 1.5));
		arr.add(makeSimpleParallelAction(new ActionWait(1.25), new ActionIntake(1)));
		arr.add(new ActionDriveStraightByEncoders(2000));
		arr.add(new ActionTurnToAngle(-90, true, 3)); //turn to the boiler
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(7400), new ActionShooterSpinUp(true, 20250)));
		arr.add(makeSimpleParallelAction(new ActionTurnAngleUntilCollision(-30, true, 15, false, 0, false, 1), new ActionShooterSpinUp(true, 20250)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByCollision(0.85, 1),new ActionShooterSpinUp(true, 20250)));
		arr.add(makeSimpleParallelAction(new ActionWait(5),new ActionShooter(true, 20250)));
		arr.add(new ActionShooter(false, 0));
	}

	public void assembleHopperFromBlueAllianceStation() { //untested and unfinished
		arr.add(new ActionDriveStraightByEncoders(9800));
		arr.add(new ActionTurnToAngle(95, true, 6)); //turn to the hopper
		arr.add(new ActionDriveStraightByCollision(-.6, 1.5));
		arr.add(makeSimpleParallelAction(new ActionWait(2), new ActionIntake(1)));
		arr.add(new ActionDriveStraightByEncoders(2000));
		arr.add(new ActionTurnToAngle(95, true, 1)); //turn to the boiler
		arr.add(new ActionDriveStraightByEncoders(7700));
		arr.add(new ActionTurnAngleUntilCollision(30, true, 15, false, 0, false, 1));
		arr.add(makeSimpleParallelAction (new ActionDriveStraightByCollision(0.75, 1),new ActionShooterSpinUp(true, 27000)));
		arr.add(makeSimpleParallelAction(new ActionWait(5),new ActionShooter(true, 20250)));
		arr.add(new ActionShooter(false, 0));
		}
	
	public void assembleBlueBoilerAutoLine () {
		//used when another team can get the auto gear for us, so we can shoot longer
		arr.add(new ActionZero());
		arr.add(makeSimpleParallelAction(new ActionWait(7.5), new ActionShooter(true, 20250)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionTurnToAngle(-170, true, 15, true, 0, true));
		//arr.add(new ActionTurnAngleUntilCollision(-160, false, 6, true, 0.4, true, 1));
		arr.add(new ActionDriveStraightByEncoders(3000));
		scoreGearBoiler();
	}
	
	public void assembleRedBoilerAutoLine () {
		//used when another team can get the auto gear for us, so we can shoot longer
		arr.add(makeSimpleParallelAction(new ActionWait(7.5), new ActionShooter(true, 20250)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(-3400));
		arr.add(new ActionTurnToAngle(-190, true, 3, true, 0.3, false));
		arr.add(new ActionDriveStraightByEncoders(3000));
		scoreGear();
	}

	public void assembleShooter() {
		arr.add(makeSimpleParallelAction(new ActionWait(10), new ActionShooter(true, 20250)));
		arr.add(new ActionShooter(false, 0));
	}
	
	public void assembleRedCenterShoot(){
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveStraightByEncoders(2500));
		arr.add(new ActionDriveToGoalByArea(13500));
		arr.add(makeSimpleParallelAction(new ActionDrive(), new ActionWait(1)));
		arr.add(makeSimpleParallelAction(new ActionWait(0.75), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(false)));
		
		//double setpoint, boolean relative, float tolerance, boolean pivot, double pivotPower, boolean pivotDirection
		//Original Settings, too scared I will break something. ActionTurnToAngle(106, false, 1.5f) ActionDriveStraightByEncoders(16000)
		//Current settings seem to work with our fuel bumper simulation, don't know how it will fare with actual bumpers
		//Cannot test shooter at this time
		arr.add(new ActionTurnToAngle(106, false, 1f));  //109
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(16200));
		arr.add(new ActionZero());
		arr.add(makeSimpleParallelAction(new ActionTurnAngleUntilCollision(30, true, 3f, false, 1, false, 3), new ActionShooterSpinUp(true, 20250)));
		arr.add(new ActionZero());
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByCollision(0.75, 1), new ActionShooterSpinUp(true, 20250)));
		arr.add(makeSimpleParallelAction(new ActionWait(7), new ActionShooter(true, 20250)));
		
	}
	
	public void assembleBlueCenterShoot(){
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveStraightByEncoders(2500));
		arr.add(new ActionDriveToGoalByArea(13500));
		arr.add(makeSimpleParallelAction(new ActionDrive(), new ActionWait(1)));
		arr.add(makeSimpleParallelAction(new ActionWait(0.75), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(false)));
		
		//double setpoint, boolean relative, float tolerance, boolean pivot, double pivotPower, boolean pivotDirection
		
		arr.add(new ActionTurnToAngle(-101, false, 1.5f));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(17000));
		arr.add(new ActionZero());
		arr.add(makeSimpleParallelAction(new ActionTurnAngleUntilCollision(-32, true, 3f, false, 1, false, 3), new ActionShooterSpinUp(true, 20250)));
		arr.add(new ActionZero());
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByCollision(0.75, 1), new ActionShooterSpinUp(true, 20250)));
		arr.add(makeSimpleParallelAction(new ActionWait(7), new ActionShooter(true, 20250)));
	}
	
	public void assembleScoreOpRight(){
		arr.add(new ActionDriveStraightByEncoders(97500));
		arr.add(new ActionTurnToAngle(-115, true, 3));
		scoreGear();
	}
	
	public void assembleScoreOpLeft(){
		arr.add(new ActionDriveStraightByEncoders(97500));
		arr.add(new ActionTurnToAngle(115, true, 3));
		scoreGear();
	}
	
	public void assembleScoreOpRightCent(){
		arr.add(new ActionDriveStraightByEncoders(100000));
		arr.add(new ActionTurnToAngle(-90, true, 3));
		arr.add(new ActionZero());
		arr.add(new ActionWait(0.0000001));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(10000));
		arr.add(new ActionTurnToAngle(-90, true, 3));
		scoreGear();
	}
	
	public void assembleScoreOpLeftCent(){
		arr.add(new ActionDriveStraightByEncoders(97500));
		arr.add(new ActionTurnToAngle(115, true, 3));
		scoreGear();
	}
	
	
	public ActionParallelAction makeSimpleParallelAction(Action con, Action nonCon) {
		ArrayList<Action> nonConAction = new ArrayList<Action>();
		nonConAction.add(nonCon);
		ArrayList<Action> conAction = new ArrayList<Action>();
		conAction.add(con);
		return new ActionParallelAction(conAction, nonConAction);

	}

}

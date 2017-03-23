package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {

	ArrayList<Action> arr = new ArrayList<Action>();
	int taskNum = 0;

	public Autonomous() {

	}

	enum AutoStates {
		Default, RightPeg, LeftPeg, MidPeg, rBoiler, bBoiler, rHopper, rShootAlign, shoot, bHopper, bBoilerAutoline, rBoilerAutoline, rCenterShoot, bCenterShoot,scoreOpRight, scoreOpLeft, scoreOpRightCent, scoreOpLeftCent;
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
		arr.add(makeSimpleParallelAction(new ActionWait(3.5), new ActionShooter(true, 20375)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(-3400));
		arr.add(new ActionTurnToAngle(-190, true, 3, true, 0.3, false));
		scoreGearBoiler();
	}

	public void assembleGearFromBBoiler() {
		arr.add(makeSimpleParallelAction(new ActionWait(3.5), new ActionShooter(true, 20375)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionTurnAngleUntilCollision(181, true, 15, true, 0, true, 1.9));
		arr.add(new ActionTurnAngleUntilCollision(-10, true, 6, true, 0.4, true, 1));
		scoreGearBoiler();
	}

	public void assembleGearFromLeftPeg() {
		arr.add(new ActionDriveStraightByEncoders(9600));
		arr.add(new ActionTurnToAngle(61, true, 3));
		scoreGear();
	}

	public void assembleGearFromMidPeg() {
		scoreGear();
	}

	public void assembleGearFromRightPeg() {
		arr.add(new ActionDriveStraightByEncoders(9600));
		arr.add(new ActionTurnToAngle(-61, true, 3));
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

	public void assembleHopperFromRedAllianceStation() {
		arr.add(new ActionDriveStraightByEncoders(10600));
		arr.add(new ActionTurnToAngle(-90, true, 3));
		arr.add(new ActionDriveStraightByCollision(-.6));
		arr.add(makeSimpleParallelAction(new ActionWait(1), new ActionIntake(1)));
		arr.add(new ActionDriveStraightByEncoders(1000));
		arr.add(new ActionTurnToAngle(-95, true, 3));
		arr.add(makeSimpleParallelAction(new ActionWait(5),new ActionShooter(true, 27000)));
		arr.add(new ActionShooter(false, 0));
	}

	public void assembleHopperFromBlueAllianceStation() {
		arr.add(new ActionDriveStraightByEncoders(10600));
		arr.add(new ActionTurnToAngle(90, true, 3));
		arr.add(new ActionDriveStraightByCollision(-.6));
		arr.add(makeSimpleParallelAction(new ActionWait(1), new ActionIntake(1)));
		arr.add(new ActionDriveStraightByEncoders(1000));
		arr.add(new ActionTurnToAngle(110, true, 3));
		arr.add(makeSimpleParallelAction(new ActionWait(5),new ActionShooter(true, 27000)));
		arr.add(new ActionShooter(false, 0));
	}
	public void assembleBlueBoilerAutoLine () {
		//used when another team can get the auto gear for us, so we can shoot longer
		arr.add(makeSimpleParallelAction(new ActionWait(7.5), new ActionShooter(true, 20375)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionTurnAngleUntilCollision(181, true, 15, true, 0, true, 1.9));
		arr.add(new ActionTurnAngleUntilCollision(-10, true, 6, true, 0.4, true, 1));
		scoreGear();
	}
	
	public void assembleRedBoilerAutoLine () {
		//used when another team can get the auto gear for us, so we can shoot longer
		arr.add(makeSimpleParallelAction(new ActionWait(7.5), new ActionShooter(true, 20375)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(-3400));
		arr.add(new ActionTurnToAngle(-190, true, 3, true, 0.3, false));
		scoreGear();
	}

	public void assembleShooterAlignR() {
		arr.add(makeSimpleParallelAction(new ActionWait(10), new ActionShooter(true, 20375)));
		arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(-3400));
		arr.add(new ActionTurnToAngle(-190, true, 3, true, 0.3, false));
	}

	public void assembleShooter() {
		arr.add(makeSimpleParallelAction(new ActionWait(10), new ActionShooter(true, 20375)));
		arr.add(new ActionShooter(false, 0));
	}
	
	public void assembleRedCenterShoot(){
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveToGoalByArea(13500));
		arr.add(makeSimpleParallelAction(new ActionDrive(), new ActionWait(1)));
		arr.add(makeSimpleParallelAction(new ActionWait(0.75), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(false)));
		
		//double setpoint, boolean relative, float tolerance, boolean pivot, double pivotPower, boolean pivotDirection
		
		arr.add(new ActionTurnToAngle(106, false, 1.5f));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(16000));
		arr.add(new ActionZero());
		arr.add(new ActionTurnAngleUntilCollision(30, true, 3f, false, 1, false, 3));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByCollision(0.75));
		arr.add(makeSimpleParallelAction(new ActionWait(7), new ActionShooter(true, 20375)));
		
	}
	
	public void assembleBlueCenterShoot(){
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveToGoalByArea(13500));
		arr.add(makeSimpleParallelAction(new ActionDrive(), new ActionWait(1)));
		arr.add(makeSimpleParallelAction(new ActionWait(0.75), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(false)));
		
		//double setpoint, boolean relative, float tolerance, boolean pivot, double pivotPower, boolean pivotDirection
		
		arr.add(new ActionTurnToAngle(-101, false, 1.5f));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(16500));
		arr.add(new ActionZero());
		arr.add(new ActionTurnAngleUntilCollision(-30, true, 3f, false, 1, false, 3));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByCollision(0.75));
		arr.add(makeSimpleParallelAction(new ActionWait(7), new ActionShooter(true, 20375)));
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

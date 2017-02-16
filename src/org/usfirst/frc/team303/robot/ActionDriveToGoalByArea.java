package org.usfirst.frc.team303.robot;

public class ActionDriveToGoalByArea implements Action {

	double degreeSetpoint = 0;
	double pixelPerDegreeConstant = 0.084;
	double offsetConstant = 0;
	boolean goalFinished;
	boolean firstRun;
	ActionDriveStraightByEncoders driveLastBit = new ActionDriveStraightByEncoders(5000);
	ActionDriveStraightByEncoders drive = new ActionDriveStraightByEncoders(0); //used as nonconditional action
	
	
	public ActionDriveToGoalByArea() {
		firstRun = true;
	}
	
	@Override
	public void run() {
		
		if(firstRun) {
			Robot.drivebase.zeroEncoders();
		}
		
		if(Robot.camera.getArea()>9000 || Robot.drivebase.getLeftEncoder()>10000) {
			goalFinished = true;
		}
		
		if(!goalFinished) {
			drive.run();
		} else {
			driveLastBit.run();
		}
		
		if(firstRun) {
			firstRun = false;
		}
	}

	@Override
	public boolean isFinished() {
		return (goalFinished && driveLastBit.isFinished());
	}
	
}

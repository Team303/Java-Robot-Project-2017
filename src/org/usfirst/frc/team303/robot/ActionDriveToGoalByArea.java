package org.usfirst.frc.team303.robot;

public class ActionDriveToGoalByArea extends ActionAbstract implements Action {

	boolean goalFinished;
	boolean firstRun;

	int area;
	
	public ActionDriveToGoalByArea(int stopArea) {
		firstRun = true;
		area = stopArea;
	}
	
	@Override
	public void run() {
		if(firstRun) {
			Robot.drivebase.zeroEncoders();
			firstRun = false;
		}
		
		double minPower = 0.53;
		double scaledCameraArea = (((-(Robot.camera.getArea()-700))+14000)/20000); //scale the power //23000
		scaledCameraArea = scaledCameraArea > minPower ? scaledCameraArea : minPower;
		double degreeOffset = getCameraDegreeOffset();
		double[] pow = driveStraightAngle(scaledCameraArea, degreeOffset, 0.01); //power was 0.55
		Robot.drivebase.drive(pow[0], pow[1]);
		
	}

	@Override
	public boolean isFinished() {
		if(firstRun) {
			Robot.drivebase.zeroEncoders();
			firstRun = false;
			return false;
		} else {
			return (Robot.navX.collisionDetected() || Robot.camera.getArea()>area);
		}	
	}	
	
}

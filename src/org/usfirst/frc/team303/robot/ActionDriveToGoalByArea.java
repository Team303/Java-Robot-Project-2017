package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionDriveToGoalByArea extends ActionAbstract implements Action {

	boolean goalFinished;
	boolean firstRun;
	static final double pixelPerDegreeConstant = 0.146875; //0.084
	static final double offsetConstant = 20;
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
		
		double minPower = 0.43;
		double scaledCameraArea = (((-(Robot.camera.getArea()-700))+14000)/23000); //scale the power
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
	
	public double getCameraDegreeOffset() {
		if(Robot.camera.getCenterX()+offsetConstant>=(Camera.cameraResX/2)){
			double centerXOffset = (((Camera.cameraResX/2)-(Robot.camera.getCenterX()+offsetConstant)));
			SmartDashboard.putNumber("Center X Offset", centerXOffset);
			return centerXOffset*pixelPerDegreeConstant;
		}
		else {
			double centerXOffset = -1*(((Robot.camera.getCenterX()-(Camera.cameraResX/2))+offsetConstant));
			SmartDashboard.putNumber("Center X Offset", centerXOffset);
			return centerXOffset * pixelPerDegreeConstant;
		}
	}
	
	public double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}
}

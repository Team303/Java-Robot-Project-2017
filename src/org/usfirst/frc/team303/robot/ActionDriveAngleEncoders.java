package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionDriveAngleEncoders extends ActionAbstract implements Action{
	boolean firstRun;
	double targetAngle = 0;
	double encoders = 0;
	public ActionDriveAngleEncoders() {
		firstRun = true;
		
	}
	
	@Override
	public void run() {
		if(firstRun) {
			Robot.drivebase.zeroEncoders();
			firstRun = false;
		}
		
		double degreeOffset = Robot.navX.navX.getYaw() - targetAngle; 
		double[] pow = driveStraightAngle(0.55, degreeOffset, 0.015);
		Robot.drivebase.drive(-pow[0], -pow[1]);
		SmartDashboard.putNumber("Degree Offset", degreeOffset);
	}

	@Override
	public boolean isFinished() {
		encoders = ((Robot.drivebase.getLeftEncoder()+Robot.drivebase.getRightEncoder())/2);
		if(firstRun) {
			Robot.drivebase.zeroEncoders();
			firstRun = false;
			return false;
		} else {
			return (encoders<-10000);
		}	
	}
	
	public double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}
}

package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionDriveStraightByEncoders implements Action{

	double initialNavX = 0;
	double encoderEndThreshold = 0;
	double encoders = 0;
	boolean firstRun = true;
	
	public ActionDriveStraightByEncoders(double distance) {
		initialNavX = Robot.navX.getYaw();
		encoderEndThreshold = distance;
	}
	
	public void run() {
		
		if(firstRun) {
			Robot.drivebase.zeroEncoders();
			firstRun = false;
		}
		
		encoders = ((Robot.drivebase.getLeftEncoder()+Robot.drivebase.getRightEncoder())/2);
		double[] pow = {0,0};
		
		if(encoderEndThreshold>=0) { //encoders should be positive
			pow = driveStraightAngle(0.8, getDegreeOffset(initialNavX), -0.01);
			SmartDashboard.putNumber("Auto Power", pow[1]);
			Robot.drivebase.drive(pow[0], pow[1]); //when pow is reversed, tuning constant must be reversed as well
		} else { //encoders should be negative
			pow = driveStraightAngle(-0.8, getDegreeOffset(initialNavX), -0.01);
			SmartDashboard.putNumber("Auto Power", -pow[1]);
			Robot.drivebase.drive(pow[0], pow[1]);
		}
	}

	public boolean isFinished() {
		
		boolean end = false;
		
		if(encoderEndThreshold>=0) {
			end = encoders>encoderEndThreshold;
		} else {
			end = encoders<encoderEndThreshold;
		}
		
		if(end) {
			firstRun = true;
		}
		
		return end;
	}

	public double getDegreeOffset(double initialAngle) {
		return initialNavX-Robot.navX.getYaw(); //this may need to be reversed
	}
	
	public double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}
	
}

package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionDriveStraightByEncoders implements Action{

	double initialNavX = 0;
	double encoderEndThreshold = 0;
	double encoders = 0;
	boolean firstRun;
	boolean goingForward;
	
	public ActionDriveStraightByEncoders(double distance) {
		encoders = ((Robot.drivebase.getLeftEncoder()+Robot.drivebase.getRightEncoder())/2);
		encoderEndThreshold = distance;
		firstRun=true;
	}
	
	public void run() {
		
		if(firstRun) {
			Robot.drivebase.zeroEncoders();
			Robot.navX.navX.zeroYaw();
			firstRun = false;
		}
		
		encoders = ((Robot.drivebase.getLeftEncoder()+Robot.drivebase.getRightEncoder())/2);
		double[] pow = {0,0};
		
		if(encoderEndThreshold>=0) { //determines to go forward or backward
			pow = driveStraightAngle(0.8, getDegreeOffset(0), 0.01); //fwd
			SmartDashboard.putNumber("Auto Power", pow[1]);
			Robot.drivebase.drive(pow[0], pow[1]); //when pow is reversed, tuning constant must be reversed as well
		} else { 
			pow = driveStraightAngle(-0.8, getDegreeOffset(0), 0.01); //bck
			SmartDashboard.putNumber("Auto Power", -pow[1]);
			Robot.drivebase.drive(pow[0], pow[1]);
		}
	}

	public boolean isFinished() {
		boolean end = false;
		
		if(firstRun) {
			return false;
		}
		
		encoders = ((Robot.drivebase.getLeftEncoder()+Robot.drivebase.getRightEncoder())/2);
		
		if(encoderEndThreshold>=0) {
			end = encoders>encoderEndThreshold;
		} else {
			end = encoders<encoderEndThreshold;
		}
		
		return end;
	}

	public double getDegreeOffset(double initialAngle) {
		return Robot.navX.getYaw();
	}
	
	public double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}
	
}

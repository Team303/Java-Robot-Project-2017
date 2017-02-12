package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionDriveStraightByEncoders implements Action{

	double initialNavX = 0;
	double encoderEndThreshold = 0;
	
	public ActionDriveStraightByEncoders(double distance) {
		initialNavX = Robot.navX.getYaw();
		encoderEndThreshold = distance;
	}
	
	public void run() {
		double[] pow = driveStraightAngle(0.8, getDegreeOffset(initialNavX), 0.01);
		Robot.drivebase.drive(-pow[0], -pow[1]);
		SmartDashboard.putNumber("Power 1", pow[1]);
	}

	public boolean isFinished() {
		SmartDashboard.putNumber("encoder pos", Robot.drivebase.getEncPos());
		return (Robot.drivebase.getEncPos()>encoderEndThreshold);
		
	}

	public double getDegreeOffset(double initialAngle) {
		return initialNavX-Robot.navX.getYaw(); //this may need to be reversed
	}
	
	public double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}
	
}

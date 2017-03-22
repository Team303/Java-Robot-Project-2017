package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionDriveStraightByEncoders extends ActionAbstract implements Action{

	double initialNavX = 0;
	double encoderEndThreshold = 0;
	double encoders = 0;
	boolean firstRun;
	boolean goingForward;
	double power;
	double initialAngle = 0;
	Timer t = new Timer();
	double timeout;
	
	public ActionDriveStraightByEncoders(double distance, double basePower, double timeout) { //alternate constructor
		encoderEndThreshold = distance;
		firstRun=true;
		power = basePower;
		this.timeout = timeout;
	}
	
	public ActionDriveStraightByEncoders(double distance, double basePower) {
		this(distance, basePower, 15);
	}
	
	public ActionDriveStraightByEncoders(double distance) {
		this(distance, 0.8, 15);
	}
	
	public void run() {
		
		if(firstRun) {
			t.stop();
			t.reset();
			t.start();
			Robot.drivebase.zeroEncoders();
			Robot.navX.navX.zeroYaw();
			firstRun = false;
		}
		
		encoders = ((Robot.drivebase.getLeftEncoder()+Robot.drivebase.getRightEncoder())/2);
		double[] pow = {0,0};
		
		if(encoderEndThreshold>=0) { //determines to go forward or backward
			pow = driveStraightAngle(power, Robot.navX.getYaw(), 0.01); //fwd
			SmartDashboard.putNumber("Auto Power", pow[1]);
			Robot.drivebase.drive(pow[0], pow[1]); //when pow is reversed, tuning constant must be reversed as well
		} else { 
			pow = driveStraightAngle(-power, Robot.navX.getYaw(), 0.01); //bck
			SmartDashboard.putNumber("Auto Power", -pow[1]);
			Robot.drivebase.drive(pow[0], pow[1]);
		}
	}

	public boolean isFinished() {
		boolean end = false;
		
		if(firstRun) {
			Robot.drivebase.zeroEncoders();
			initialAngle = Robot.navX.getYaw();
			encoders = 0;
			firstRun = false;
			return false;
		}
		
		encoders = ((Robot.drivebase.getLeftEncoder()+Robot.drivebase.getRightEncoder())/2);
		
		if(encoderEndThreshold>=0) {
			end = (encoders>encoderEndThreshold)||t.get()>timeout; 
		} else {
			end = (encoders<encoderEndThreshold)||t.get()>timeout;
		}
		
		if(end) {
			encoders = 0;
			firstRun = true;
			t.stop();
			t.reset();
		}
		
		return end;
	}
	
	
	
}

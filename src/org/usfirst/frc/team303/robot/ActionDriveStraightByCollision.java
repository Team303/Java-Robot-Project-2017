package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class ActionDriveStraightByCollision extends ActionAbstract implements Action {

	double power;
	boolean firstRun = false;
	int startCounter = 0;
	Timer t = new Timer();
	double timeout;
	
	public ActionDriveStraightByCollision(double power, double timeoutC) {
		this.power = power;
		firstRun = true;
		startCounter = 0;
		timeout = timeoutC;
	}
	
	public void run() {
		if(firstRun){
			t.start();
			Robot.navX.navX.zeroYaw();
			firstRun = false;
		}
		
		double[] pow = {0,0};
		startCounter++;
		
		pow = driveStraightAngle(power, Robot.navX.getYaw(), 0.01); //fwd
		Robot.drivebase.drive(pow[0], pow[1]); //when pow is reversed, tuning constant must be reversed as well	
	}

	public boolean isFinished() {
		boolean collision = Robot.navX.collisionDetected();
		
		if((collision && startCounter>10) || t.get()>timeout){
			t.stop();
			t.reset();
			firstRun = true;
			startCounter = 0;
			return true;		
		}
		return false;
		
	}
	
}

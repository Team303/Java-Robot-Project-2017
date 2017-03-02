package org.usfirst.frc.team303.robot;

public class ActionDriveStraightByCollision extends ActionAbstract implements Action {

	double power;
	boolean firstRun = false;
	

	public ActionDriveStraightByCollision(double power) {
		this.power = power;
		firstRun = true;
	}
	
	public void run() {
		if(firstRun){
			
			Robot.navX.navX.zeroYaw();
			
			firstRun = false;
			
		}
		
		double[] pow = {0,0};
		
		pow = driveStraightAngle(power, Robot.navX.getYaw(), 0.01); //fwd
		Robot.drivebase.drive(pow[0], pow[1]); //when pow is reversed, tuning constant must be reversed as well	
	}

	public boolean isFinished() {
		boolean collision = Robot.navX.collisionDetected();
		
		
		if(collision){
			firstRun = true;
			
			return true;		
		}
		return false;
		
	}
	
}

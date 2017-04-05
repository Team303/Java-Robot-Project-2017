
package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class ActionShooter implements Action{

	boolean shootActive = false;
	double savedSetpoint = 0;
	double givenSetpoint;
	Timer t;
	
	public ActionShooter(boolean setShootActive, int setpoint){
		t = new Timer();
		shootActive = setShootActive;
		givenSetpoint = setpoint;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		double setpoint;

		if(!shootActive) {
			setpoint = 0;
		} else if(shootActive) {
			setpoint = givenSetpoint;
		} else {
			setpoint = savedSetpoint;
		}
		
		Robot.shooter.setSetpoint(setpoint);
		
		if(setpoint!=0 && (Robot.shooter.getSpeed()<=(setpoint*(1+Shooter.maxFeedError)) && Robot.shooter.getSpeed()>=(setpoint*(1-Shooter.maxFeedError)))) { //feed fuel if shooter is close to setpoint
			Robot.shooter.agitator.set((Robot.pdp.getCurrent(11)>=18) ? -0.4 : 1);
			Robot.shooter.indexer.set(1);
		} else {
			Robot.shooter.agitator.set(0);
			Robot.shooter.indexer.set(0);
		}
	
		
		savedSetpoint = setpoint;
		
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}

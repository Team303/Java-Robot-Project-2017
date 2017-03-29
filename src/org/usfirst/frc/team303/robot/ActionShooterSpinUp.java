package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class ActionShooterSpinUp implements Action{

	boolean shootActive = false;
	double savedSetpoint = 0;
	double givenSetpoint;
	Timer t;
	
	
	public ActionShooterSpinUp(boolean setShootActive, int setpoint){
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
		
		if(setpoint!=0) { //feed fuel if shooter is close to setpoint
			Robot.shooter.setSetpoint(setpoint);
		} else {
			Robot.shooter.setSetpoint(0);
		}
	
		
		savedSetpoint = setpoint;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}

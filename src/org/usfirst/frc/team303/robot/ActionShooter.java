
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
		
		if(setpoint!=savedSetpoint) { //setpoint changed
			if(setpoint==0) { //setpoint changed and is stopped
				t.stop();
				t.reset();
				Robot.shooter.agitator.set(0);
				Robot.shooter.indexer.set(0);
			} else { //setpoint changed and is not stopped
				t.start();
				Robot.shooter.agitator.set(0);
				Robot.shooter.indexer.set(0);
			}
		} else { //setpoint unchanged
			if(t.get()>0.55) { //setpoint unchanged and delay is over
				Robot.shooter.agitator.set(.3);
				Robot.shooter.indexer.set(1);
			} else { //setpoint unchanged and delay is not over
				Robot.shooter.agitator.set(0); 
				Robot.shooter.indexer.set(0);
			}
		}
		
		savedSetpoint = setpoint;
		
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}

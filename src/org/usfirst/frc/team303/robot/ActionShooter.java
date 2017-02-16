package org.usfirst.frc.team303.robot;

public class ActionShooter implements Action{

	boolean shootActive = false;
	
	
	public ActionShooter(boolean setShootActive){
		
		shootActive = setShootActive;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		double setpoint;

		if(!shootActive) { //set setpoint
			setpoint = 0;
			//Robot.shooter.shooter.disable();
			//Robot.shooter.shooterSlave.disable();
		} else if(shootActive) {
			//Robot.shooter.shooter.enable();
			//Robot.shooter.shooterSlave.enable();
			setpoint = -25000; // was -26150
		} else {
			setpoint = Robot.shooter.savedSetpoint;
		}
		
		Robot.shooter.setSetpoint(setpoint);
		
		if(setpoint!=Robot.shooter.savedSetpoint) { //setpoint changed
			if(setpoint==0) { //setpoint changed and is stopped
				Robot.shooter.t.stop();
				Robot.shooter.t.reset();
				Robot.shooter.agitator.set(0);
				Robot.shooter.indexer.set(0);
			} else { //setpoint changed and is not stopped
				Robot.shooter.t.start();
				Robot.shooter.agitator.set(0);
				Robot.shooter.indexer.set(0);
			}
		} else { //setpoint unchanged
			if(Robot.shooter.t.get()>0.3) { //setpoint unchanged and delay is over
				Robot.shooter.agitator.set(0.6);
				Robot.shooter.indexer.set(0.25);
			} else { //setpoint unchanged and delay is not over
				Robot.shooter.agitator.set(0); 
				Robot.shooter.indexer.set(0);
			}
		}
		
		Robot.shooter.savedSetpoint = setpoint;
		
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}

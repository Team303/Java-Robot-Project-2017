package org.usfirst.frc.team303.robot;

public class ActionIntake implements Action {

	double percentVoltage;
	
	public ActionIntake(double set){
		percentVoltage = set;
	}
	
	@Override
	public void run() {
		Robot.intake.set(percentVoltage);
		
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}

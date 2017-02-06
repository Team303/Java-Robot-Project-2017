package org.usfirst.frc.team303.robot;

public class ActionTurnToAngle implements Action {

	@Override
	public void run() {
		Robot.drivebase.drive(0.7, 0.7);
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}


}

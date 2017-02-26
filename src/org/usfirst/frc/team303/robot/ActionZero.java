package org.usfirst.frc.team303.robot;

public class ActionZero implements Action{

	@Override
	public void run() {
		Robot.drivebase.zeroEncoders();
		Robot.navX.navX.zeroYaw();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	
}

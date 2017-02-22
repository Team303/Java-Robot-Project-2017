package org.usfirst.frc.team303.robot;

public class ActionDrive extends ActionAbstract implements Action{

	@Override
	public void run() {
		Robot.drivebase.drive(0.8, 0.8);
	}

	@Override
	public boolean isFinished() {
		return true;
	}
	
	

}

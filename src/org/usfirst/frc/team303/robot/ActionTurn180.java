package org.usfirst.frc.team303.robot;

public class ActionTurn180 implements Action {

	public void run(){
		Robot.drivebase.drive(0.6, -0.6);
	}
	public boolean isFinished(){
		return true;
	}
}

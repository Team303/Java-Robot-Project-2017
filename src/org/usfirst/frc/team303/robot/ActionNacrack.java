package org.usfirst.frc.team303.robot;

public class ActionNacrack implements Action{

	@Override
	public void run() { 
		Robot.nacrac.control();
	}

	@Override
	public boolean isFinished() {
		return true;
}	
}

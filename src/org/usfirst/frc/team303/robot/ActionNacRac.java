package org.usfirst.frc.team303.robot;

public class ActionNacRac implements Action{

	boolean active = false;
	
	public ActionNacRac(boolean setActive){
		active = setActive;
	}
	
	@Override
	public void run() {
		
		if(!active) {
			Robot.nacrac.set(0.2); //power needed to travel up
		} else {
			Robot.nacrac.set(-.15); //power needed to travel down
		}
		
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}

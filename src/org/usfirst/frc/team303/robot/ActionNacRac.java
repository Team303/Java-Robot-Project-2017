package org.usfirst.frc.team303.robot;

public class ActionNacRac extends ActionAbstract implements Action{

	boolean active;
	
	public ActionNacRac(boolean setActive){
		active = setActive;
	}
	
	@Override
	public void run() {
		
		if(!active) {
			Robot.nacrac.set(0.4); //power needed to travel up
		} else {
			Robot.nacrac.set(-.4); //power needed to travel down
		}
		
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}

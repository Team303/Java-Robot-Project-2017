package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class ActionTurnMoPro extends ActionAbstract implements Action {

	double deg;
	boolean rel;
	boolean firstRun;
	double currDeg;
	double prevDeg;
	double currRotV;
	double currRotA;
	double initDeg;
	double prevRotV;
	double prevTime;
	double currTime;
	static Timer timer = new Timer();
	public ActionTurnMoPro(double degrees, boolean relative){
	deg = degrees;
	rel = relative;
	firstRun = true;
	}
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(firstRun){
			initDeg = Robot.navX.getYaw();
			prevDeg = 0;
			currRotV = 0;
			prevRotV = 0;
			currTime = 0;
			prevTime = 0;
			firstRun = false;
		}
		currTime = timer.get();
		currDeg = Robot.navX.getYaw();
		currRotV = (currDeg-prevDeg)/(currTime-prevTime);
		currRotA = (currRotV-prevRotV)/(currTime-prevTime);
		prevDeg = currDeg;
		prevRotV = currRotV;
		prevTime = currTime;
		
		
		
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}

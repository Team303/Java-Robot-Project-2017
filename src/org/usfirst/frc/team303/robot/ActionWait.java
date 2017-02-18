package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class ActionWait extends ActionAbstract implements Action{

	boolean firstRun;
	Timer t;
	double timeThreshold; //in seconds
	
	public ActionWait(double time) {
		timeThreshold = time;
		firstRun = true;
		t = new Timer();
		t.stop();
	}
	
	@Override
	public void run() {
		if(firstRun) {
			firstRun = false;
			t.start();
		}
	}

	@Override
	public boolean isFinished() {
		boolean end = t.get()>=timeThreshold;
		
		if(end) {
			firstRun = true;
			t.stop();
			t.reset();
		}
		
		return end;
	}
	
}

package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class ActionWait implements Action{

	Timer t;
	double timeThreshold; //in seconds
	
	public ActionWait(double time) {
		timeThreshold = time;
		t = new Timer();
		t.start();
	}
	
	@Override
	public void run() {
		
	}

	@Override
	public boolean isFinished() {
		return (t.get()>=timeThreshold);
	}
	
}

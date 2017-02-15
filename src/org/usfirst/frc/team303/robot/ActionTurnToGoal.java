package org.usfirst.frc.team303.robot;

public class ActionTurnToGoal implements Action{

	static final double offsetConstant = 0;
	static final double pixelPerDegreeConstant = 0.09792;
	double degreeSetpoint = 0;
	ActionTurnToAngle angleTurn;
	boolean firstRun;
	
	public ActionTurnToGoal() {
		firstRun = true;
	}

	@Override
	public void run() {
		
		if(firstRun) {
			angleTurn = new ActionTurnToAngle(getCameraDegreeOffset(), true);
		} else {
			angleTurn.run();
		}
		
		if(firstRun) {
			firstRun = false;
		}
	}

	@Override
	public boolean isFinished() {
		boolean end = false;
		
		if(!firstRun) {
			end = angleTurn.isFinished();
		}
		
		if(end) {
			firstRun = false;
		}
		
		return end;
	}
	
	public double getCameraDegreeOffset() {
		if(Robot.camera.getCenterX()+offsetConstant<= Robot.camera.cameraResX){
			return -1*((Robot.camera.cameraResX-Robot.camera.getCenterX()+offsetConstant) * pixelPerDegreeConstant);
		}
		else {
			return ((Robot.camera.getCenterX()-Robot.camera.cameraResX+offsetConstant) * pixelPerDegreeConstant);
		}
	}
		
}

package org.usfirst.frc.team303.robot;

public class ActionTurnToGoal implements Action{

	static final double offsetConstant = 0;
	static final double pixelPerDegreeConstant = 0.084;
	double degreeSetpoint = 0;
	ActionTurnToAngle angleTurn;
	boolean firstRun;
	
	public ActionTurnToGoal() {
		firstRun = true;
	}

	@Override
	public void run() {
		
		if(firstRun) {
			double degRelSetpoint = getCameraDegreeOffset();
			angleTurn = new ActionTurnToAngle(degRelSetpoint+Robot.navX.getYaw(), false, 1.0f);
			firstRun = false;
		} else {
			angleTurn.run();
		}

	}

	@Override
	public boolean isFinished() {
		boolean end = false;
		
		if(!firstRun) {
			end = angleTurn.isFinished();
		}
		
		if(end) {
			firstRun = true;
		}
		
		return end;
	}
	
	public double getCameraDegreeOffset() {
		if(Robot.camera.getCenterX()+offsetConstant>=(Camera.cameraResX/2)){
			return -1*(((Camera.cameraResX/2)-(Robot.camera.getCenterX()+offsetConstant)) * pixelPerDegreeConstant);
		}
		else {
			return (((Robot.camera.getCenterX()-(Camera.cameraResX/2))+offsetConstant) * pixelPerDegreeConstant);
		}
	}
		
}

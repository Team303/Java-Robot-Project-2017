package org.usfirst.frc.team303.robot;

public class ActionTurnToGoal implements Action{

	static final double offsetConstant = 0; //was 10 for some reason?
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
			angleTurn = new ActionTurnToAngle(degRelSetpoint, true, 1.0f);
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
		
		return end;
	}
	
	public double getCameraDegreeOffset() {
		if(Robot.camera.getCenterX()+offsetConstant>=(Camera.cameraResX/2)){
			return (((Camera.cameraResX/2)-(Robot.camera.getCenterX()+offsetConstant)) * pixelPerDegreeConstant);
		}
		else {
			return -1*(((Robot.camera.getCenterX()-(Camera.cameraResX/2))+offsetConstant) * pixelPerDegreeConstant);
		}
	}
		
}

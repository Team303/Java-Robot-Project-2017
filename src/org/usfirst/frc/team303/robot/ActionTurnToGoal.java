package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionTurnToGoal extends ActionAbstract implements Action{

	static final double offsetConstant = 50; 
	static final double pixelPerDegreeConstant = 0.1468; //0.084
	double degreeSetpoint = 0;
	ActionTurnToAngle angleTurn;
	boolean firstRun;
	
	/**
	 * Turns to the goal based on offset from center X pixel.
	 * @deprecated Does not work. Use ActionDriveToGoal instead.
	 */
	@Deprecated
	public ActionTurnToGoal() {
		firstRun = true;
	}

	@Override
	public void run() {
		
		if(firstRun) {
			double degRelSetpoint = getCameraDegreeOffset();
			SmartDashboard.putNumber("Degree Offset", degRelSetpoint);
			angleTurn = new ActionTurnToAngle(degRelSetpoint, true, 1);
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
			SmartDashboard.putNumber("Center X Offset", (-1*(((Camera.cameraResX/2)-(Robot.camera.getCenterX()+offsetConstant)))));
			return -1*(((Camera.cameraResX/2)-(Robot.camera.getCenterX()+offsetConstant)) * pixelPerDegreeConstant);
		}
		else {
			SmartDashboard.putNumber("Center X Offset", ((Robot.camera.getCenterX()-(Camera.cameraResX/2))+offsetConstant));	
			return (((Robot.camera.getCenterX()-(Camera.cameraResX/2))+offsetConstant) * pixelPerDegreeConstant);
		}
	}
		
}

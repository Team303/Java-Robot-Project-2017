package org.usfirst.frc.team303.robot;

public abstract class ActionAbstract {
	
	static final double pixelPerDegreeConstant = 0.146875;
	static final double offsetConstant = 20;
	
	public static double getCameraDegreeOffset() {
		double centerXIdeal = Camera.cameraResX/2;
		double centerXCurrent = Robot.camera.getCenterX()+offsetConstant;
		double centerXOffset = centerXIdeal-centerXCurrent;
		
		return centerXOffset*pixelPerDegreeConstant;
	}
	
	public static double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}
}

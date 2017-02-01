package org.usfirst.frc.team303.robot;

public class Autonomous {
	
	double degreeSetpoint = 0;
	double pixelPerDegreeConstant = 0.09792; //experimental constant is 0.064, theoretical constant is 0.09792
	
	public void updateDegreeSetpoint() {
		if(Robot.camera.getCenterX()<= 240){
			degreeSetpoint = Robot.navX.getYaw() - ((240-Robot.camera.getCenterX()) * pixelPerDegreeConstant);
		}
		else if(Robot.camera.getCenterX()> 240){
			degreeSetpoint = Robot.navX.getYaw() + ((Robot.camera.getCenterX()-240) * pixelPerDegreeConstant);
		}
	}
	
	public double[] centerTapeWithGyro() {
		if(degreeSetpoint==0) {
			return new double[] {0, 0};
		}
		return rotateToAngle(degreeSetpoint);
	}
	
	public double[] rotateToAngle(double setpoint) {
		Robot.navX.setSetpoint(setpoint);
		double output = Robot.navX.getPidOutput();
		return new double[] {-1*output, output};
	}
 } 

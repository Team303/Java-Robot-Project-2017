package org.usfirst.frc.team303.robot;

public class Autonomous {
	
	double degreeSetpoint = 0;
	
	public double[] centerTapeWithBangBang() {
		int range = 20; //acceptable range where robot will not turn
		int leftBound = 240-(range/2);
		int rightBound = 240+(range/2);
		double minError = 0.5;
		double centerX = Robot.camera.getCenterX();
		
		if(centerX==0) { //if camera is not connected, do not move
			return new double[] {0, 0};
		}
		
		if(!((centerX>leftBound)&&(centerX<rightBound))) { //true if robot is not pointed at tape
			double error = (240-centerX)/240; //percent error
			error = (error>minError) ? error : minError; //check that the error is greater than minError
			
			if(centerX<leftBound) {
				return new double[] {-error, error}; //robot is pointed too far to the left
			}
				return new double[] {error, -error}; //robot is pointed too far to the right
		}
		return new double[] {0, 0};
	}

	
	public void updateDegreeSetpoint() {
		
	}
	
	public double[] centerTapeWithGyro() {
		double centerX = Robot.camera.getCenterX();
		final double degreesPerPixel = 0.0643809523809524;
		double output = 0;
																	//delicious memes
		if(centerX==0) {
			return new double[] {0, 0};
		}
		
		Robot.navX.setSetpoint(centerX*degreesPerPixel);
		output = Robot.navX.getPidOutput();
		return new double[] {-1*output, output};
		
	}
	
	public double[] rotateToAngle(double setpoint) {
		Robot.navX.setSetpoint(setpoint);
		double output = Robot.navX.getPidOutput();
		return new double[] {-1*output, output};
	}
 } 

package org.usfirst.frc.team303.robot;

public class Autonomous {
	
	public Autonomous() {
		
	}
	
	public double[] centerTapeWithCamera() {
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

	public double[] centerTapeWithGyro() {
		int range = 20;
		int leftBound = 240-(range/2);
		int rightBound = 240-(range/2);
		double minOutput = 0.5;
		double centerX = Robot.camera.getCenterX();
		double error = centerX - 240;
		final double pixelsToDegrees = 0;
		double output = 0;
		
		if(centerX==0) {
			return new double[] {0, 0};
		}
		
		if(!((centerX>leftBound)&&(centerX<rightBound))) { //true if root is not pointed at tape
			Robot.navX.openController();
			Robot.navX.setSetpoint(error*pixelsToDegrees);
			output = Robot.navX.getPidOutput();
			output = (output>minOutput) ? output : minOutput; //check that output is greater than minError
			return new double[] {output, output};
		}
		else {
			Robot.navX.closeController();
			return new double[] {0, 0};
		}
	}
	
	public double[] rotateToAngle(double setpoint) {
		Robot.navX.openController();
		Robot.navX.setSetpoint(setpoint);
		double output = Robot.navX.getPidOutput();
		
		if(Robot.navX.getYaw()>setpoint) { 
			return new double[] {-output, output};
		}
		else if(Robot.navX.getYaw()<setpoint) {
			return new double[] {output, -output};
		}
		
		return new double[] {0, 0};
	}
	
}

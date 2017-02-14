package org.usfirst.frc.team303.robot;

public class ActionDriveToGoalByArea implements Action {

	double degreeSetpoint = 0;
	double pixelPerDegreeConstant = 0.09792; //experimental constant is 0.064, theoretical constant is 0.09792
	double offsetConstant = 0;
	boolean goalFinished;
	ActionDriveStraightByEncoders drive = new ActionDriveStraightByEncoders(-5000);
	
	public ActionDriveToGoalByArea() {
		updateDegreeSetpoint();
	}
	
	@Override
	public void run() {
		
		if(Robot.camera.getArea()<9000) {
			goalFinished = true;
		}
		
		if(!goalFinished) {
			updateDegreeSetpoint();
			double[] output = driveStraightAngle(-0.7, getCameraDegreeOffset(), -0.01);
			Robot.drivebase.drive(output[0], output[1]);
		} else {
			drive.run();
		}
	}

	@Override
	public boolean isFinished() {
		return (goalFinished && drive.isFinished());
	} 

	public void updateDegreeSetpoint() {
		if(Robot.camera.getCenterX()<= Robot.camera.cameraResX){
			degreeSetpoint = Robot.navX.getYaw() - ((Robot.camera.cameraResX-Robot.camera.getCenterX()) * pixelPerDegreeConstant);
		}
		else if(Robot.camera.getCenterX()> Robot.camera.cameraResX){																										                                           //bangbangalloveryou
			degreeSetpoint = Robot.navX.getYaw() + ((Robot.camera.getCenterX()-Robot.camera.cameraResX) * pixelPerDegreeConstant);
		}
	}
	
	public double getCameraDegreeOffset() {
		if(Robot.camera.getCenterX()+offsetConstant<= Robot.camera.cameraResX){
			return -1*((Robot.camera.cameraResX-Robot.camera.getCenterX()+offsetConstant) * pixelPerDegreeConstant);
		}
		else {
			return ((Robot.camera.getCenterX()-Robot.camera.cameraResX+offsetConstant) * pixelPerDegreeConstant);
		}
	}
		
	/*public double[] rotateToAngle(double setpoint) {                                                                                    
		Robot.navX.setSetpoint(setpoint);
		double output = Robot.navX.getPidOutput();
		return new double[] {-1*output, output};
	}*/

	public double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}

	
}

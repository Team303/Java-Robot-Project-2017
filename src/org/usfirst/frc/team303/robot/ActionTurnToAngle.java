package org.usfirst.frc.team303.robot;

public class ActionTurnToAngle implements Action {
	double gSetpoint;
	double degSetpoint;
	double pixelPerDegreeConstant = 0.09792;
	double offsetConstant = 0;
	
	public ActionTurnToAngle(double setpoint) {
		gSetpoint = setpoint;
		degSetpoint = setpoint*pixelPerDegreeConstant;
		Robot.navX.openController();
	}
	
	@Override
	public void run() {
		
		Robot.navX.setSetpoint(degSetpoint);
		double output = Robot.navX.getPidOutput();
		Robot.drivebase.drive(-output, output);
	}
	
	@Override
	public boolean isFinished() {
		Robot.navX.closeController();
		return Robot.navX.turnController.onTarget();
	}
	
}

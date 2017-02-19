package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionTurnToAngle extends ActionAbstract implements Action {
	
	double fSetpoint; //final setpoint to feed to controller
	double iSetpoint;
	boolean firstRun;
	boolean relativeYaw;
	int counter = 0;
	float tolerance;
	boolean biased;
	
	public ActionTurnToAngle(double setpoint, boolean relative, float toleranceC, boolean biased) {
		firstRun = true;
		tolerance = toleranceC;
		iSetpoint = setpoint;
		relativeYaw = relative;
		this.biased = biased;
	}
	
	@Override
	public void run() {
		
		if(firstRun) {
			double theta = Robot.navX.getYaw();
			fSetpoint = relativeYaw ? theta+iSetpoint : iSetpoint;
			
			if(relativeYaw) {
				fSetpoint = theta+iSetpoint;
				
				if(fSetpoint>180){
					fSetpoint-=360;
				} else if(fSetpoint<-180){
					fSetpoint+=360;	
				}
			} else {
				fSetpoint = iSetpoint;
			}
			
			SmartDashboard.putNumber("Auto NavX Setpoint", fSetpoint);
			Robot.navX.setSetpoint(fSetpoint);
			
			Robot.navX.turnController.enable();
		}
		
		double output = Robot.navX.getPidOutput();
		
		if(biased) {
			Robot.drivebase.drive(-output, 0);
		} else {
			Robot.drivebase.drive(-output, output);	
		}
		
		SmartDashboard.putNumber("NavX PID Output", output);
		
		firstRun = false;
	}
	
	@Override
	public boolean isFinished() {
		double yaw = Robot.navX.getYaw();
		double setpoint = Robot.navX.turnController.getSetpoint();
		boolean end = ((yaw<=setpoint+tolerance) && (yaw>=setpoint-tolerance));
		boolean end2 = false;
	
		if(end) {
			counter++;
		} else {
			counter = 0;
		}
		
		if(counter>=6) {
			end2 = true;
		} else {
			end2 = false;
		}
		
		if(end2) {
			firstRun = true;
			Robot.navX.turnController.disable();
		}
		
		return end2;
	}
	
}

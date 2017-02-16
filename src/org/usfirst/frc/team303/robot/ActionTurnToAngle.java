package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionTurnToAngle implements Action {
	
	double fSetpoint; //final setpoint to feed to controller
	boolean firstRun;
	int counter = 0;
	
	public ActionTurnToAngle(double setpoint, boolean relative) {
		firstRun = true;
		double theta = Robot.navX.getYaw();
		
		fSetpoint = relative ? theta+setpoint : setpoint;
		
		if(relative) {
			fSetpoint = theta+setpoint;
			
			if(fSetpoint>180){
				fSetpoint-=360;
			} else if(fSetpoint<-180){
				fSetpoint+=360;	
			}
			
		} else {
			fSetpoint = setpoint;
		}
		
		SmartDashboard.putNumber("Auto NavX Setpoint", fSetpoint);
		Robot.navX.setSetpoint(fSetpoint);
	}
	
	@Override
	public void run() {
		
		if(firstRun) {
			Robot.navX.turnController.enable();
		}
		
		//Robot.navX.turnController.enable();
		double output = Robot.navX.getPidOutput();
		Robot.drivebase.drive(-output, output);
		SmartDashboard.putNumber("NavX PID Output", output);
		
		firstRun = false;
	}
	
	@Override
	public boolean isFinished() {
		double yaw = Robot.navX.getYaw();
		double setpoint = Robot.navX.turnController.getSetpoint();
		boolean end = ((yaw<=setpoint+3) && (yaw>=setpoint-3));
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
		
		SmartDashboard.putBoolean("END STATE", end);
		SmartDashboard.putBoolean("ended auto", end2);
		if(end2) {
			Robot.navX.turnController.disable();
		}
		
		return end2;
	}
	
}

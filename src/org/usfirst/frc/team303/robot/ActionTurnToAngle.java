package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionTurnToAngle implements Action {
	
	double fSetpoint; //final setpoint to feed to controller
	boolean[] lastEnd = new boolean[4];
	boolean firstRun;
	
	public ActionTurnToAngle(double setpoint, boolean relative) {
		firstRun = true;
		double theta = Robot.navX.getYaw();
		
		fSetpoint = relative ? theta+setpoint : setpoint;
		
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
		Robot.drivebase.drive(output, -output);
		SmartDashboard.putNumber("NavX PID Output", output);
		
		firstRun = false;
	}
	
	@Override
	public boolean isFinished() {
		boolean end = Robot.navX.turnController.onTarget();
		
		lastEnd[0] = end;
		
		for(int i=0;i<lastEnd.length-1;i++) {
			lastEnd[i+1] = lastEnd[i];
		}
		
		for(boolean endState : lastEnd) {
			if(!end) {
				if(endState) {
					end = true;
				} else  {
					end = false;
				}
			}
		}
		
		if(end) {
			Robot.navX.turnController.disable();
		}
		
		return end;
	}
	
}

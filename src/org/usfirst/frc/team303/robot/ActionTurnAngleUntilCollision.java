package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionTurnAngleUntilCollision extends ActionAbstract implements Action {
	
	double fSetpoint; //final setpoint to feed to controller
	double iSetpoint;
	boolean firstRun;
	boolean relativeYaw;
	int counter = 0;
	float tolerance;
	boolean pivot;
	double pivotPower;
	boolean pivotDirection;
	Timer t = new Timer();
	double timer;
	
	public ActionTurnAngleUntilCollision(double setpoint, boolean relative, float tolerance) { //pivot is assumed false
		this(setpoint, relative, tolerance, false, 1, false, 15);
	}
	
	public ActionTurnAngleUntilCollision(double setpoint, boolean relative, float tolerance, boolean pivot, double pivotPower, boolean pivotDirection, double timerC) {
		firstRun = true;
		this.tolerance = tolerance;
		iSetpoint = setpoint;
		relativeYaw = relative;
		this.pivot = pivot;
		this.pivotPower = pivotPower;
		this.pivotDirection = pivotDirection;
		timer = timerC;
		
	}
	
	@Override
	public void run() {
		
		if(firstRun) {
			double theta = Robot.navX.getYaw();
			fSetpoint = relativeYaw ? theta+iSetpoint : iSetpoint;
			t.start();
			firstRun = false;
			
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
		
		if(pivot) {
			if(pivotDirection) {
				Robot.drivebase.drive(-pivotPower*output, output);
			}
			else {
				Robot.drivebase.drive(-output, pivotPower*output);
			}
		} else {
			Robot.drivebase.drive(-output, output);	
		}
		
		SmartDashboard.putNumber("NavX PID Output", output);
		
	}
	
	@Override
	public boolean isFinished() {
		
		double yaw = Robot.navX.getYaw();
		double setpoint = Robot.navX.turnController.getSetpoint();
		boolean end = false;
		
		if(relativeYaw) {
			end = (((yaw<=fSetpoint+tolerance) && (yaw>=fSetpoint-tolerance))) || (t.get() >= timer) ;
		} else {
			end = (((yaw<=setpoint+tolerance) && (yaw>=setpoint-tolerance))) || (t.get() >= timer) ;
		}
		
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
			t.stop();
			t.reset();
			Robot.navX.turnController.disable();
			Robot.drivebase.zeroEncoders();
		}
		
		return end2;
	}
	
}

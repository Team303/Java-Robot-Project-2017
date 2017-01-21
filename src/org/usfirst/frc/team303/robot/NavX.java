package org.usfirst.frc.team303.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class NavX implements PIDOutput { //this class controls the PID for the navX as well as the AHRS class itself
	AHRS navX;
	PIDController turnController;
	double rate; //this is the output
	
	public NavX() {
		navX = new AHRS(I2C.Port.kMXP);
	}
	
	public void initController(double P, double I, double D, double F, double tolerance) {
		turnController = new PIDController(P, I, D, F, navX, this);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1);
		turnController.setAbsoluteTolerance(tolerance);
		openController();
	}
	
	public void openController() {
		turnController.enable();
	}
	
	public void closeController() {
		turnController.disable();
	}
	
	public void setSetpoint(double setpoint) {
		turnController.setSetpoint(setpoint);
	}
	
	public double getYaw() {
		return navX.getYaw();
	}

	@Override
	public void pidWrite(double output) {
		rate = output;
	}
	
	public double getPidOutput() {
		return rate;
	}
	
}

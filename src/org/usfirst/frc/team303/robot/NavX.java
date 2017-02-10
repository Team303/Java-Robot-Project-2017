package org.usfirst.frc.team303.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;

public class NavX implements PIDOutput { //this class controls the PID for the navX as well as the AHRS class itself
	AHRS navX;
	PIDController turnController;
	int intRate = 0;
	double rate; //this is the output
	double setPoint = 0;
	double storedRate = 0;	
	
	public NavX() {
		navX = new AHRS(SPI.Port.kMXP);
	}
	
	public void initController(double P, double I, double D, double F, double tolerance) {
		turnController = new PIDController(P, I, D, F, navX, this);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-0.8, .8);
		turnController.setAbsoluteTolerance(4.0f);
		turnController.setContinuous();
		openController();
	}
	
	public void openController() {
		turnController.enable();
	}
	
	public void closeController() {
		turnController.disable();
	}
	
	public void setSetpoint(double setpoint) {
		setPoint = setpoint;
		turnController.setSetpoint(setpoint);
	}
	
	public double getYaw() {
		return navX.getYaw();
	}

	@Override
	public void pidWrite(double output) {		
		
		if((navX.getYaw()>=setPoint-0.5) && (navX.getYaw()<=setPoint+0.5)) {
			rate = 0;
		}
		else {
			rate = Double.valueOf(String.format("%.3g", output));
		}
	}
	
	public double getPidOutput() {
		return rate; 
	}
	
}

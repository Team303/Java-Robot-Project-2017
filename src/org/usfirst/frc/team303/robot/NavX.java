package org.usfirst.frc.team303.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;

public class NavX implements PIDOutput { //this class controls the PID for the navX as well as the AHRS class itself
	AHRS navX;
	PIDController turnController;
	double rate; //this is the output
	double setPoint = 0;
	
	public NavX() {
		navX = new AHRS(SPI.Port.kMXP);
		navX.setPIDSourceType(PIDSourceType.kDisplacement);
		
		turnController = new PIDController(0.06, 0.008, 0.11, navX, this); //"kill it with the d" -Josh Tatum 2k17
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1, 1);
		turnController.setAbsoluteTolerance(4.0f);
		turnController.setContinuous(true);
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
			rate = output;
	}
	
	public double getPidOutput() {
		return rate; 
	}
	
}

package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Shooter {

	CANTalon shooter;
	
	public Shooter() {
		shooter = new CANTalon(RobotMap.SHOOTER_ID);
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.setPID(0.16, .0003, .04, 0, 1000000, 1, 0);
		shooter.setSafetyEnabled(true);
		shooter.enable();
	}
	
	public void setSetpoint(double setpoint) {
		shooter.setSetpoint(setpoint);
	}
	
	public double getSpeed() {
		return shooter.getEncVelocity();
	}
	
	public void resetI() {
		shooter.clearIAccum();
	}
	
	public void setPIDF(double P, double I, double D, double F) {
		shooter.setP(P);
		shooter.setI(I);
		shooter.setD(D);
		shooter.setF(F);
	}
	
}

package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Shooter {

	CANTalon shooter;
	CANTalon shooterSlave;
	
	public Shooter() {
		shooter = new CANTalon(RobotMap.SHOOTER_ID);
		shooter.setInverted(RobotMap.SHOOTER_INV);
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.setPID(0.11, .00005, .04, 0.024, 1000000, 1, 0);
		shooter.setSafetyEnabled(true);
		shooter.enable();
		
		shooterSlave = new CANTalon(RobotMap.SHOOTER_SLAVE_ID);
		shooterSlave.changeControlMode(TalonControlMode.Follower);
		shooterSlave.set(shooter.getDeviceID());
		shooterSlave.reverseOutput(RobotMap.SHOOTER_SLAVE_INV);
		shooterSlave.setSafetyEnabled(true);
		shooterSlave.enable();
	}
	
	public void control() {
		
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

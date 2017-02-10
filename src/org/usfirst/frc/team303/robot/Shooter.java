package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;

public class Shooter {

	CANTalon shooter;
	CANTalon shooterSlave;
	Indexer indexer;
	Agitator agitator;
	Timer t;
	double savedSetpoint;
	
	public Shooter() {
		shooter = new CANTalon(RobotMap.SHOOTER_ID);
		shooter.changeControlMode(TalonControlMode.Speed);
		//shooter.setPID(1, 0, 0);
		shooter.setSafetyEnabled(true);
		shooter.reverseOutput(RobotMap.SHOOTER_INV);
		shooter.enable();
		shooter.setSetpoint(0);
		shooter.set(0);
		
		shooterSlave = new CANTalon(RobotMap.SHOOTER_SLAVE_ID);
		shooterSlave.changeControlMode(TalonControlMode.Follower);
		shooterSlave.reverseOutput(RobotMap.SHOOTER_INV);
		shooterSlave.setSafetyEnabled(true);
		shooterSlave.enable();
		shooterSlave.setSetpoint(0);
		shooterSlave.set(RobotMap.SHOOTER_ID);
		
		indexer = new Indexer();
		agitator = new Agitator();
		t = new Timer();
	}
	
	public void control() {
		
		double setpoint;

		if(OI.xBtnY) { //set setpoint
			setpoint = 0;
		} else if(OI.xBtnX) {
			setpoint = -26150;
		} else {
			setpoint = savedSetpoint;
		}
		
		setSetpoint(setpoint);
		
		if(setpoint!=savedSetpoint) { //setpoint changed
			if(setpoint==0) { //setpoint changed and is stopped
				t.stop();
				t.reset();
				agitator.set(0);
				indexer.set(0);
			} else { //setpoint changed and is not stopped
				t.start();
				agitator.set(0);
				indexer.set(0);
			}
		} else { //setpoint unchanged
			if(t.get()>0.3) { //setpoint unchanged and delay is over
				agitator.set(0.9);
				indexer.set(0.25);
			} else { //setpoint unchanged and delay is not over
				agitator.set(0); 
				indexer.set(0);
			}
		}
		
		savedSetpoint = setpoint;
	}
	
	public void setSetpoint(double setpoint) {
		shooter.set(setpoint);
		shooterSlave.set(RobotMap.SHOOTER_ID);
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

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
		
		indexer = new Indexer();
		agitator = new Agitator();
		t = new Timer();
	}
	
	public void control() {
		
		if(OI.lBtn[6]) {
			indexer.set(.8);
		}
		
		//--------------------------
		
		double setpoint;

		if(OI.xBtnY) {
			setpoint = 0;
		} else if(OI.xBtnX) {
			setpoint = -27000;
		} else {
			setpoint = savedSetpoint;
		}
		
		setSetpoint(setpoint);
		
		if(setpoint!=savedSetpoint) { //setpoint changed
			if(setpoint==0) { //setpoint changed and is stopped
				t.stop();
				t.reset();
			} else { //setpoint changed and is not stopped
				t.start();
			}
		} else { //setpoint unchanged
			if(t.get()>0.3) { //setpoint unchanged and delay is over
				agitator.set(0.8);
				indexer.set(0.8);
			}
		}
		
		savedSetpoint = setpoint;
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

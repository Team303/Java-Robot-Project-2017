package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

	CANTalon shooter;
	CANTalon shooterSlave;
	Indexer indexer;
	Agitator agitator;
	Timer t;
	double savedSetpoint;
	static final double maxFeedError = 0.15; //.15
	
	public Shooter() {
		shooter = new CANTalon(RobotMap.SHOOTER_ID);
		shooter.changeControlMode(TalonControlMode.Speed);
		setPIDF(shooter, .3, 0.000003, 1, 0.02475);
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
			//shooter.disable();
			//shooterSlave.disable();
		} else if(OI.xBtnX) {
			//shooter.enable();
			//shooterSlave.enable();
			setpoint =-20000; // was -26150
		} else {
			setpoint = savedSetpoint;
		}
		
		setSetpoint(setpoint);
		
		if(setpoint!=0 ){//&& (getSpeed()<=(setpoint*(1+maxFeedError)) && getSpeed()>=(setpoint*(1-maxFeedError)))) { //feed fuel if shooter is close to setpoint
			SmartDashboard.putNumber("agitator current", Robot.pdp.getCurrent(11));
			
			if(Robot.pdp.getCurrent(11)>=10){
				agitator.set(-0.2);
			}else{
				agitator.set(0.75);
			}
			indexer.set(.4);
		} else {
			agitator.set(0);
			indexer.set(0);
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
	
	public void setPIDF(CANTalon motor, double P, double I, double D, double F) {
		motor.setP(P);
		motor.setI(I);
		motor.setD(D);
		motor.setF(F);
	}
	
}

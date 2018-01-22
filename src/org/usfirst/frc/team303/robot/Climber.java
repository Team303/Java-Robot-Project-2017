package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber {
	
	CANTalon climber;
	CANTalon climberSlave;
	ControlStates currentControlState = ControlStates.NORMAL;
	Timer t;
	double current;
	int loadCounter;
	boolean dropped = false;
	public boolean disableState = false;
	boolean cTT = false;
	
	enum ControlStates {
		NORMAL, HIGH
	}
	
	public Climber() {
		climber = new CANTalon(RobotMap.CLIMBER_ID);
		climber.setInverted(RobotMap.CLIMBER_INV);
		climber.changeControlMode(TalonControlMode.PercentVbus);
		climber.setSafetyEnabled(true);
		
		climberSlave = new CANTalon(RobotMap.CLIMBER_SLAVE_ID);
		climberSlave.setInverted(!RobotMap.CLIMBER_INV);
		climberSlave.changeControlMode(TalonControlMode.PercentVbus);
		climberSlave.setSafetyEnabled(true);
		
		t = new Timer();
	}
	
	
	public void control() {
		
		if((OI.xLeftBumper && OI.xRightBumper)) {
			Robot.intake.set(0.8);
			set(-OI.xlY);
		} else {
			set(0);
		}
		
	}
	
	public void set(double value) {
		climber.set(value);
		climberSlave.set(value);
	}
	
	public boolean inRange(double tolerance, double x) {
		return ((x >= x-tolerance) && (x <= x+tolerance));
	}
	
	public boolean pulse(boolean input) { //this method turns a constant true signal into a pulse of true then false
		if(input) {
			if(!disableState) {
				disableState = true;
				return true;
			}
			return false;
		} 
		disableState = false;
		return false;
	}

}

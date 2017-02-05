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
	
	
	public ControlStates findControlMode() {
		current = Robot.pdp.getCurrent(RobotMap.CLIMBER_PDP_CHANNEL);
		double cT = 10; //current threshold TODO test this value
		double lag = 0.2; //in seconds
			
		if(t.get()>lag && current>cT) {
			return ControlStates.HIGH;
		}
		else{
			return ControlStates.NORMAL;
		}
		
	}
	
	public void control() {
		boolean btnState = OI.rBtn[RobotMap.CLIMBER_CLIMB_BUTTON];
		
		if(pulse(btnState)) {
			t.start();
		} else {
			t.stop();
			t.reset();
		}
		
		if(btnState) {
			if(findControlMode().equals(ControlStates.NORMAL)) {
				set(.2);
			} else {
				set(1);
			}
		}
		
		SmartDashboard.putNumber("Climber Amperage", current);
		
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

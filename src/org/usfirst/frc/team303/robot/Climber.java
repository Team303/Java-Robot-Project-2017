package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Climber {
	
	CANTalon climber;
	CANTalon climberSlave;
	
	public Climber() {
		climber = new CANTalon(RobotMap.CLIMBER_ID);
		climber.setInverted(RobotMap.CLIMBER_INV);
		climber.changeControlMode(TalonControlMode.PercentVbus);
		climber.setSafetyEnabled(true);
		
		climberSlave = new CANTalon(RobotMap.CLIMBER_SLAVE_ID);
		climberSlave.setInverted(!RobotMap.CLIMBER_INV);
		climberSlave.changeControlMode(TalonControlMode.PercentVbus);
		climberSlave.setSafetyEnabled(true);
	}
	
	public void control() {
		if(OI.rBtn[4]) {
			set(.2);
		} else if(OI.rBtn[6]) {
			set(-.2);
		} 
		
		if(OI.rBtn[3]) {
			set(1);
		} else if(OI.rBtn[5]) {
			set(-1);
		}
	}
	
	public void set(double value) {
		climber.set(value);
		climberSlave.set(value);
	}
}

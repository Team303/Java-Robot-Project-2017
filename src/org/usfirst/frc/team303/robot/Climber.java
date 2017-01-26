package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Climber {
	
	CANTalon climber;
	
	public Climber() {
		climber = new CANTalon(RobotMap.CLIMBER_ID);
		climber.setInverted(RobotMap.CLIMBER_INV);
		climber.changeControlMode(TalonControlMode.PercentVbus);
		climber.setSafetyEnabled(true);
	}
	
	public void control() {
		if(OI.lBtn[4]) {
			set(1);
		} else if(OI.lBtn[6]) {
			set(-1);
		}
	}
	
	public void set(double value) {
		climber.set(value);
	}
}

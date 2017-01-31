package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Intake {
	
	CANTalon motor = new CANTalon(RobotMap.INTAKE_ID);
	
	public Intake(){
		motor.changeControlMode(TalonControlMode.PercentVbus);
		motor.setSafetyEnabled(true);
		motor.setInverted(RobotMap.INTAKE_INV);
	}
	
	public void control(){
		if(OI.lBtn[2]){
			set(1);
		}else if(OI.lBtn[3]){
			set(-1);
		}else {
			set(0);
		}
	}
	
	public void set(double percentVoltage){
		motor.set(percentVoltage);
	}
}

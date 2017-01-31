package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Intake {
	
	CANTalon intake = new CANTalon(RobotMap.INTAKE_ID);
	
	public Intake(){
		intake.changeControlMode(TalonControlMode.PercentVbus);
		intake.setSafetyEnabled(true);
		intake.setInverted(RobotMap.INTAKE_INV);
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
		intake.set(percentVoltage);
	}
}

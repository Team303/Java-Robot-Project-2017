package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Agitator {

	CANTalon agitator;
	
	public Agitator(){
		agitator = new CANTalon(RobotMap.AGITATOR_ID);
		agitator.changeControlMode(TalonControlMode.PercentVbus);
		agitator.setInverted(RobotMap.AGITATOR_INV);
		agitator.setSafetyEnabled(true);
	}
	
	public void set(double percentVoltage) {
		agitator.set(percentVoltage);
	}
}

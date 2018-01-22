package org.usfirst.frc.team303.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Agitator {

	TalonSRX agitator;
	
	public Agitator(){
		agitator = new TalonSRX(RobotMap.AGITATOR_ID);
		agitator.setInverted(RobotMap.AGITATOR_INV);
	}
	
	public void set(double percentVoltage) {
		agitator.set(ControlMode.Current, percentVoltage);
	}
}

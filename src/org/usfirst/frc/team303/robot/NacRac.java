package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;

public class NacRac {
	CANTalon nacRac;
	
	public NacRac() {
		nacRac = new CANTalon(RobotMap.NACRAC_ID);
		nacRac.setInverted(RobotMap.NACRAC_INV);
		nacRac.setSafetyEnabled(true);
	}
	
	public void control() {
		
		if(!OI.rBtn[RobotMap.NACRAC_DOWN_BUTTON]) {
			set(0.4); //power needed to travel up
		} else {
			set(-.05); //power needed to travel down
		}
	}
	
	public void set(double percentVoltage) {
		nacRac.set(percentVoltage);
	}
}

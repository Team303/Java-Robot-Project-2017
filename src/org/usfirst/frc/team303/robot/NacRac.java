package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;

public class NacRac {
	CANTalon nacRac;
	
	public NacRac() {
		nacRac = new CANTalon(RobotMap.NACRAC_ID);
		nacRac.setInverted(RobotMap.NACRAC_INV);
		nacRac.setSafetyEnabled(true);
	}
}

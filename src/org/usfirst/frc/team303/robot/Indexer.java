package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Indexer {
	CANTalon indexer;
	
	public Indexer() {
		indexer = new CANTalon(RobotMap.INDEXER_ID);
		indexer.setInverted(RobotMap.INDEXER_INV);
		indexer.setSafetyEnabled(true);
		indexer.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void set(double percentVoltage) {
		indexer.set(percentVoltage);
	}
	
}

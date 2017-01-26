package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class VictorSixDrivebase {
	VictorSP left;
	VictorSP leftTwo;
	VictorSP leftThree;
	VictorSP right;
	VictorSP rightTwo;
	VictorSP rightThree;
	
	
	public VictorSixDrivebase() {
		
		left = new VictorSP(0);
		leftTwo = new VictorSP(1);
		leftThree = new VictorSP(2);
		right = new VictorSP(3);
		rightTwo = new VictorSP(4);
		rightThree = new VictorSP(5);
		
		left.setSafetyEnabled(false);
		leftTwo.setSafetyEnabled(false);
		leftThree.setSafetyEnabled(false);
		right.setSafetyEnabled(false);
		rightTwo.setSafetyEnabled(false);
		rightThree.setSafetyEnabled(false);
	}
	
	public void drive(double leftPower, double rightPower) {
		left.set(-leftPower);
		leftTwo.set(-leftPower);
		leftThree.set(-leftPower);
		right.set(-rightPower);
		rightTwo.set(-rightPower);
		rightThree.set(-rightPower);
	}
	
}

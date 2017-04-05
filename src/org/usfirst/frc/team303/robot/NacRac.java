package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NacRac {
	CANTalon nacRac;
	static final double pixelPerDegreeConstant = 0.146875; //0.084
	static final double offsetConstant = 0;
	
	NacRac() {
		nacRac = new CANTalon(RobotMap.NACRAC_ID);
		nacRac.setInverted(RobotMap.NACRAC_INV);
		nacRac.setSafetyEnabled(true);
	}
	
	public void control() {
		
		if(!OI.rBtn[3]) {
			set(0.4); //power needed to travel up
		} else {
			set(-.4); //power needed to travel down
		}
	}
	
	public void set(double percentVoltage) {
		nacRac.set(percentVoltage);
	}
	
	public void driveToPeg(){
		double minPower = 0.4;
		double scaledCameraArea = (((-(Robot.camera.getArea()-700))+14000)/22000+0.1); //scale the power
		scaledCameraArea = scaledCameraArea > minPower ? scaledCameraArea : minPower;
		SmartDashboard.putNumber("Vision Align Power", scaledCameraArea);
		
		double degreeOffset = ActionAbstract.getCameraDegreeOffset();
		double[] pow = ActionAbstract.driveStraightAngle(scaledCameraArea, degreeOffset, 0.011);
		Robot.drivebase.drive(pow[0], pow[1]);
	}
}

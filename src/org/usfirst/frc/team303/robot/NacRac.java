package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NacRac {
	CANTalon nacRac;
	static final double pixelPerDegreeConstant = 0.146875; //0.084
	static final double offsetConstant = 20;
	
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
		
		double degreeOffset = getCameraDegreeOffset();
		double[] pow = driveStraightAngle(scaledCameraArea, degreeOffset, 0.011);
		Robot.drivebase.drive(pow[0], pow[1]);
	}
	
	public double getCameraDegreeOffset(){
		if(Robot.camera.getCenterX()+offsetConstant>=(Camera.cameraResX/2)){
			double centerXOffset = (((Camera.cameraResX/2)-(Robot.camera.getCenterX()+offsetConstant)));
			SmartDashboard.putNumber("Center X Offset", centerXOffset);
			return centerXOffset*pixelPerDegreeConstant;
		}
		else {
			double centerXOffset = -1*(((Robot.camera.getCenterX()-(Camera.cameraResX/2))+offsetConstant));
			SmartDashboard.putNumber("Center X Offset", centerXOffset);
			return centerXOffset * pixelPerDegreeConstant;
		}
	}
	
	public double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}
}

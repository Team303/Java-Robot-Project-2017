package org.usfirst.frc.team303.robot;

import org.usfirst.frc.team303.robot.RobotMap;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

public class Drivebase {
	com.ctre.CANTalon FL;
	CANTalon FR;
	CANTalon BL;
	CANTalon BR;
	RobotDrive drivebase;
	//Encoder lDriveEnc;
	//Encoder rDriveEnc;
	double navXYaw;
	double lDriveEncDist;
	double rDriveEncDist;
	
	public Drivebase() {
		FL = new CANTalon(RobotMap.FROMT_LEFT);
		FR = new CANTalon(RobotMap.FRONT_RIGHT);
		BL = new CANTalon(RobotMap.REAR_LEFT);
		BR = new CANTalon(RobotMap.REAR_RIGHT);
		drivebaseInit();
	}
	
	public void drivebaseInit() {
		drivebase = new RobotDrive(FL, BL, FR, BR);
		drivebase.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.FRONT_LEFT_INV);
		drivebase.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.FRONT_RIGHT_INV);
		drivebase.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.REAR_LEFT_INV);
		drivebase.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.REAR_RIGHT_INV);
		drivebase.setSafetyEnabled(true);
		//lDriveEnc = new Encoder(0, 1);
		//rDriveEnc = new Encoder(2, 3);
	}
	
	public void drive(double left, double right) {
		drivebase.tankDrive(left, right);
	}
	
}

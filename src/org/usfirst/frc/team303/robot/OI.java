package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
	//static NetworkTable preferences = NetworkTable.getTable("Preferences");
	static Joystick left = new Joystick(0);
	static Joystick right = new Joystick(1);
	static XboxController xbox = new XboxController(2);
	static boolean disabledState = false;
	
	static double lX = 0, lY = 0, lZ = 0;
	static double rX = 0, rY = 0, rZ = 0;
	static double xlX = 0, xlY = 0, xrX = 0, xrY = 0;
	static double xLeftTrigger=0, xRightTrigger=0;
	
	static boolean[] lBtn = new boolean[9];
	static boolean[] rBtn = new boolean[9];	
	static boolean xBtnA, xBtnB, xBtnX, xBtnY, xLeftBumper, xRightBumper, xBtnStart, xBtnBack, xLeftStickBtn, xRightStickBtn;
	
	public static void update() {
		
		for(int i=1;i<8;i++) { //wpilib buttons are 1 indexed
			lBtn[i] = left.getRawButton(i);
			rBtn[i] = right.getRawButton(i);
			SmartDashboard.putNumber("POV value", right.getPOV());
		}
		
		updateXbox();
		//preferences = NetworkTable.getTable("Preferences");
		
	}
	
	public static void outputs() {
		
		if(RobotState.isOperatorControl() && RobotState.isAutonomous()) { //auto only outputs
			SmartDashboard.putNumber("NavX PID Setpoint", Robot.navX.turnController.getSetpoint());
		} else if (RobotState.isOperatorControl() && RobotState.isEnabled()) { //teleop only outputs
			
		} 
		
		//amperage outputs
		SmartDashboard.putNumber("Right Drive Current", Robot.pdp.getCurrent(2));
		SmartDashboard.putNumber("Right Drive Current 2", Robot.pdp.getCurrent(3));
		SmartDashboard.putNumber("Climber Current", Robot.pdp.getCurrent(14));		
		SmartDashboard.putNumber("Climber Current 2", Robot.pdp.getCurrent(15));
		SmartDashboard.putNumber("Intake current", Robot.pdp.getCurrent(10));
		SmartDashboard.putNumber("Agitator current", Robot.pdp.getCurrent(11));
		SmartDashboard.putNumber("NACRAC Current", Robot.pdp.getCurrent(4));	
		SmartDashboard.putNumber("Left Drive Current", Robot.pdp.getCurrent(12));
		SmartDashboard.putNumber("Left Drive Current 2", Robot.pdp.getCurrent(13));
		SmartDashboard.putNumber("Big Fan Current", Robot.pdp.getCurrent(6));
		SmartDashboard.putNumber("PDP Temperature", (Robot.pdp.getTemperature()*1.8)+32);
		
		//universal outputs
		SmartDashboard.putNumber("Time Elapsed", Robot.timer.get());
		SmartDashboard.putNumber("Theta", Robot.navX.getYaw());
		SmartDashboard.putNumber("Rectangle Area", Robot.camera.getArea());
		SmartDashboard.putNumber("Shooter Percent Voltage", (Math.abs(Robot.shooter.shooter.getOutputVoltage()/Robot.pdp.getVoltage())));
		SmartDashboard.putNumber("Shooter Velocity", Math.abs(Robot.shooter.getSpeed()));
		SmartDashboard.putNumber("L Encoder", Robot.drivebase.getLeftEncoder());
		SmartDashboard.putNumber("R Encoder", Robot.drivebase.getRightEncoder());
	}
	
	public static void updateXbox() {
		lX = left.getX();
		lY = left.getY();
		lZ = left.getZ();
		
		rX = right.getX();
		rY = right.getY();
		rZ = right.getZ();
		
		xlX = xbox.getX(Hand.kLeft);
		xlY = xbox.getY(Hand.kLeft);
		xrX = xbox.getX(Hand.kRight);
		xrY = xbox.getY(Hand.kRight);
		
		xBtnA = xbox.getAButton();
		SmartDashboard.putBoolean("a state", xBtnA);
		xBtnB = xbox.getBButton();
		SmartDashboard.putBoolean("b state", xBtnB);
		xBtnX = xbox.getXButton();
		xBtnY = xbox.getYButton();
		xLeftBumper = xbox.getBumper(Hand.kLeft);
		xRightBumper = xbox.getBumper(Hand.kRight);
		xBtnStart = xbox.getStartButton();
		xBtnBack = xbox.getBackButton();
		xLeftStickBtn = xbox.getStickButton(Hand.kLeft);
		xRightStickBtn = xbox.getStickButton(Hand.kRight);
		xLeftTrigger = xbox.getTriggerAxis(Hand.kLeft);
		xRightTrigger = xbox.getTriggerAxis(Hand.kRight);
	}
	
	public static boolean pulse(boolean input){
	
		if(input){
			if(!disabledState){
				disabledState = true;
				return true;
			}
			return false;
		}
		disabledState = false;
		return false;
	}
}
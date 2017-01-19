package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
	static Joystick left = new Joystick(0);
	static Joystick right = new Joystick(1);
	static XboxController xbox = new XboxController(3);
	
	static double lX = 0, lY = 0, lZ = 0;
	static double rX = 0, rY = 0, rZ = 0;
	static double xlX = 0, xlY = 0, xrX = 0, xrY = 0;
	
	static boolean[] lBtn = new boolean[9];
	static boolean[] rBtn = new boolean[9];	
	static boolean xBtnA, xBtnB, xBtnX, xBtnY, xLeftBumper, xRightBumper, xBtnStart, xBtnBack, xLeftStickBtn, xRightStickBtn;
	
	public void update() {
		
		for(int i=1;i<8;i++) { //wpilib buttons are 1 indexed
			lBtn[i] = left.getRawButton(i);
			rBtn[i] = right.getRawButton(i);
		}
	
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
		xBtnB = xbox.getBButton();
		xBtnX = xbox.getXButton();
		xBtnY = xbox.getYButton();
		xLeftBumper = xbox.getBumper(Hand.kLeft);
		xRightBumper = xbox.getBumper(Hand.kRight);
		xBtnStart = xbox.getStartButton();
		xBtnBack = xbox.getBackButton();
		xLeftStickBtn = xbox.getStickButton(Hand.kLeft);
		xRightStickBtn = xbox.getStickButton(Hand.kRight);
		
	}
}
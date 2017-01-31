package org.usfirst.frc.team303.robot;

public class RobotMap {

	//================= CAN IDs =================
	
	public static final int PCM = 0; //these don't do anything, but are here so we don't accidentally use their IDs
	public static final int PDP = 0;
	
	public static final int FROMT_LEFT = 8;
	public static final int FRONT_RIGHT = 7;
	public static final int REAR_LEFT = 4;
	public static final int REAR_RIGHT = 3;
	
	public static final boolean FRONT_LEFT_INV = true;
	public static final boolean FRONT_RIGHT_INV = true;
	public static final boolean REAR_LEFT_INV = false;
	public static final boolean REAR_RIGHT_INV = false;
	
	
	public static final int CLIMBER_ID = 0; //TODO
	public static final boolean CLIMBER_INV = false;
	
	public static final int INTAKE_ID = 10;
	public static final boolean INTAKE_INV = false;
	
	public static final int SHOOTER_ID = 6;
	
	public static final int GEAR_ID = 0;
}

package org.usfirst.frc.team303.robot;

public class RobotMap {

	//================= CAN IDs =================
	
	//these don't do anything, but are here so we don't accidentally use their IDs
	public static final int PDP = 0;
	
	public static final int FROMT_LEFT = 1;
	public static final int FRONT_RIGHT = 10;
	public static final int REAR_LEFT = 2;
	public static final int REAR_RIGHT = 11;
	
	public static final boolean FRONT_LEFT_INV = true;
	public static final boolean FRONT_RIGHT_INV = false;
	public static final boolean REAR_LEFT_INV = true;
	public static final boolean REAR_RIGHT_INV = true;
	
	
	public static final int CLIMBER_ID = 3; 
	public static final int CLIMBER_SLAVE_ID = 4;
	public static final boolean CLIMBER_INV = false;
	
	public static final int INTAKE_ID = 6;
	public static final boolean INTAKE_INV = false;
	
	public static final int SHOOTER_ID = 0;
	public static final int SHOOTER_SLAVE_ID = 0;
	public static final boolean SHOOTER_INV = false;
	public static final boolean SHOOTER_SLAVE_INV = true;
	
	public static final int NACRAC_ID = 5;
	public static final boolean NACRAC_INV = false;
}

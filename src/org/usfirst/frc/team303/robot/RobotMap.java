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
	
	public static final int CLIMBER_PDP_CHANNEL = 13;
	public static final int CLIMBER_ID = 3; 
	public static final int CLIMBER_SLAVE_ID = 4;
	public static final boolean CLIMBER_INV = false;
	public static final int CLIMBER_CLIMB_BUTTON = 2; //runs in OI.rBtn[CLIMBER_CLIMB_BUTTON];
	
	public static final int INTAKE_ID = 5;
	public static final boolean INTAKE_INV = false;
	
	public static final int SHOOTER_ID = 9;
	public static final int SHOOTER_SLAVE_ID = 8;
	public static final boolean SHOOTER_INV = false;
	public static final boolean SHOOTER_SLAVE_INV = true; 
	//XBOX BUTTON SETPOINTS FOR SHOOTER ARE IN THE SHOOTER CLASS
	
	public static final int NACRAC_ID = 7;
	public static final boolean NACRAC_INV = true;
	
	public static final int INDEXER_ID = 6;
	public static final boolean INDEXER_INV = false;
	
	public static final int AGITATOR_ID = 12;
	public static final boolean AGITATOR_INV = true;
}

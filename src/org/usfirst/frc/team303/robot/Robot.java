package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//final String defaultAuto = "Default";
	//final String customAuto = "My Auto";
	//String autoSelected;
	//SendableChooser<String> chooser = new SendableChooser<>();
	static Camera camera;
	static Shooter shooter;
	static Drivebase drivebase;
	static Timer timer = new Timer();
	static Autonomous auto;
	static NavX navX;
	static Climber climber;
	static Intake intake;
	static boolean autoRunOnce = false;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//chooser.addDefault("Default Auto", defaultAuto);
		//chooser.addObject("My Auto", customAuto);
		//SmartDashboard.putData("Auto choices", chooser);
		camera = new Camera();
		shooter = new Shooter();
		timer.start();
		drivebase = new Drivebase();
		auto = new Autonomous();
		navX = new NavX();
		climber = new Climber();
		intake = new Intake();
	}

	@Override
	public void robotPeriodic() {
		OI.update();
		OI.outputs();
	}
	
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		//autoSelected = chooser.getSelected();
		
		// autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		//System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {		
		
		if(!autoRunOnce){
			navX.initController(OI.preferences.getNumber("nP", 0), OI.preferences.getNumber("nI", 0), OI.preferences.getNumber("nD", 0), 0, 2.0f);
			auto.updateDegreeSetpoint();
		}
/*		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
			default:
			// Put default auto code here
			break;
		} */
		
		if(!navX.turnController.isEnabled()) {
			navX.turnController.enable();
		}
		
		double[] output = auto.centerTapeWithGyro();
		drivebase.drive(output[0], output[1]);
		
		autoRunOnce = true;
	}

	@Override
	public void teleopInit() {
		timer.reset();
		shooter.setPIDF(OI.preferences.getNumber("sP", 0), OI.preferences.getNumber("sI",0), OI.preferences.getNumber("sD",0), OI.preferences.getNumber("sF",0));
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		climber.control();
		intake.control();
		drivebase.drive(OI.lY, OI.rY);
		shooter.setSetpoint(OI.preferences.getNumber("shooterS", 0));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	@Override
	public void disabledPeriodic() {
		autoRunOnce = false;
	}
}


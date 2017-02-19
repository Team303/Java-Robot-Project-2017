package org.usfirst.frc.team303.robot;

import org.usfirst.frc.team303.robot.Autonomous.AutoStates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {	
	static AutoStates autoSelected;
	SendableChooser<AutoStates> chooser = new SendableChooser<>();
	static Camera camera;
	static Shooter shooter;
	static Drivebase drivebase;
	static Timer timer = new Timer();
	static Autonomous auto;
	static NavX navX;
	static Climber climber;
	static Intake intake;
	static NacRac nacrac;
	static boolean autoRunOnce = false;
	static PowerDistributionPanel pdp;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", AutoStates.Default);
		chooser.addObject("Center Auto", AutoStates.CenterStation);
		chooser.addObject("Far Auto", AutoStates.FarStation);
		chooser.addObject("Near Auto", AutoStates.NearStation);
		chooser.addObject("Boiler Auto", AutoStates.Boiler);
		SmartDashboard.putData("Auto choices", chooser);
		camera = new Camera();
		shooter = new Shooter();
		timer.start();
		drivebase = new Drivebase();
		navX = new NavX();
		climber = new Climber();
		intake = new Intake();
		pdp = new PowerDistributionPanel(RobotMap.PDP);
		nacrac = new NacRac();
		navX.navX.zeroYaw();
		
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
		auto = new Autonomous();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {		
		
		if(!autoRunOnce){
			autoSelected = chooser.getSelected();
			
			SmartDashboard.putString("Auto Selected", autoSelected.toString());
			
			switch (autoSelected) {
			case FarStation:
				auto.assembleGearFromFarStation();
				break;
			case NearStation:
				auto.assembleGearFromNearStation();
				break;
			case CenterStation:
				SmartDashboard.putString("Selected", "yes");
				auto.assembleGearFromCenterStation();
				break;
			case Boiler:
				auto.assembleGearFromBoiler();
				break;
			case Default:
				default:
				break;
			}
			
			auto.arr.add(new ActionWait(999999999));
			auto.taskNum = 0;
		}
		
		auto.run();
		
		autoRunOnce = true;
	}

	@Override
	public void teleopInit() {
		timer.reset();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		if(OI.lZ<0.5) {
			drivebase.zeroEncoders();
		}
		
		nacrac.control();
		climber.control();
		intake.control();
		camera.control();
		drivebase.drive(OI.lY, OI.rY);
		shooter.control();
		
		navX.collisionDetected();
		
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


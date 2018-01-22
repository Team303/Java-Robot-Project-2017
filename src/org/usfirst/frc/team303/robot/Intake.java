package org.usfirst.frc.team303.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {

	CANTalon intake = new CANTalon(RobotMap.INTAKE_ID);

	public Intake(){
		intake.changeControlMode(TalonControlMode.PercentVbus);
		intake.setSafetyEnabled(true);
		intake.setInverted(RobotMap.INTAKE_INV);
	}

	public void control(){

		if(OI.lBtn[14]) {
			set(-1);
		} else if(OI.lBtn[15]) {
			set(1);
		} else if(OI.xLeftBumper && OI.xRightBumper) {
			//do nothing
		} else if(!OI.xBtnA && !OI.xBtnB && !OI.lBtn[14] && !OI.lBtn[15]) {
			set(0);
		} else if(OI.xBtnB || OI.lBtn[15]){
			set(1);
		}else if(OI.xBtnA){
			set(-1);
		}

	}

	public void set(double percentVoltage){
		intake.set(percentVoltage);
		SmartDashboard.putNumber("intake pvoltage", percentVoltage);
	}
}

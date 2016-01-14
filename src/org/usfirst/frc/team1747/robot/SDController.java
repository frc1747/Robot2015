package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SDController {

	private OI oi;
	private Elevator elevator;
	private DriveTrain driveTrain;

	public SDController() {
		oi = Robot.getOI();
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putNumber("Dampening Constant", .8);
		SmartDashboard.putNumber("Left Damper", 0.56);
		SmartDashboard.putNumber("Right Damper", 0.3);
	}

	public void init() {
		elevator = Robot.getElevator();
		driveTrain = Robot.getDrive();
	}

	public double getDampeningConstant() {
		return SmartDashboard.getNumber("Dampening Constant");
	}

	public double getElevatorP() {
		return SmartDashboard.getNumber("Elevator P");
	}

	public double getElevatorI() {
		return SmartDashboard.getNumber("Elevator I");
	}

	public double getElevatorD() {
		return SmartDashboard.getNumber("Elevator D");
	}
	
	public double getRightDamper() {
		return SmartDashboard.getNumber("Right Damper");
	}
	
	public double getLeftDamper() {
		return SmartDashboard.getNumber("Left Damper");
	}

	public void refresh() {
		oi.getCyborg().logToSmartDashboard();
		elevator.logToSmartDashboard();
		driveTrain.logToSmartDashboard();
	}
}
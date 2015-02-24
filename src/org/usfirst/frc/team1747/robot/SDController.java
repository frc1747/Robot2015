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
		SmartDashboard.putNumber("Dampening Constant", .6);
		SmartDashboard.putNumber("Angle 1", 25);
    	SmartDashboard.putNumber("Distance 1", 0.35);//Measured in Carls  //3/3.125
    	SmartDashboard.putNumber("Distance 2", 3.3/3.125);
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

	public void refresh() {
		oi.getCyborg().logToSmartDashboard();
		elevator.logToSmartDashboard();
		driveTrain.logToSmartDashboard();
	}
}
package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CalibrateElevator extends Command {
	
	Elevator elevator;
	
	public CalibrateElevator(){
		elevator=Robot.getElevator();
		requires(elevator);
	}
	
	protected void initialize() {
		elevator.disablePID();
	}

	protected void execute() {
		elevator.manualElevatorDown();
	}

	protected boolean isFinished() {
		return elevator.isAtLowerLimit();
	}

	protected void end() {
		elevator.enablePID();
	}

	protected void interrupted() {
	}
}
package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrateElevator extends Command {

	Elevator elevator;
	
	public CalibrateElevator(){
		elevator=Robot.getElevator();
		requires(elevator);
	}
	
	protected void initialize() {
	}

	protected void execute() {
		elevator.elevatorDown();
	}

	protected boolean isFinished() {
		return elevator.isAtLowerLimit();
	}

	protected void end() {
		elevator.resetPIDController();
	}

	protected void interrupted() {
	}
}

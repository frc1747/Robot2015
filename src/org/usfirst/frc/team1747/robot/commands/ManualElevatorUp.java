package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ManualElevatorUp extends Command {

	Elevator elevator;

	public ManualElevatorUp() {
		elevator = Robot.getElevator();
		requires(elevator);
	}

	protected void initialize() {
	}

	protected void execute() {
		elevator.setSetpoint(elevator.getSetpoint()+0.1);
		if((((double)elevator.getPosition())*6.375+4.0)<=elevator.getSetpoint()){
			elevator.setCurrentPosition(1);
		}
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
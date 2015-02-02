package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class IncreaseElevatorLevel extends Command {
	
	Elevator elevator;
	
	public IncreaseElevatorLevel(){
		elevator=Robot.getElevator();
		requires(elevator);
	}

	protected void initialize() {
	}

	protected void execute() {
		elevator.increaseElevatorLevel();
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}

package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class IncreaseElevatorLevel extends Command {

	Elevator elevator;
	long start = 0, waitTime = 0;

	public IncreaseElevatorLevel(long waitTime) {
		this.waitTime = waitTime;
		elevator = Robot.getElevator();
		requires(elevator);
	}

	protected void initialize() {
		elevator.resetBump();
		elevator.increaseElevatorLevel();
		start = System.currentTimeMillis();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return System.currentTimeMillis() - start > waitTime;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class DecreaseElevatorLevel extends Command {
	
	Elevator elevator;
	long start = 0, waitTime = 0;

	public DecreaseElevatorLevel(long waitTime) {
		this.waitTime = waitTime*1000;
		elevator=Robot.getElevator();
		requires(elevator);
	}

	protected void initialize() {
		elevator.decreaseElevatorLevel();
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

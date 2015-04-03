package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class FeederCommand extends Command {

	Elevator elevator;

	public FeederCommand() {
		elevator = Robot.getElevator();
		requires(elevator);
	}

	protected void initialize() {
		elevator.bumpDown();
		Timer.delay(0.2);
		elevator.bumpDown();
		Timer.delay(0.2);
		elevator.bumpDown();
		Timer.delay(0.2);
		elevator.bumpDown();
		Timer.delay(0.2);
	}

	protected void execute() {
		Timer.delay(1);
		elevator.bumpUp();
		Timer.delay(0.2);
		elevator.bumpUp();
		Timer.delay(0.2);
		elevator.bumpUp();
		Timer.delay(0.2);
		elevator.bumpUp();
		Timer.delay(0.2);		
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
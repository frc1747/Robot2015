package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Setpoint2Preset extends Command {

	Elevator elevator;

	public Setpoint2Preset() {
		elevator = Robot.getElevator();
		requires(elevator);
	}

	protected void initialize() {
		if(elevator.getCurrentPosition() < 3){
			elevator.setCurrentPos(3);
			elevator.moveToLevel();
			Timer.delay(0.2);
		}
		elevator.setCurrentPos(5);
		elevator.moveToLevel();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
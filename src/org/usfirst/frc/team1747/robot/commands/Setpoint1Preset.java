package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Setpoint1Preset extends Command {

	Elevator elevator;

	public Setpoint1Preset() {
		elevator = Robot.getElevator();
		requires(elevator);
	}

	protected void initialize() {
		if(elevator.getCurrentPosition() > 4){
			elevator.setCurrentPos(4);
			elevator.moveToLevel();
			Timer.delay(0.2);
		}
		elevator.setCurrentPos(2);
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
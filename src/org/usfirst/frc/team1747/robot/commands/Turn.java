package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {

	DriveTrain drive;
	double angle;
	double speed;

	public Turn(double degree, double speed) {
		drive = Robot.getDrive();
		requires(drive);
		angle = degree;
		this.speed = speed;
		
	}

	protected void initialize() {
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		drive.tankDrive(0, 0);
	}

	protected void interrupted() {
	}
}

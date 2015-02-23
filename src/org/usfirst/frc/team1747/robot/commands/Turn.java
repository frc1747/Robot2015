package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		drive.resetAngle();
	}

	protected void execute() {
		drive.hDrive(0, 0, speed * ((angle - drive.getAngle()) < 0 ? -1 : 1));
	}

	protected boolean isFinished() {
		return (Math.abs(drive.getAngle() - angle) <= 1.0);
	}

	protected void end() {
		drive.setLeftMiddleRightMotor(0, 0, 0);
	}

	protected void interrupted() {
	}
}

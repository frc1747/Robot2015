package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command {

	DriveTrain drive;
	double distance;
	double currPos;
	double time;

	public DriveStraight(double distance) {
		drive = Robot.getDrive();
		requires(drive);
		this.distance = distance;
	}

	protected void initialize() {
		drive.resetAngle();
		currPos = 0;
		time = Timer.getFPGATimestamp();
	}

	protected void execute() {
		SmartDashboard.putNumber("Straight Pos", currPos);
		drive.hDrive(0, 0.5, -(drive.getAngle() / 50.0));
		currPos += (((drive.getLeftSpeed() + drive.getRightSpeed()) / 2.0) * (Timer
				.getFPGATimestamp() - time));
		time = Timer.getFPGATimestamp();
	}

	protected boolean isFinished() {
		return (Math.abs(distance - currPos) <= 0.1);
	}

	protected void end() {
		drive.setLeftMiddleRightMotor(0, 0, 0);

	}

	protected void interrupted() {
	}
}
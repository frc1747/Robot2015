package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetInfo extends Command {

	DriveTrain drive;
	double storedPositionRight = 0;
	double storedPositionLeft = 0;

	public GetInfo() {
		this.drive = Robot.getDrive();
		requires(drive);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		System.out.println("Right Position = "
				+ (drive.getRightPosition() - storedPositionRight));
		System.out.println("Left Position = "
				+ (drive.getLeftPosition() - storedPositionLeft));
		storedPositionRight = drive.getRightPosition();
		storedPositionLeft = drive.getLeftPosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class TestDrive extends Command {
	
	DriveTrain drive;
	
    public TestDrive() {
    	this.drive=Robot.getDrive();
        requires(drive);
    }

    protected void initialize() {
    }
    
    protected void execute() {
    	drive.arcadeDrive(0.9, 0);
    	Timer.delay(2);
    	drive.arcadeDrive(-0.9, 0);
    	Timer.delay(2);
    	drive.arcadeDrive(0, 0.9);
    	Timer.delay(2);
    	drive.arcadeDrive(0, -0.9);
    	Timer.delay(2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

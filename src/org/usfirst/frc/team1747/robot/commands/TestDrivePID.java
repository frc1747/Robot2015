package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestDrivePID extends Command {

	DriveTrain driveTrain;
	
    public TestDrivePID() {
    	driveTrain=Robot.getDrive();
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//driveTrain.setSetpoint(8*12);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO Fix this
//    	driveTrain.executePID();
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

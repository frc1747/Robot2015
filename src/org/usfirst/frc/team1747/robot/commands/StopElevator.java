package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopElevator extends Command {
	
	Elevator elevator;
	
    public StopElevator() {
        elevator = Robot.getElevator();
        requires(elevator);
    }

    protected void initialize() {
    }

    protected void execute() {
    	elevator.setSetpoint(elevator.getPosition());
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

package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.PrecisionCyborgController;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopElevator extends Command {

	Elevator elevator;
	PrecisionCyborgController cyborg;

	public TeleopElevator() {
		elevator = Robot.getElevator();
		cyborg=Robot.getOI().getCyborg();
		requires(elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(cyborg.getButtonX().get()){
			elevator.elevatorDown();
		}else if(cyborg.getButtonTriangle().get()){
			elevator.elevatorUp();
		}else{
			elevator.elevatorStop();
		}
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

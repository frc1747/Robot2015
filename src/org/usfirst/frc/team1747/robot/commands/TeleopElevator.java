package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Cyborg;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopElevator extends Command {

	Elevator elevator;

	public TeleopElevator() {
		elevator = Robot.getElevator();
		requires(elevator);
		
	}

	protected void initialize() {
	}

	protected void execute() {
		Cyborg cyborg = Robot.getOI().getCyborg();
		elevator.logToSmartDashboard();
		if (cyborg.getButtonTwo().get()) {
			elevator.manualElevatorDown();
		} else if (cyborg.getButtonFour().get()) {
			elevator.manualElevatorUp();
		} else {
			elevator.manualElevatorStop();
		}
		
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}

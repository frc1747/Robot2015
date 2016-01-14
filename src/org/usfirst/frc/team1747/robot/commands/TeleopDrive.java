package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Cyborg;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.SDController;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

	DriveTrain drive;
	SDController sd;
	double storedPositionRight = 0;
	double storedPositionLeft = 0;

	public TeleopDrive() {
		this.drive = Robot.getDrive();
		this.sd = Robot.getSD();
		requires(drive);
	}

	protected void initialize() {
	}

	protected void execute() {
		Cyborg cyborg = Robot.getOI().getCyborg();
		double damper = 1;
		if (cyborg.getLeftBumper().get()) {
			damper = sd.getLeftDamper();
		}
		else if (cyborg.getRightBumper().get()) {
			damper = sd.getRightDamper();
		}
		drive.arcadeDrive(Math.pow(cyborg.getLeftVert(), 3) * damper,
						  Math.pow(cyborg.getRightHoriz(), 3) * damper);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
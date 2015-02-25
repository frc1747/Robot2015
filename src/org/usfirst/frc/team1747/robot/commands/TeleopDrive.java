package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Cyborg;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

	DriveTrain drive;
	double storedPositionRight = 0;
	double storedPositionLeft = 0;

	public TeleopDrive() {
		this.drive = Robot.getDrive();
		requires(drive);
	}

	protected void initialize() {
	}

	protected void execute() {
		Cyborg cyborg = Robot.getOI().getCyborg();
		if (cyborg.getLeftTrigger().get()) {
			drive.hDrive(-.5, 0, 0);
		} else if (cyborg.getRightTrigger().get()) {
			drive.hDrive(.5, 0, 0);
		} else if (cyborg.getLeftBumper().get()
				&& cyborg.getRightBumper().get()) {
			drive.hDrive(Math.pow(cyborg.getLeftHoriz(), 3) * 2.0 / 9.0,
					Math.pow(cyborg.getLeftVert(), 3) * 2.0 / 9.0,
					Math.pow(cyborg.getRightHoriz(), 3) * 2.0 / 9.0);
		} else if (cyborg.getLeftBumper().get()) {
			drive.hDrive(Math.pow(cyborg.getLeftHoriz(), 3) * 2.0 / 3.0,
					Math.pow(cyborg.getLeftVert(), 3) * 2.0 / 3.0,
					Math.pow(cyborg.getRightHoriz(), 3) * 2.0 / 3.0);
		} else if (cyborg.getRightBumper().get()) {
			drive.hDrive(Math.pow(cyborg.getLeftHoriz(), 3) * 1.0 / 3.0,
					Math.pow(cyborg.getLeftVert(), 3) * 1.0 / 3.0,
					Math.pow(cyborg.getRightHoriz(), 3) * 1.0 / 3.0);
		} else {
			drive.hDrive(Math.pow(cyborg.getLeftHoriz(), 3),
					Math.pow(cyborg.getLeftVert(), 3),
					Math.pow(cyborg.getRightHoriz(), 3));
		}
		if (cyborg.getLeftBumper().get()) {
			System.out.println("Angle = " + drive.getAngle());
			System.out.println("Right Position = "
					+ (drive.getRightPosition() - storedPositionRight));
			System.out.println("Left Position = "
					+ (drive.getLeftPosition() - storedPositionLeft));
			drive.resetAngle();
			storedPositionRight = drive.getRightPosition();
			storedPositionLeft = drive.getLeftPosition();
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

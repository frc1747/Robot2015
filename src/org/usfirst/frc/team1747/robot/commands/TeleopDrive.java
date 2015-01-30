package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Cyborg;
import org.usfirst.frc.team1747.robot.PrecisionCyborgController;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

	DriveTrain drive;
	
    public TeleopDrive() {
    	this.drive=Robot.getDrive();
        requires(drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Cyborg cyborg=Robot.getOI().getCyborg();
    	drive.hDrive(cyborg.getLeftHoriz(), cyborg.getLeftVert(),cyborg.getRightHoriz());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

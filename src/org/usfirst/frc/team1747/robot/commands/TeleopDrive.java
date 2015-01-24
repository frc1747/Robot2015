package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.CyborgController;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

	DriveTrain drive;
	CyborgController cyborg;
	
    public TeleopDrive() {
    	this.drive=Robot.getDrive();
    	this.cyborg=Robot.getOI().getCyborg();
        requires(drive);
    }

    protected void initialize() {
    }

    protected void execute() {
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

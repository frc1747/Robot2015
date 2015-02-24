package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

	double waitTime;
	
    public Wait(double seconds) {
        this.waitTime = seconds;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Timer.delay(waitTime);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

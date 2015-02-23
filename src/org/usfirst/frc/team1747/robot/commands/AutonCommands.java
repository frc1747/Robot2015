package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommands extends CommandGroup {
	private static double waitTime = 0.5;
	public AutonCommands(){
		//addSequential(new CalibrateElevator());
		addSequential(new AutoPattern1());
	}
}

package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommands extends CommandGroup {
	public AutonCommands(){
		addSequential(new CalibrateElevator());
	}
}

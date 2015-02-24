package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SimpleAuto extends CommandGroup {

	public SimpleAuto() {
		addSequential(new CalibrateElevator());
		addSequential(new DriveStraight(-5.0));
	}
}

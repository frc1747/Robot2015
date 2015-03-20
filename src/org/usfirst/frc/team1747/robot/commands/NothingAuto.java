package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
 
public class NothingAuto extends CommandGroup {

	public NothingAuto() {
		addSequential(new CalibrateElevator());
	}
}

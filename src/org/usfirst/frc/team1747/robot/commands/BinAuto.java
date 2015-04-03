package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
 
public class BinAuto extends CommandGroup {

	public BinAuto() {
		addSequential(new CalibrateElevator());
		addSequential(new IncreaseElevatorLevel(1));
		addSequential(new IncreaseElevatorLevel(0));
		addSequential(new DriveStraight(-3.3));
	}
}

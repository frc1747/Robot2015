package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
 
public class SimpleAuto extends CommandGroup {

	public SimpleAuto() {
		addSequential(new CalibrateElevator());
		//addParallel(new DriveStraight(0.5));
		addSequential(new IncreaseElevatorLevel(1));
		addSequential(new DriveStraight(0.25));
		addSequential(new DecreaseElevatorLevel(1));
		addSequential(new DriveStraight(-0.25));			
//		addSequential(new Turn(90, 0.6));
//		addSequential(new DriveStraight(5.0));
	}
}

package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestElevator extends CommandGroup {

	public TestElevator() {
		this.addSequential(new CalibrateElevator());
		this.addSequential(new IncreaseElevatorLevel(1));
		this.addSequential(new IncreaseElevatorLevel(1));
		this.addSequential(new IncreaseElevatorLevel(1));
		this.addSequential(new IncreaseElevatorLevel(1));
		this.addSequential(new DecreaseElevatorLevel());
		this.addSequential(new DecreaseElevatorLevel());
		this.addSequential(new DecreaseElevatorLevel());
		this.addSequential(new DecreaseElevatorLevel());
	}
}

package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AdvancedAuto extends CommandGroup {

	public AdvancedAuto() {
		addSequential(new CalibrateElevator());

		addSequential(new IncreaseElevatorLevel(1));
		// You have tote 1
		addSequential(new Strafe(0.65));
		addSequential(new DriveStraight(1.5));
		addSequential(new Wait(0.5));
		addSequential(new Strafe(-1.0)); // Go left to line up with next tote
		addSequential(new DriveStraight(0.85));
		addSequential(new Wait(0.3));
		addSequential(new DriveStraight(0.07));
		addSequential(new Wait(3.0));
		addSequential(new CalibrateElevator());
		addSequential(new IncreaseElevatorLevel(1));
		// You have tote 2
		addSequential(new Strafe(0.65));
		addSequential(new DriveStraight(1.5));
		addSequential(new Wait(0.5));
		addSequential(new Strafe(-1.0)); // Go left to line up with next tote
		addSequential(new DriveStraight(0.85));
		addSequential(new Wait(0.3));
		addSequential(new DriveStraight(0.07));
		addSequential(new Wait(3.0));
		addSequential(new CalibrateElevator());
		addSequential(new IncreaseElevatorLevel(1));
		// You have tote3
		addSequential(new Strafe(6));
		addSequential(new DecreaseElevatorLevel());
		addSequential(new DriveStraight(-0.5)); //Stop. Hammer Time!
	}
}

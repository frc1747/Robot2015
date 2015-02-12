package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestCommand extends CommandGroup {
    
    public  TestCommand() {
    	this.addSequential(new TestDrive());
    	this.addSequential(new TestElevator());
    }
}

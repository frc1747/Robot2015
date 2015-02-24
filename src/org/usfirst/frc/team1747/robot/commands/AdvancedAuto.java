package org.usfirst.frc.team1747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AdvancedAuto extends CommandGroup {
	
	double angle1;
	double distance1;
	double distance2;
	
    public  AdvancedAuto() {
    	SmartDashboard.putNumber("Angle 1", 25);
    	SmartDashboard.putNumber("Distance 1", 0.35);//Measured in Carls  //3/3.125
    	SmartDashboard.putNumber("Distance 2", 3.3/3.125);
    	angle1 = SmartDashboard.getNumber("Angle 1");
    	distance1 = SmartDashboard.getNumber("Distance 1");
    	distance2 = SmartDashboard.getNumber("Distance 2");
    	addSequential(new CalibrateElevator());
    	addSequential(new BumpUp());
    	addSequential(new Turn(angle1, 0.2));
    	addSequential(new DriveStraight(distance1));
    	addSequential(new Turn(angle1 * -2.5, 0.2)); //Speed with container = 0.3
    	addSequential(new Turn(angle1 * 0.5, 0.2));
    	addSequential(new Wait(0.05));
    	addSequential(new IncreaseElevatorLevel());
    	addSequential(new IncreaseElevatorLevel());
    	addSequential(new DriveStraight(distance1+0.27));
    	addSequential(new Turn(angle1*0.74, 0.2));
    	addSequential(new Wait(0.16));
    	addSequential(new DriveStraight(distance1*2.87));
    	addSequential(new Wait(0.15));
    	/*SmartDashboard.putNumber("Angle 1", 30);
    	SmartDashboard.putNumber("Distance 1", 1.2);//3/3.125
    	SmartDashboard.putNumber("Distance 2", 3.3/3.125);
    	angle1 = SmartDashboard.getNumber("Angle 1");
    	distance1 = SmartDashboard.getNumber("Distance 1");
    	distance2 = SmartDashboard.getNumber("Distance 2");
    	 * 
    	 * addSequential(new IncreaseElevatorLevel());
        addSequential(new Turn(angle1));
        addSequential(new DriveStraight(distance1));
        addSequential(new Turn((angle1 * -2)));
        addSequential(new DriveStraight(distance1 - 0.2));
        //addSequential(new Turn(angle1));
        //addSequential(new DriveStraight(distance2/3));
        */
    }
}

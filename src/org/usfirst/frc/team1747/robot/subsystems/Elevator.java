package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
/**
 *
 */
public class Elevator extends PIDSubsystem {
	CANJaguar leftElevatorJag;
	CANJaguar middleElevatorJag;
	CANJaguar rightElevatorJag;
	
	public Elevator() {
		super ("Elevator",0,0,0);
		leftElevatorJag = new CANJaguar(RobotMap.ELELVATOR_CIM_LEFT);
		middleElevatorJag = new CANJaguar(RobotMap.ELELVATOR_CIM_MIDDLE);
		rightElevatorJag = new CANJaguar(RobotMap.ELELVATOR_CIM_RIGHT);
		
	
	}
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }


	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}


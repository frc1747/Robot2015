package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;

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
	SDController sd;
	
	public Elevator() {
		super ("Elevator",0,0,0);
		leftElevatorJag = new CANJaguar(RobotMap.ELELVATOR_CIM_LEFT);
		middleElevatorJag = new CANJaguar(RobotMap.ELELVATOR_CIM_MIDDLE);
		rightElevatorJag = new CANJaguar(RobotMap.ELELVATOR_CIM_RIGHT);
		sd=Robot.getSD();
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
	
	public void updatePID(){
		getPIDController().setPID(sd.getElevatorP(), sd.getElevatorI(), sd.getElevatorD(), sd.getElevatorF());		
		
	}
}


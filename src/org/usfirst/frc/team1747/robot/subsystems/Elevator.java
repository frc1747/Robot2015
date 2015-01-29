package org.usfirst.frc.team1747.robot.subsystems;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.CANJaguar;
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
	
	public void setElevatorMotors(double elevatorSpeed){
		leftElevatorJag.set(elevatorSpeed);
		middleElevatorJag.set(elevatorSpeed);
		rightElevatorJag.set(elevatorSpeed);
		
	}
	public void elevatorDown(){
		setElevatorMotors(-0.5);
		
	}
	public void elevatorUp(){
		setElevatorMotors(0.5);
	}
	public void elevatorStop(){
		setElevatorMotors(0);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
	
	public void updatePID(){
		getPIDController().setPID(sd.getElevatorP(), sd.getElevatorI(), sd.getElevatorD(), sd.getElevatorF());		
		
	}
}


package org.usfirst.frc.team1747.robot.subsystems;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
public class Elevator extends PIDSubsystem {

	CANJaguar leftElevatorJag;
	CANJaguar middleElevatorJag;
	CANJaguar rightElevatorJag;
	Encoder elevatorEncoder;
	DigitalInput lowerLimit;
	DigitalInput upperLimit;
	SDController sd;

	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;
	private static final double ENCODER_REVS_12_22 = 11.0 * Math.PI * 1.805 / 12.0;
	private static final double ENCODER_REVS_21_22 = 11.0 * Math.PI * 1.805 / 21.0;
	private int position = 0;
	private static final double POSITION_ZERO=0;
	private static final double POSITION_ONE=1;
	private static final double POSITION_TWO=2;
	private static final double POSITION_THREE=3;
	private static final double POSITION_FOUR=4;

	public Elevator() {
		super("Elevator", P, I, D);
		leftElevatorJag = new CANJaguar(RobotMap.ELEVATOR_CIM_LEFT);
		middleElevatorJag = new CANJaguar(RobotMap.ELEVATOR_CIM_MIDDLE);
		rightElevatorJag = new CANJaguar(RobotMap.ELEVATOR_CIM_RIGHT);
		lowerLimit = new DigitalInput(RobotMap.LOWER_LIMIT_SWITCH);
		upperLimit = new DigitalInput(RobotMap.UPPER_LIMIT_SWITCH);
		sd=Robot.getSD();
		SmartDashboard.putNumber("Elevator P", P);
		SmartDashboard.putNumber("Elevator I", I);
		SmartDashboard.putNumber("Elevator D", D);
		position=0;
	}

	public void setElevatorMotors(double elevatorSpeed){
		if(isAtLowerLimit()&&elevatorSpeed<0)
		{
			elevatorSpeed=0;
			resetPIDController();
		}else if(isAtUpperLimit()&&elevatorSpeed>0){
			elevatorSpeed=0;
		}
		leftElevatorJag.set(elevatorSpeed);
		middleElevatorJag.set(elevatorSpeed);
		rightElevatorJag.set(elevatorSpeed);
	}

	public void setElevatorLevel(double level){
		setSetpoint(level);
	}

	public void elevatorDown(){
		if (lowerLimit.get()) {
			elevatorStop();
		}
		else {
			setElevatorMotors(-.5);
		}
	}

	public void elevatorUp(){
		if (upperLimit.get()){
			elevatorStop();
		}
		else {
			setElevatorMotors(.5);
		}
	}

	public void elevatorStop(){
		setElevatorMotors(0);
		setSetpoint(getPosition());
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new TeleopElevator());
	}

	public void updatePID(){
		PIDController controller=getPIDController();
		if(controller.getP()!=sd.getElevatorP()||controller.getI()!=sd.getElevatorI()||controller.getD()!=sd.getElevatorD())
		{
			resetPIDController();
		}
	}

	protected double returnPIDInput() {
		return leftElevatorJag.getSpeed();
	}

	protected void usePIDOutput(double output) {
		setElevatorMotors(output);
	}

	public boolean isAtLowerLimit() {
		return lowerLimit.get();
	}

	public boolean isAtUpperLimit() {
		return upperLimit.get();
	}

	public void resetPIDController(){
		System.out.println("Resetting Elevator PID Controller");
		PIDController controller=getPIDController();
		position=0;
		controller.setPID(sd.getElevatorP(), sd.getElevatorI(), sd.getElevatorD());
		controller.reset();
		controller.enable();
	}

	public void increaseElevatorLevel() {
		position++;
		moveToLevel();
	}

	public void decreaseElevatorLevel() {
		position--;
		moveToLevel();
	}
	
	public void moveToLevel(){
		switch (position)
		{
		case -1:
			position=0;
			elevatorStop();
			break;
		case 0:
			setElevatorLevel(POSITION_ZERO);
			break;
		case 1:
			setElevatorLevel(POSITION_ONE);
			break;
		case 2:
			setElevatorLevel(POSITION_TWO);
			break;
		case 3:
			setElevatorLevel(POSITION_THREE);
			break;
		case 4:
			setElevatorLevel(POSITION_FOUR);
			break;
		case 5:
			position=4;
			elevatorStop();
			break;
		}		
	}
}
package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;
import org.usfirst.frc.team1747.robot.commands.TeleopElevator;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem {

	CANJaguar elevatorMotorOne;
	CANJaguar elevatorMotorTwo;
	CANJaguar elevatorMotorThree;
	SDController sd;

	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;
	private int preset = 0;
	private static final double POSITION_ZERO = 0;
	private static final double POSITION_ONE = -1;
	private static final double POSITION_TWO = -2;
	private static final double POSITION_THREE = -3;
	private static final double POSITION_FOUR = -4;
	private static final double ENCODER_CONVERSION = (10.0 * 1.806 * Math.PI / (1024.0 * 12.0))
			* (21.0 / 22.0);
	private Encoder encoder;

	// Everything above this needs to be cleaned up
	public Elevator() {
		super("Elevator",P,I,D);
		elevatorMotorOne = new CANJaguar(RobotMap.ELEVATOR_CIM_LEFT);
		//enablePID();
		// leftElevatorJag.setPositionMode(CANJaguar.kQuadEncoder, 1024, P, I,
		// D);
		// leftElevatorJag.enableControl();
		elevatorMotorTwo = new CANJaguar(RobotMap.ELEVATOR_CIM_MIDDLE);
		elevatorMotorThree = new CANJaguar(RobotMap.ELEVATOR_CIM_RIGHT);
		encoder = new Encoder(10, 11);
		sd = Robot.getSD();
		SmartDashboard.putNumber("Elevator P", P);
		SmartDashboard.putNumber("Elevator I", I);
		SmartDashboard.putNumber("Elevator D", D);
		preset = 0;
	}

	// Everything below this needs to be cleaned up
	public double getSpeed() {
		return encoder.getRate() * ENCODER_CONVERSION;
	}

	public void setElevatorMotors(double elevatorSpeed) {
		elevatorMotorOne.set(elevatorSpeed);
		elevatorMotorTwo.set(elevatorSpeed);
		elevatorMotorThree.set(elevatorSpeed);
	}

	public void manualElevatorDown() {
		setElevatorMotors(.55);
	}

	public void manualElevatorUp() {
		setElevatorMotors(-.75);
	}

	public void manualElevatorStop() {
		setElevatorMotors(0);
	}

	public void elevatorStop() {
		setLiftPosition(elevatorMotorOne.getPosition());
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TeleopElevator());
	}

	public boolean isAtLowerLimit() {
		return !elevatorMotorOne.getForwardLimitOK();
	}

	public boolean isAtUpperLimit() {
		return !elevatorMotorOne.getReverseLimitOK();
	}

	// more automatic
	public void increaseElevatorLevel() {
		preset++;
		moveToLevel();
	}

	public void decreaseElevatorLevel() {
		preset--;
		moveToLevel();
	}

	public void moveToLevel() {
		switch (preset) {
		case -1:
			preset = 0;
			elevatorStop();
			break;
		case 0:
			setLiftPosition(POSITION_ZERO);
			break;
		case 1:
			setLiftPosition(POSITION_ONE);
			break;
		case 2:
			setLiftPosition(POSITION_TWO);
			break;
		case 3:
			setLiftPosition(POSITION_THREE);
			break;
		case 4:
			setLiftPosition(POSITION_FOUR);
			break;
		case 5:
			preset = 4;
			elevatorStop();
			break;
		}
	}

	public double presetToPosition() {
		switch (preset) {
		case 0:
			return POSITION_ZERO;
		case 1:
			return POSITION_ONE;
		case 2:
			return POSITION_TWO;
		case 3:
			return POSITION_THREE;
		case 4:
			return POSITION_FOUR;
		default:
			System.out.println("I DON'T KNOW WHERE I AM!!!!!!");
			return 0;
		}
	}

	// Everything above this needs to be cleaned up
	
	public void setLiftPosition(double position) {
		if (0 > position && !isAtUpperLimit()) {
			elevatorMotorOne.set(position);
			elevatorMotorTwo.set(elevatorMotorOne.getOutputVoltage());
			elevatorMotorThree.set(elevatorMotorOne.getOutputVoltage());
		}
	}

	public void disablePID() {
		// leftElevatorJag.disableControl();
		elevatorMotorOne.setPercentMode();
	}

	public void enablePID() {
		preset = 0;
		elevatorMotorOne.setPositionMode(CANJaguar.kQuadEncoder, 1024,
				sd.getElevatorP(), sd.getElevatorI(), sd.getElevatorD());
		elevatorMotorOne.enableControl();
	}

	public void updateSlaveJags() {
		elevatorMotorTwo.set(elevatorMotorOne.getOutputVoltage() / 12.0);
		elevatorMotorThree.set(elevatorMotorOne.getOutputVoltage() / 12.0);
	}

	public boolean isDoneMoving() {
		// TODO Fix .1
		return Math.abs(presetToPosition() - elevatorMotorOne.getPosition()) < .1;
	}

	public void logToSmartDashboard() {
		SmartDashboard.putNumber("Elevator Speed", getSpeed());
		SmartDashboard.putNumber("Elevator Position",
				elevatorMotorOne.getPosition());
		SmartDashboard.putBoolean("Elevator Upper Limit", isAtUpperLimit());
		SmartDashboard.putBoolean("Elevator Lower Limit", isAtLowerLimit());
	}

	protected double returnPIDInput() {
		return getSpeed();
	}

	protected void usePIDOutput(double output) {
		setElevatorMotors(output);
		
	}
}
package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem {

	private CANJaguar elevatorMotorOne, elevatorMotorTwo, elevatorMotorThree;
	private SDController sd;

	private static final double P = 0.05;
	private static final double I = 0.002;
	private static final double D = 0.002;

	private double accumulator;
	private double time;
	private double pTime;
	private double distance;
	private double pDistance;

	private boolean changePID = false;
	private static double prevOutput;
	private static double motorChangeLim;

	private Encoder encoder;
	private static final double ENCODER_CONVERSION = (1.806 * Math.PI / 256.0 * (12.0 / 22.0));

	private int currentPosition;
	private static final double TOTE_HEIGHT = 12.75;
	private static final double OFFSET = 4.0;
	private static final double POSITION_ZERO = 0.0;
	private static final double POSITION_ONE = 0.5 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_TWO = 1.0 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_THREE = 1.5 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_FOUR = 2.0 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_FIVE = 2.5 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_SIX = 3.0 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_SEVEN = 3.5 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_EIGHT = 4.0 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_NINE = 4.5 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_TEN = 5.0 * TOTE_HEIGHT + OFFSET;
	private boolean isPIDEnabled = true;

	public Elevator() {
		super("Elevator", P, I, D);
		elevatorMotorOne = new CANJaguar(RobotMap.ELEVATOR_CIM_LEFT);
		elevatorMotorTwo = new CANJaguar(RobotMap.ELEVATOR_CIM_MIDDLE);
		elevatorMotorThree = new CANJaguar(RobotMap.ELEVATOR_CIM_RIGHT);
		getPIDController().setPercentTolerance(10.0);
		getPIDController().enable();
		encoder = new Encoder(10, 11, false);
		encoder.setDistancePerPulse(ENCODER_CONVERSION);
		time = 0;
		pTime = 0;
		distance = 0;
		pDistance = 0;
		sd = Robot.getSD();
		SmartDashboard.putNumber("Elevator P", P);
		SmartDashboard.putNumber("Elevator I", I);
		SmartDashboard.putNumber("Elevator D", D);
		currentPosition = 0;
		prevOutput = 0;
		motorChangeLim = 0.05;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}
	
	public void setCurrentPosition(int changePosition){
		currentPosition = currentPosition + changePosition;
	}
	
	public double getSpeed() {
		return encoder.getRate();
	}

	public double getDistance() {
		pDistance = distance;
		if (encoder == null)
			return 0;
		distance = encoder.getDistance();
		return ((distance - pDistance));
	}

	public void setElevatorMotors(double elevatorSpeed) {
		elevatorMotorOne.set(-elevatorSpeed * voltCorrection());
		elevatorMotorTwo.set(-elevatorSpeed * voltCorrection());
		elevatorMotorThree.set(-elevatorSpeed * voltCorrection());
	}

	private double voltCorrection() {
		return 14 / elevatorMotorOne.getBusVoltage();
	}

	public boolean isDoneMoving() {
		return getPIDController().onTarget();
	}

	public void manualElevatorDown() {
		setElevatorMotors(-.45);
	}

	public void manualElevatorUp() {
		setElevatorMotors(.45);
	}

	public void manualElevatorStop() {
		setElevatorMotors(0);
	}

	public void elevatorStop() {
		setLiftPosition(elevatorMotorOne.getPosition());
	}

	public void initDefaultCommand() {
		// setDefaultCommand(new TeleopElevator());
	}

	public boolean isAtLowerLimit() {
		return !elevatorMotorOne.getForwardLimitOK()
				&& !elevatorMotorTwo.getForwardLimitOK()
				&& !elevatorMotorThree.getForwardLimitOK();
	}

	public boolean isAtUpperLimit() {
		return !elevatorMotorOne.getReverseLimitOK()
				&& !elevatorMotorTwo.getReverseLimitOK()
				&& !elevatorMotorThree.getReverseLimitOK();
	}
	
	public void increaseElevatorLevel() {
		currentPosition = currentPosition + 2;
		System.out.println(currentPosition);
		System.err.println("Current Position Incremented");
		moveToLevel();
	}

	public void decreaseElevatorLevel() {
		currentPosition = currentPosition - 2;
		moveToLevel();
	}

	public void moveToLevel() {
		switch (currentPosition) {
		case -2:
		case -1:
			currentPosition = 0;
			moveToLevel();
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
			setLiftPosition(POSITION_FIVE);
			break;
		case 6:
			setLiftPosition(POSITION_SIX);
			break;
		case 7:
			setLiftPosition(POSITION_SEVEN);
			break;
		case 8:
			setLiftPosition(POSITION_EIGHT);
			break;
		case 9:
			setLiftPosition(POSITION_NINE);
			break;
		case 10:
			setLiftPosition(POSITION_TEN);
			break;
		case 11:
		case 12:
			currentPosition = 10;
			moveToLevel();
			break;
		default:
			System.out.println("Help! I Don't know where I am!!!");
			break;
		}
	}

	public double presetToPosition() {
		switch (currentPosition) {
		case -2:
		case -1:
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
		case 5:
			return POSITION_FIVE;
		case 6:
			return POSITION_SIX;
		case 7:
			return POSITION_SEVEN;
		case 8:
			return POSITION_EIGHT;
		case 9:
			return POSITION_NINE;
		case 10:
		case 11:
		case 12:
			return POSITION_TEN;
		default:
			System.out.println("I DON'T KNOW WHERE I AM!!!!!!");
			return getPosition();
		}
	}

	public void setLiftPosition(double position) {
		setSetpoint(position);
		SmartDashboard.putNumber("", getCurrentPosition());
	}

	public void logToSmartDashboard() {
		SmartDashboard.putNumber("Elevator Speed", getSpeed());
		SmartDashboard.putNumber("Elevator Position", getNetDistance());
		SmartDashboard.putNumber("Sudo Elevator Position", currentPosition);
		SmartDashboard.putBoolean("Elevator Upper Limit", isAtUpperLimit());
		SmartDashboard.putBoolean("Elevator Lower Limit", isAtLowerLimit());
		SmartDashboard.putBoolean("Is Done Moving", isDoneMoving());
		SmartDashboard.putNumber("Elevator Distance", getNetDistance());
		SmartDashboard.putBoolean("Encoder Direction", encoder.getDirection());
		if (isAtLowerLimit() && !changePID) {
			changePID = true;
			resetAccumulator();
			getPIDController().disable();
			getPIDController().setPID(sd.getElevatorP(), sd.getElevatorI(),
					sd.getElevatorD());
			getPIDController().enable();
		} else {
			changePID = false;
		}
	}

	protected double returnPIDInput() {
		if (encoder == null)
			return getPIDController().getSetpoint();
		return getNetDistance();
	}

	public void disable() {
		isPIDEnabled = false;
		super.disable();
	}

	public void enable() {
		isPIDEnabled = true;
		super.enable();
	}

	protected void usePIDOutput(double output) {
		if (isPIDEnabled)
			//if((output - prevOutput) > motorChangeLim){
				//setElevatorMotors(prevOutput + motorChangeLim);
				//prevOutput = prevOutput+motorChangeLim;
			//} else if((output-prevOutput) < motorChangeLim){
				//setElevatorMotors(prevOutput - motorChangeLim);
				//prevOutput = prevOutput-motorChangeLim;
			//} else {
				setElevatorMotors(output);
				//prevOutput = output;
			//}
		SmartDashboard.putNumber("Elevator PID Output", output);
	}

	public void resetAccumulator() {
		accumulator = 0;
	}

	private double getNetDistance() {
		pTime = time;
		time = Timer.getFPGATimestamp();
		accumulator += (getSpeed() * (time - pTime));
		return accumulator;
	}

	public void resetPosition() {
		currentPosition = 0;
	}

	public double getOffset() {
		return 0.5;
	}

	public void bumpUp() {
		currentPosition++;
		moveToLevel();
	}

	public void bumpDown() {
		currentPosition--;
		moveToLevel();
	}
	
	public void setCurrentPos(int newPos){
		currentPosition = newPos;
	}
}
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
	private double bump = 0;

	private Encoder encoder;
	private static final double ENCODER_CONVERSION = (1.806 * Math.PI / 256.0 * (21.0 / 22.0));

	private int currentPosition = 0;
	private static final double TOTE_HEIGHT = 12.75;
	private static final double OFFSET = 4;
	private static final double POSITION_ZERO = 0;
	private static final double POSITION_ONE = 1 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_TWO = 2 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_THREE = 3 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_FOUR = 4 * TOTE_HEIGHT + OFFSET;
	private static final double POSITION_FIVE = 5 * TOTE_HEIGHT + OFFSET;
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
	}

	public double getSpeed() {
		if (encoder == null)
			return 0;
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
		setElevatorMotors(-.2);
	}

	public void manualElevatorUp() {
		setElevatorMotors(.2);
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
		currentPosition++;
		System.out.println(currentPosition);
		System.err.println("Current Position Incremented");
		moveToLevel();
	}

	public void decreaseElevatorLevel() {
		currentPosition--;
		moveToLevel();
	}

	public void moveToLevel() {
		switch (currentPosition) {
		case -1:
			currentPosition = 0;
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
			setLiftPosition(POSITION_FIVE);
			break;
		case 6:
			currentPosition = 5;
			break;
		}
	}

	public double presetToPosition() {
		switch (currentPosition) {
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
		default:
			System.out.println("I DON'T KNOW WHERE I AM!!!!!!");
			return getPosition();
		}
	}

	public void setLiftPosition(double position) {
		setSetpoint(position + bump);
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
		SmartDashboard.putNumber("Bump", bump);
		if (isAtLowerLimit() && !changePID) {
			changePID = true;
			resetAccumulator();
			resetBump();
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
			setElevatorMotors(output);
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
		moveToLevel();
	}

	public double getOffset() {
		return 0.5;
	}

	public void resetBump() {
		bump = 0;
	}

	public void bumpUp() {
		bump += 8;
		moveToLevel();
	}

	public void bumpDown() {
		//Prevents negative setpoints
		if ((getPosition() - 8) > 0) {
			bump -= 8;
			moveToLevel();
		}
	}
}
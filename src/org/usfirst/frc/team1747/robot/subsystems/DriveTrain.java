package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;
import org.usfirst.frc.team1747.robot.commands.TeleopDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	CANTalon leftCim, leftMiniCim;
	CANTalon rightCim, rightMiniCim;

	double turnMultiplier;
	double driveMultiplier;
	
	// Uses the 256 count to ft/s
	private final static double ENCODER_CONVERSION_CONSTANT = (4.0 * Math.PI)
			/ (12.0 * 256.0/* counts */);

	public DriveTrain() {
		leftCim = new CANTalon(RobotMap.LEFT_CIM_ID);
		leftMiniCim = new CANTalon(RobotMap.LEFT_MINI_CIM_ID);
		rightMiniCim = new CANTalon(RobotMap.RIGHT_MINI_CIM_ID);
		rightCim = new CANTalon(RobotMap.RIGHT_CIM_ID);
		SmartDashboard.putNumber("Turn Multiplier", 0.5);
		SmartDashboard.putNumber("Drive Multiplier", 0.8);
		turnMultiplier = 0.5;
		driveMultiplier = 0.8;
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		leftCim.set(leftSpeed * leftVoltSmoothing());
		leftMiniCim.set(leftSpeed * leftVoltSmoothing());
		rightCim.set(-rightSpeed * rightVoltSmoothing());
		rightMiniCim.set(-rightSpeed * rightVoltSmoothing());
	}

	public void arcadeDrive(double forward, double turn) {
		double leftCurrent, rightCurrent;
		leftCurrent = (forward + (turn * turnMultiplier)) * driveMultiplier;
		rightCurrent = (forward - (turn * turnMultiplier)) * driveMultiplier;
		tankDrive(leftCurrent, rightCurrent);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}

	public double getLeftSpeed() {
		return leftMiniCim.getSpeed() * ENCODER_CONVERSION_CONSTANT;
	}

	public double getRightSpeed() {
		return -rightMiniCim.getSpeed() * ENCODER_CONVERSION_CONSTANT;
	}

	public double getLeftPosition() {
		return leftMiniCim.getEncPosition();
	}

	public double getRightPosition() {
		return -rightMiniCim.getEncPosition();
	}

	public void logToSmartDashboard() {
		SmartDashboard.putNumber("DriveTrain Left Speed", getLeftSpeed());
		SmartDashboard.putNumber("DriveTrain Right Speed", getRightSpeed());
		turnMultiplier = SmartDashboard.getNumber("Turn Multiplier");
		driveMultiplier = SmartDashboard.getNumber("Drive Multiplier");
	}

	public double leftVoltSmoothing() {
		return 28.0/(leftCim.getBusVoltage()+leftMiniCim.getBusVoltage());
	}
	
	public double rightVoltSmoothing(){
		return 28.0/(rightCim.getBusVoltage()+rightMiniCim.getBusVoltage());
	}
}
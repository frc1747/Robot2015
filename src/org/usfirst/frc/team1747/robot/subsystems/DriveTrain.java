package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.GyroITG3200;
import org.usfirst.frc.team1747.robot.PID;
import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;
import org.usfirst.frc.team1747.robot.commands.TeleopDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	CANTalon leftCim, leftMiniCim;
	CANTalon rightCim, rightMiniCim;
	CANTalon centerCim, centerMiniCim;
	// DriveSmoother driveSmoother;
	PID leftPID;
	PID rightPID;
	double lTime;
	double lPTime;
	double rTime;
	double rPTime;
	double rightPosition;
	double leftPosition;
	double[] leftData;
	double[] rightData;
	double[] pidData;
	GyroITG3200 gyro;

	// Uses the 256 count to ft/s
	private final static double ENCODER_CONVERSION_CONSTANT = (10 * Math.PI)
			/ (12 * 256/* counts */);

	public DriveTrain() {
		leftCim = new CANTalon(RobotMap.LEFT_CIM_ID);
		leftMiniCim = new CANTalon(RobotMap.LEFT_MINI_CIM_ID);
		centerCim = new CANTalon(RobotMap.CENTER_CIM_ID);
		centerMiniCim = new CANTalon(RobotMap.CENTER_MINI_CIM_ID);
		rightMiniCim = new CANTalon(RobotMap.RIGHT_MINI_CIM_ID);
		rightCim = new CANTalon(RobotMap.RIGHT_CIM_ID);
		leftPID = new PID(0, 0, 0);
		rightPID = new PID(0, 0, 0);
		leftData = new double[2];
		rightData = new double[2];
		pidData = new double[2];
		gyro = new GyroITG3200(I2C.Port.kMXP);
		gyro.initialize();
		// driveSmoother=new DriveSmoother();
	}

	public void setLeftMiddleRightMotor(double leftSpeed, double middleSpeed,
			double rightSpeed) {
		leftCim.set(leftSpeed);
		leftMiniCim.set(leftSpeed);
		centerCim.set(middleSpeed);
		centerMiniCim.set(middleSpeed);
		rightCim.set(-rightSpeed);
		rightMiniCim.set(-rightSpeed);
	}

	public void hDrive(double xAxis, double yAxis, double rotate) {
		double leftCurrent, centerCurrent, rightCurrent;
		centerCurrent = xAxis;
		leftCurrent = yAxis + rotate;
		rightCurrent = yAxis - rotate;
		/*
		 * centerCurrent = xAxis; double
		 * gaussianInput=angleToGaussianInput(Math.atan2(yAxis, xAxis)); double
		 * scaledGaussianOutput=yAxis*gaussianConversion(gaussianInput)
		 * leftCurrent =scaledGaussianOutput+rotate;
		 * rightCurrent=scaledGaussianOutput-rotate;
		 */
		SmartDashboard.putNumber("Left Speed", getLeftSpeed());
		SmartDashboard.putNumber("Middle Speed", getMiddleSpeed());
		SmartDashboard.putNumber("Right Speed", getRightSpeed());
		setLeftMiddleRightMotor(leftCurrent, centerCurrent, rightCurrent);
	}

	private double gaussianConversion(double gaussianInput) {
		return Math.exp(Math.pow(gaussianInput, 2) / (-.18));
	}

	private double angleToGaussianInput(double rad) {
		if (rad > Math.PI)
			rad -= Math.PI;
		return (rad - Math.PI / 2.0) / (Math.PI / 2.0);
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

	public double getMiddleSpeed() {
		return centerMiniCim.getSpeed() * ENCODER_CONVERSION_CONSTANT;
	}

	static class DriveSmoother {

		static final double[] SIGMOIDSTRETCH = { 0.0, 0.03, 0.06, 0.09, 0.1,
				0.11, 0.12, 0.11, 0.1, 0.09, 0.06, 0.03, 0.0 };
		double[] leftTargetDeltas = new double[SIGMOIDSTRETCH.length];
		double[] rightTargetDeltas = new double[SIGMOIDSTRETCH.length];
		double[] centerTargetDeltas = new double[SIGMOIDSTRETCH.length];
		double pLeftCurrent, pRightCurrent, pCenterCurrent, prevLeftTarget,
				prevRightTarget, prevCenterTarget;
		SDController sd;

		public DriveSmoother() {
			for (int j = 0; j < SIGMOIDSTRETCH.length; j++) {
				leftTargetDeltas[j] = 0.0;
				rightTargetDeltas[j] = 0.0;
				centerTargetDeltas[j] = 0.0;
			}
			prevLeftTarget = 0.0;
			prevRightTarget = 0.0;
			prevCenterTarget = 0.0;
			pLeftCurrent = 0.0;
			pRightCurrent = 0.0;
			pCenterCurrent = 0.0;
			this.sd = Robot.getSD();
		}

		public double[] calculateSmoothenedValues(double targetLeftCurrent,
				double targetCenterCurrent, double targetRightCurrent) {

			for (int i = leftTargetDeltas.length - 1; i > 0; i--) {
				leftTargetDeltas[i] = leftTargetDeltas[i - 1];
				rightTargetDeltas[i] = rightTargetDeltas[i - 1];
				centerTargetDeltas[i] = centerTargetDeltas[i - 1];
			}
			leftTargetDeltas[0] = targetLeftCurrent - prevLeftTarget;
			rightTargetDeltas[0] = targetRightCurrent - prevRightTarget;
			centerTargetDeltas[0] = targetCenterCurrent - prevCenterTarget;
			prevLeftTarget = targetLeftCurrent;
			prevRightTarget = targetRightCurrent;
			prevCenterTarget = targetCenterCurrent;
			for (int i = 0; i < SIGMOIDSTRETCH.length; i++) {
				pLeftCurrent += leftTargetDeltas[i] * SIGMOIDSTRETCH[i];
				pRightCurrent += rightTargetDeltas[i] * SIGMOIDSTRETCH[i];
				pCenterCurrent += centerTargetDeltas[i] * SIGMOIDSTRETCH[i];
			}
			double damper = sd.getDampeningConstant();
			return new double[] { pLeftCurrent * damper,
					pCenterCurrent * damper, pRightCurrent * damper };
			// return new double[]{pLeftCurrent, pCenterCurrent, pRightCurrent};
		}
	}

	public void logToSmartDashboard() {
		SmartDashboard.putNumber("DriveTrain Left Speed", getLeftSpeed());
		SmartDashboard.putNumber("DriveTrain Center Speed", getMiddleSpeed());
		SmartDashboard.putNumber("DriveTrain Right Speed", getRightSpeed());
		SmartDashboard.putNumber("gyro", gyro.getRotationZ());
	}
}
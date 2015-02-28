package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;
import org.usfirst.frc.team1747.robot.commands.TeleopDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	CANTalon leftCim, leftMiniCim;
	CANTalon rightCim, rightMiniCim;
	CANTalon centerCim, centerMiniCim;

	double turnDamper;
	double driveDamper;

	// DriveSmoother driveSmoother;
	Gyro gyro;

	// Uses the 256 count to ft/s
	private final static double ENCODER_CONVERSION_CONSTANT = (4.0 * Math.PI)
			/ (12.0 * 256.0/* counts */);

	public DriveTrain() {
		leftCim = new CANTalon(RobotMap.LEFT_CIM_ID);
		leftMiniCim = new CANTalon(RobotMap.LEFT_MINI_CIM_ID);
		centerCim = new CANTalon(RobotMap.CENTER_CIM_ID);
		centerMiniCim = new CANTalon(RobotMap.CENTER_MINI_CIM_ID);
		rightMiniCim = new CANTalon(RobotMap.RIGHT_MINI_CIM_ID);
		rightCim = new CANTalon(RobotMap.RIGHT_CIM_ID);
		SmartDashboard.putNumber("Turn Damper", 0.5);
		turnDamper = SmartDashboard.getNumber("Turn Damper");
		SmartDashboard.putNumber("Drive Damper", 1);
		driveDamper = SmartDashboard.getNumber("Drive Damper");
		gyro = new Gyro(0);
		gyro.initGyro();
		// driveSmoother=new DriveSmoother();
	}

	public void setLeftMiddleRightMotor(double leftSpeed, double middleSpeed,
			double rightSpeed) {
		leftCim.set(leftSpeed * leftVoltSmoothing());
		leftMiniCim.set(leftSpeed * leftVoltSmoothing());
		centerCim.set(middleSpeed * centerVoltSmoothing());
		centerMiniCim.set(middleSpeed * centerVoltSmoothing());
		rightCim.set(-rightSpeed * rightVoltSmoothing());
		rightMiniCim.set(-rightSpeed * rightVoltSmoothing());
	}

	public void hDrive(double xAxis, double yAxis, double rotate) {
		double leftCurrent, centerCurrent, rightCurrent;
		centerCurrent = xAxis * driveDamper;
		leftCurrent = (yAxis + (rotate * turnDamper) * driveDamper);
		rightCurrent = (yAxis - (rotate * turnDamper) * driveDamper);
		setLeftMiddleRightMotor(leftCurrent, centerCurrent, rightCurrent);
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

	public double getLeftPosition() {
		return leftMiniCim.getEncPosition();
	}

	public double getMiddlePosition() {
		return centerMiniCim.getEncPosition();
	}

	public double getRightPosition() {
		return -rightMiniCim.getEncPosition();
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
		SmartDashboard.putNumber("DriveTrain Gyro Angle", getAngle());
		SmartDashboard.putNumber("DriveTrain Gyro Rate", getRate());
		turnDamper = SmartDashboard.getNumber("Turn Damper");
		driveDamper = SmartDashboard.getNumber("Drive Damper");
	}

	public void resetAngle() {
		gyro.reset();
	}

	public double getAngle() {
		return gyro.getAngle();
	}

	public double getRate() {
		return gyro.getRate();
	}

	public double leftVoltSmoothing() {
		return 28.0/(leftCim.getBusVoltage()+leftMiniCim.getBusVoltage());
	}
	
	public double centerVoltSmoothing(){
		return 28.0/(centerCim.getBusVoltage()+centerMiniCim.getBusVoltage());
	}
	
	public double rightVoltSmoothing(){
		return 28.0/(rightCim.getBusVoltage()+rightMiniCim.getBusVoltage());
	}
	
	public void setSpeedDampener(double speedDampener){
		SmartDashboard.putNumber("Drive Damper", speedDampener);
		driveDamper = speedDampener;
	}
}
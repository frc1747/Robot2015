package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.commands.TeleopDrive;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	CANTalon leftCim, leftMiniCim;
	CANTalon rightCim, rightMiniCim;
	CANTalon centerCim, centerMiniCim;
	private static final double d45 = Math.PI/4;
	private static final double d90 = Math.PI/2;
	private static final double d135 = 3*Math.PI/4;
	private static final double d180 = Math.PI;
	private static final double d63 = Math.toRadians(63);
	private static final double d117 = Math.toRadians(117);;

	public DriveTrain () {
		leftCim = new CANTalon(RobotMap.LEFT_CIM_ID);
		leftMiniCim = new CANTalon(RobotMap.LEFT_MINI_CIM_ID);
		centerCim = new CANTalon(RobotMap.CENTER_CIM_ID);
		centerMiniCim = new CANTalon(RobotMap.CENTER_MINI_CIM_ID);
		rightMiniCim = new CANTalon(RobotMap.RIGHT_MINI_CIM_ID);
		rightCim = new CANTalon(RobotMap.RIGHT_CIM_ID);
	}

	public void setLeftMiddleRightMotor(double leftSpeed, double middleSpeed, double rightSpeed){
		leftCim.set(leftSpeed);
		leftMiniCim.set(leftSpeed);
		centerCim.set(middleSpeed);
		centerMiniCim.set(middleSpeed);
		rightCim.set(-rightSpeed);
		rightMiniCim.set(-rightSpeed);
	}

	public void hDrive(double xAxis, double yAxis, double rotate, double angle){
		double leftCurrent, centerCurrent, rightCurrent;
		centerCurrent = xAxis;
		leftCurrent = yAxis + rotate;
		rightCurrent = yAxis - rotate;
		/*if((0 <= angle && angle <= d45)||(d135 <= angle && angle <= d180)){
    		leftCurrent=0.5*yAxis;
    		rightCurrent=leftCurrent;
    		leftCurrent -= rotate;
        	rightCurrent += rotate;
    		setLeftMiddleRightMotor(leftCurrent ,centerCurrent ,rightCurrent);
    	} else if((d45 < angle && angle <= d63)||(d117 <= angle && angle < d135)){
    		leftCurrent = 0.5*Math.tan(angle)*yAxis;
    		rightCurrent = leftCurrent;
    		leftCurrent -= rotate;
        	rightCurrent += rotate;
    		setLeftMiddleRightMotor(leftCurrent ,centerCurrent ,rightCurrent);
    	} else if(d63 < angle && angle < 117){
    		leftCurrent = yAxis;
    		rightCurrent = leftCurrent;
    		leftCurrent -= rotate;
        	rightCurrent += rotate;
    		setLeftMiddleRightMotor(leftCurrent ,centerCurrent ,rightCurrent);
    	} else if(d180 < angle){
    		hDrive(-xAxis, -yAxis, -rotate, (angle-d180));
    	}*/
		/*centerCurrent = xAxis;
    	 double gaussianInput=angleToGaussianInput(Math.atan2(yAxis, xAxis));
    	 leftCurrent =gaussianConversion(gaussianInput)+rotate;
    	 rightCurrent=leftCurrent-rotate;*/
		setLeftMiddleRightMotor(leftCurrent ,centerCurrent ,rightCurrent);
	}

	private double gaussianConversion(double gaussianInput) {
		return Math.exp(Math.pow(gaussianInput, 2)/(-.18));
	}

	private double angleToGaussianInput(double rad) {
		if(rad>Math.PI)
			rad-=Math.PI;
		return (rad-Math.PI/2.0)/(Math.PI/2.0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}
}
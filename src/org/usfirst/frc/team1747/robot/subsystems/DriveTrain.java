package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	CANJaguar leftCim, leftMiniCim;
    CANJaguar rightCim, rightMiniCim;
    CANJaguar centerCim, centerMiniCim;
 
    public DriveTrain () {
    	leftCim = new CANJaguar (RobotMap.LEFT_CIM_ID);
    	leftMiniCim = new CANJaguar (RobotMap.LEFT_MINI_CIM_ID);
    	centerCim = new CANJaguar (RobotMap.CENTER_CIM_ID);
    	centerMiniCim = new CANJaguar (RobotMap.CENTER_MINI_CIM_ID);
    	rightMiniCim = new CANJaguar (RobotMap.RIGHT_MINI_CIM_ID);
    	rightCim = new CANJaguar (RobotMap.RIGHT_CIM_ID);
    }
    
    public void setLeftMiddleRightMotor(double leftSpeed, double middleSpeed, double rightSpeed){
    	leftCim.set(leftSpeed);
    	leftMiniCim.set(leftSpeed);
    	centerCim.set(middleSpeed);
    	centerMiniCim.set(middleSpeed);
    	rightCim.set(rightSpeed);
    	rightMiniCim.set(rightSpeed);
    }

    public void hDrive(double xAxis, double yAxis, double rotate){
    	double centerCurrent = xAxis;
    	//TODO Adjust y currents for extra force that y doesn't have
    	// it will (hopefully) work by dividing the angle by the nearest
    	// 90 degree angle w.r.t to the x-axis
    	double leftCurrent = yAxis - rotate;
    	double rightCurrent = yAxis + rotate;
    	setLeftMiddleRightMotor(leftCurrent ,centerCurrent ,rightCurrent);
    }
    
    public void initDefaultCommand() {
    }
}
package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.commands.TeleopDrive;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

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

    public void hDrive(double xAxis, double yAxis, double rotate, double angle){
    	double centerCurrent = xAxis;
    	double leftCurrent = yAxis - rotate;
    	double rightCurrent = yAxis + rotate;
    	/* double leftCurrent, rightCurrent;
    	double centerCurrent = xAxis;
    	if((angle>0 && angle<=(Math.PI/4))||((angle>=(3*Math.PI/4))&&(angle<Math.PI))){
    		leftCurrent=0.5*yAxis;
    		rightCurrent=leftCurrent;
    	} else if((angle>(Math.PI/4) && angle<=(Math.PI/2 - 0.729727655))||((angle>=(Math.PI/2 + 0.729727655))&&(angle<(3*Math.PI/4)))){
    		leftCurrent = 0.5*Math.tan(angle)*yAxis;
    		rightCurrent = leftCurrent;
    	} else if((angle>(Math.PI/2 - 0.729727655) && angle<(Math.PI/2 + 0.729727655))){
    		leftCurrent = yAxis;
    		rightCurrent = leftCurrent;
    	} else if(angle>Math.PI){
    		-hDrive(xAxis, yAxis, rotate, angle-Math.PI);
    	}
    	leftCurrent -= rotate;
    	rightCurrent += rotate; */
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
package org.usfirst.frc.team1747.robot.subsystems;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.RobotMap;
import org.usfirst.frc.team1747.robot.SDController;
import org.usfirst.frc.team1747.robot.commands.TeleopDrive;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain.DriveSmoother;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	CANTalon leftCim, leftMiniCim;
	CANTalon rightCim, rightMiniCim;
	CANTalon centerCim, centerMiniCim;
	DriveSmoother driveSmoother;
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
		double[] calculatedValues=driveSmoother.calculateSmoothenedValues(leftSpeed,middleSpeed, rightSpeed);
		leftCim.set(calculatedValues[0]);
		leftMiniCim.set(calculatedValues[0]);
		centerCim.set(calculatedValues[1]);
		centerMiniCim.set(calculatedValues[1]);
		rightCim.set(-calculatedValues[2]);
		rightMiniCim.set(-calculatedValues[2]);
	}

	public void hDrive(double xAxis, double yAxis, double rotate){
		double leftCurrent, centerCurrent, rightCurrent;
		centerCurrent = xAxis;
		leftCurrent = yAxis + rotate;
		rightCurrent = yAxis - rotate;
		/*double angle =Math.atan(yAxis/xAxis);
		  if((0 <= angle && angle <= d45)||(d135 <= angle && angle <= d180)){
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
    	 double scaledGaussianOutput=yAxis*gaussianConversion(gaussianInput)
    	 leftCurrent =scaledGaussianOutput+rotate;
    	 rightCurrent=scaledGaussianOutput-rotate;*/
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
	
	static class DriveSmoother{

		static final double[] SIGMOIDSTRETCH = {0.0, 0.03, 0.06, 0.09, 0.1, 0.11, 0.12, 
			0.11, 0.1, 0.09, 0.06, 0.03, 0.0};
		double[] leftTargetDeltas = new double[SIGMOIDSTRETCH.length];
		double[] rightTargetDeltas = new double[SIGMOIDSTRETCH.length];
		double[] centerTargetDeltas = new double[SIGMOIDSTRETCH.length];
		double pLeftCurrent, pRightCurrent, pCenterCurrent, prevLeftTarget, prevRightTarget, prevCenterTarget;
		SDController sd;

		public DriveSmoother(){
			for(int j = 0; j < SIGMOIDSTRETCH.length; j++){
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
			this.sd=Robot.getSD();
		}

		public double[] calculateSmoothenedValues(double targetLeftCurrent, double targetCenterCurrent, double targetRightCurrent){

			for(int i = leftTargetDeltas.length-1; i > 0; i--){
				leftTargetDeltas[i] = leftTargetDeltas[i-1];
				rightTargetDeltas[i] = rightTargetDeltas[i-1];
				centerTargetDeltas[i] = centerTargetDeltas[i-1];
			}
			leftTargetDeltas[0] = targetLeftCurrent - prevLeftTarget;
			rightTargetDeltas[0] = targetRightCurrent - prevRightTarget;
			centerTargetDeltas[0] = targetCenterCurrent - prevCenterTarget;
			prevLeftTarget = targetLeftCurrent;
			prevRightTarget = targetRightCurrent;      
			for(int i = 0; i< SIGMOIDSTRETCH.length; i++){
				pLeftCurrent += leftTargetDeltas[i]*SIGMOIDSTRETCH[i];
				pRightCurrent += rightTargetDeltas[i]*SIGMOIDSTRETCH[i];
				pCenterCurrent += centerTargetDeltas[i]*SIGMOIDSTRETCH[i];
			}
			double damper=sd.getDampeningConstant();
			return new double[]{pLeftCurrent * damper, pCenterCurrent * damper, pRightCurrent * damper};
			//return new double[]{pLeftCurrent, pCenterCurrent, pRightCurrent};
		}
	}
}
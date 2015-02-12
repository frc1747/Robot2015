package org.usfirst.frc.team1747.robot;

import edu.wpi.first.wpilibj.Timer;

public class PID {

	double kP;
	double kI;
	double kD;
	double error;
	double currentpoint;
	double rate;
	double setpoint;
	double accumulator;
	double time;
	double pTime;
	
	public PID(double kP, double kI, double kD){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		time = Timer.getFPGATimestamp();
		pTime = 0;
	}
	
	public double run(double currentpoint, double rate){
		pTime = time;
		time = Timer.getFPGATimestamp();
		this.currentpoint = currentpoint;
		this.rate = rate;
		error = setpoint - currentpoint;
		accumulator += (error*(time-pTime));
		return (error*kP + accumulator*kI + rate*kD);
	}
	
	public void updateSetpoint(double setpoint){
		this.setpoint = setpoint;
	}
	
	public void resetAccumulation(){
		setAccumulation(0);
	}
	
	public void setAccumulation(double value){
		accumulator = value;
	}
}

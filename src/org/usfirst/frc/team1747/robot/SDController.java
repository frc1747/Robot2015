package org.usfirst.frc.team1747.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SDController {

	private Cyborg cyborg;

	public SDController(){
		cyborg=Robot.getOI().getCyborg();
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putNumber("Dampening Constant", .6);
		SmartDashboard.putNumber("Elevator P", 0);
		SmartDashboard.putNumber("Elevator I", 0);
		SmartDashboard.putNumber("Elevator D", 0);
	}

	public double getDampeningConstant(){
		return SmartDashboard.getNumber("Dampening Constant");
	}

	public double getElevatorP(){
		return SmartDashboard.getNumber("Elevator P");
	}

	public double getElevatorI(){
		return SmartDashboard.getNumber("Elevator I");
	}

	public double getElevatorD(){
		return SmartDashboard.getNumber("Elevator D");
	}

	public void refresh(){
		cyborg.logToSmartDashboard();
	}
}
package org.usfirst.frc.team1747.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SDController {
	
	private Cyborg cyborg;
	
	public SDController(){
		cyborg=Robot.getOI().getCyborg();
		 SmartDashboard.putData(Scheduler.getInstance());
		 SmartDashboard.putNumber("Dampening Constant", .6);
	}
	
	public double getDampeningConstant(){
		return SmartDashboard.getNumber("Dampening Constant");
	}
	
	public void refresh(){
		cyborg.logToSmartDashboard();
	}
}

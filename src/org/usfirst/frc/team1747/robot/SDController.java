package org.usfirst.frc.team1747.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SDController {
	
	private CyborgController cyborg;
	
	public SDController(){
		cyborg=Robot.getOI().getCyborg();
		 SmartDashboard.putData(Scheduler.getInstance());
	}
	
	public void refresh(){
		cyborg.logToSmartDashboard();
	}
}

package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

	private static OI oi;
	private static SDController sd;
	private static DriveTrain drive;
	
    public void robotInit() {
		oi = new OI();
		sd = new SDController();
		drive = new DriveTrain();
		sd.refresh();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sd.refresh();
	}

    public void autonomousInit() {
    	sd.refresh();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        sd.refresh();
    }

    public void teleopInit() {
    	sd.refresh();
    }

    public void disabledInit(){
    	sd.refresh();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        sd.refresh();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
        sd.refresh();
    }
    
    public static OI getOI(){
    	return oi;
    }
    
    public static SDController getSD(){
    	return sd;
    }
    
    public static DriveTrain getDrive(){
    	return drive;
    }
}

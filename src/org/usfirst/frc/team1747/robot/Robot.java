
package org.usfirst.frc.team1747.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class Robot extends IterativeRobot {

	private static OI oi;

    public void robotInit() {
		oi = new OI();
    }
	
	public void disabledPeriodic() {
		oi.getCyborg().logToSmartDashboard();
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {

    }


    public void autonomousPeriodic() {
    	oi.getCyborg().logToSmartDashboard();
        Scheduler.getInstance().run();
    }

    public void teleopInit() {

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	oi.getCyborg().logToSmartDashboard();
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	oi.getCyborg().logToSmartDashboard();
        LiveWindow.run();
    }
    
    public static OI getOI(){
    	return oi;
    }
}

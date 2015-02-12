package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.commands.AutonCommands;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

	private static OI oi;
	private static SDController sd;
	private static DriveTrain drive;
	private static Elevator elevator;
	private AutonCommands autonCommands;
	private static double time;
	private static double pTime;
	private static double dTime;

	public void robotInit() {
		oi = new OI();
		sd = new SDController();
		drive = new DriveTrain();
		elevator = new Elevator();
		autonCommands = new AutonCommands();
		// oi.init();
		sd.init();
		sd.refresh();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sd.refresh();
	}

	public void autonomousInit() {
		autonCommands.start();
		sd.refresh();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		sd.refresh();
	}

	public void teleopInit() {
		sd.refresh();
	}

	public void disabledInit() {
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

	public static OI getOI() {
		return oi;
	}

	public static SDController getSD() {
		return sd;
	}

	public static DriveTrain getDrive() {
		return drive;
	}

	public static Elevator getElevator() {
		return elevator;
	}
	

}
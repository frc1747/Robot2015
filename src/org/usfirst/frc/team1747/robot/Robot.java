package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.commands.AdvancedAuto;
import org.usfirst.frc.team1747.robot.commands.SimpleAuto;
import org.usfirst.frc.team1747.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1747.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private static OI oi;
	private static SDController sd;
	private static DriveTrain drive;
	private static Elevator elevator;
	private CommandGroup autoCommand;
	private SendableChooser autoChooser;

	public void robotInit() {
		oi = new OI();
		sd = new SDController();
		drive = new DriveTrain();
		elevator = new Elevator();
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Simple Auto", true);
		autoChooser.addObject("Advanced Auto", false);
		SmartDashboard.putData("Autonomous Mode", autoChooser);
		oi.init();
		sd.init();
		sd.refresh();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sd.refresh();
	}

	public void autonomousInit() {
		elevator.resetAccumulator();
		elevator.resetPosition();
		if ((boolean) autoChooser.getSelected())
			autoCommand = new SimpleAuto();
		else
			autoCommand = new AdvancedAuto();
		autoCommand.start();
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
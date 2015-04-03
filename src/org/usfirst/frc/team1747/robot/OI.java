package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.commands.BumpDown;
import org.usfirst.frc.team1747.robot.commands.BumpUp;
import org.usfirst.frc.team1747.robot.commands.CalibrateElevator;
import org.usfirst.frc.team1747.robot.commands.DecreaseElevatorLevel;
import org.usfirst.frc.team1747.robot.commands.FeederCommand;
import org.usfirst.frc.team1747.robot.commands.GetInfo;
import org.usfirst.frc.team1747.robot.commands.IncreaseElevatorLevel;
import org.usfirst.frc.team1747.robot.commands.ManualElevatorDown;
import org.usfirst.frc.team1747.robot.commands.ManualElevatorUp;
import org.usfirst.frc.team1747.robot.commands.Setpoint1Preset;
import org.usfirst.frc.team1747.robot.commands.Setpoint2Preset;
import org.usfirst.frc.team1747.robot.commands.StopElevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

	private SendableChooser cyborgChooser;
	private Cyborg drCyborg;
	private Cyborg opCyborg;

	public OI() {
		cyborgChooser = new SendableChooser();
		cyborgChooser.addDefault("Normal Cyborg", true);
		cyborgChooser.addObject("Precision Cyborg", false);
		SmartDashboard.putData("Driver Cyborg Version", cyborgChooser);
		drCyborg = new CyborgController(0);
		opCyborg = new PrecisionCyborgController(1);
	}

	public void init() {
		opCyborg.getLeftTrigger().whenPressed(new DecreaseElevatorLevel(0));
		opCyborg.getRightTrigger().whenPressed(new IncreaseElevatorLevel(0));
		opCyborg.getButtonTwo().whenPressed(new BumpDown());
		opCyborg.getButtonThree().whenPressed(new BumpUp());
		opCyborg.getBackButton().whenPressed(new CalibrateElevator());
		opCyborg.getButtonOne().whenPressed(new StopElevator());
		//opCyborg.getLeftTrigger().whenPressed(new ManualElevatorDown());
		//opCyborg.getRightBumper().whenPressed(new ManualElevatorUp());
		opCyborg.getButtonFour().whenPressed(new FeederCommand());
		opCyborg.getLeftBumper().whenPressed(new Setpoint1Preset());
		opCyborg.getRightBumper().whenPressed(new Setpoint2Preset());
		drCyborg.getButtonTwo().whenPressed(new BumpDown());
		drCyborg.getButtonThree().whenPressed(new BumpUp());
		drCyborg.getBackButton().whenPressed(new CalibrateElevator());
		//Switch driver controllers that are suppose to be operator controls.
		//Don't know if drive multiplies have been implemented, but they need to be.
		//Adding manual up and down for elevator that will be put on the left and right bumpers
	}

	private boolean isNormalCyborg() {
		return (boolean) cyborgChooser.getSelected();
	}

	public Cyborg getCyborg() {
		if (drCyborg instanceof CyborgController && !isNormalCyborg()) {
			drCyborg = new PrecisionCyborgController(0);
			opCyborg = new CyborgController(1);
			init();
		} else if (drCyborg instanceof PrecisionCyborgController
				&& isNormalCyborg()) {
			drCyborg = new CyborgController(0);
			opCyborg = new PrecisionCyborgController(1);
			init();
		}
		return drCyborg;
	}
}

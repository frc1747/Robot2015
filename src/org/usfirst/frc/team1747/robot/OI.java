package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.commands.BumpDown;
import org.usfirst.frc.team1747.robot.commands.BumpUp;
import org.usfirst.frc.team1747.robot.commands.CalibrateElevator;
import org.usfirst.frc.team1747.robot.commands.DecreaseElevatorLevel;
import org.usfirst.frc.team1747.robot.commands.GetInfo;
import org.usfirst.frc.team1747.robot.commands.IncreaseElevatorLevel;
import org.usfirst.frc.team1747.robot.commands.StopElevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

	private SendableChooser cyborgChooser;
	private Cyborg cyborg;
	private Joystick opController;
	private JoystickButton button1;
	private JoystickButton button2;
	private JoystickButton button3;
	private JoystickButton button4;

	public OI() {
		cyborgChooser = new SendableChooser();
		cyborgChooser.addDefault("Normal Cyborg", true);
		cyborgChooser.addObject("Precision Cyborg", false);
		SmartDashboard.putData("Cyborg Version", cyborgChooser);
		cyborg = new CyborgController(0);
		opController = new Joystick(1);
		button1 = new JoystickButton(opController, 1);
		button2 = new JoystickButton(opController, 2);
		button3 = new JoystickButton(opController, 3);
		button4 = new JoystickButton(opController, 4);
	}

	public void init() {
		cyborg.getButtonOne().whenPressed(new DecreaseElevatorLevel());
		cyborg.getButtonFour().whenPressed(new IncreaseElevatorLevel(0));
		cyborg.getButtonTwo().whenPressed(new BumpDown());
		cyborg.getButtonThree().whenPressed(new BumpUp());
		cyborg.getBackButton().whenPressed(new CalibrateElevator());
		cyborg.getButtonOne().whenPressed(new StopElevator());
		button1.whenPressed(new CalibrateElevator());
		button3.whenPressed(new GetInfo());
	}

	private boolean isNormalCyborg() {
		return (boolean) cyborgChooser.getSelected();
	}

	public Cyborg getCyborg() {
		if (cyborg instanceof CyborgController && !isNormalCyborg()) {
			cyborg = new PrecisionCyborgController(0);
			init();
		} else if (cyborg instanceof PrecisionCyborgController
				&& isNormalCyborg()) {
			cyborg = new CyborgController(0);
			init();
		}
		return cyborg;
	}
}

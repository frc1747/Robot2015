package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.commands.BumpDown;
import org.usfirst.frc.team1747.robot.commands.BumpUp;
import org.usfirst.frc.team1747.robot.commands.CalibrateElevator;
import org.usfirst.frc.team1747.robot.commands.DecreaseElevatorLevel;
import org.usfirst.frc.team1747.robot.commands.DriveStraight;
import org.usfirst.frc.team1747.robot.commands.IncreaseElevatorLevel;
import org.usfirst.frc.team1747.robot.commands.StopElevator;
import org.usfirst.frc.team1747.robot.commands.ToggleSpeed;
import org.usfirst.frc.team1747.robot.commands.Turn;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

	private SendableChooser cyborgChooser;
	private Cyborg cyborg;

	public OI() {
		cyborgChooser = new SendableChooser();
		cyborgChooser.addDefault("Normal Cyborg", true);
		cyborgChooser.addObject("Precision Cyborg", false);
		SmartDashboard.putData("Cyborg Version", cyborgChooser);
		cyborg = new CyborgController(0);
	}

	public void init() {
		cyborg.getLeftTrigger().whenPressed(new DecreaseElevatorLevel());
		cyborg.getRightTrigger().whenPressed(new IncreaseElevatorLevel());
		cyborg.getLeftBumper().whenPressed(new BumpDown());
		cyborg.getRightBumper().whenPressed(new BumpUp());
		cyborg.getStartButton().whenPressed(new CalibrateElevator());
		cyborg.getBackButton().whenPressed(new ToggleSpeed());
		cyborg.getButtonOne().whenPressed(new StopElevator());
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

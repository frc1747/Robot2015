package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.commands.DecreaseElevatorLevel;
import org.usfirst.frc.team1747.robot.commands.IncreaseElevatorLevel;

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
		// cyborg.getButtonTwo().whenPressed(new DecreaseElevatorLevel());
		// cyborg.getButtonFour().whenPressed(new IncreaseElevatorLevel());
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

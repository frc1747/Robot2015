package org.usfirst.frc.team1747.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private SendableChooser cyborgChooser;
	private Cyborg cyborg;

	public OI(){
		cyborgChooser=new SendableChooser();
		cyborgChooser.addDefault("Normal Cyborg", true);
		cyborgChooser.addObject("Precision Cyborg", false);
		SmartDashboard.putData("Cyborg Version",cyborgChooser);
		cyborg=new CyborgController(0);
	}
	
	private boolean isNormalCyborg(){
		return (boolean) cyborgChooser.getSelected();
	}

	public Cyborg getCyborg() {
		if(cyborg instanceof CyborgController&&!isNormalCyborg()){
			cyborg=new PrecisionCyborgController(0);
		}else if(cyborg instanceof PrecisionCyborgController &&isNormalCyborg()){
			cyborg=new CyborgController(0);
		}
		return cyborg;
	}

}


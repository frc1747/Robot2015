package org.usfirst.frc.team1747.robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
    private PrecisionCyborgController cyborg;
    
    public OI(){
    	cyborg=new PrecisionCyborgController(0);
    }

	public PrecisionCyborgController getCyborg() {
		return cyborg;
	}
    
}


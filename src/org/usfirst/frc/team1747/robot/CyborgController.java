package org.usfirst.frc.team1747.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class CyborgController {

    public static final int LEFT_JOY_HORIZ_AXIS = 0;
    public static final int LEFT_JOY_VERT_AXIS = 1;
    public static final int RIGHT_JOY_HORIZ_AXIS = 3;
    public static final int RIGHT_JOY_VERT_AXIS = 4;

    public static final int JOY_X_BUTTON = 1;
    public static final int JOY_Y_BUTTON = 2;
    public static final int JOY_A_BUTTON = 3;
    public static final int JOY_B_BUTTON = 4;
    
    public static final int JOY_LEFT_BUMPER = 5;
    public static final int JOY_RIGHT_BUMPER = 6;
    public static final int TRIGGER_LEFT_BUTTON=7;
    public static final int TRIGGER_RIGHT_BUTTON=8;
    
    public static final int BACK_BUTTON=9;
    public static final int START_BUTTON=10;
    
    public static final int LEFT_JOY_PRESS=11;
    public static final int RIGHT_JOY_PRESS=12;
    
    public static final int FPS_BUTTON=13;
    
   
    
    //D-PAD == HAT
    //TRIGGERS == Z-AXIS

    Joystick controller;
    JoystickButton buttonA, buttonB, buttonX, buttonY;
    JoystickButton leftBumper, rightBumper, leftTrigger, rightTrigger;
    JoystickButton leftJoystickPress, rightJoystickPress;
    JoystickButton fpsButton, backButton, startButton;
   
    public CyborgController(int portNum) {
        controller = new Joystick(portNum);
        buttonX = new JoystickButton(controller, JOY_X_BUTTON);
        buttonY = new JoystickButton(controller, JOY_Y_BUTTON);
        buttonA = new JoystickButton(controller, JOY_A_BUTTON);
        buttonB = new JoystickButton(controller, JOY_B_BUTTON);
        leftBumper=new JoystickButton(controller,JOY_LEFT_BUMPER);
        rightBumper = new JoystickButton(controller, JOY_RIGHT_BUMPER);
        leftTrigger = new JoystickButton(controller, TRIGGER_LEFT_BUTTON);
        rightTrigger = new JoystickButton(controller, TRIGGER_RIGHT_BUTTON);
        leftJoystickPress = new JoystickButton(controller, LEFT_JOY_PRESS);
        rightJoystickPress = new JoystickButton(controller, RIGHT_JOY_PRESS);
        fpsButton = new JoystickButton(controller, FPS_BUTTON);
        backButton = new JoystickButton(controller, BACK_BUTTON);
        startButton = new JoystickButton(controller, START_BUTTON);
        
    }

    public double getLeftVert() {
        return controller.getRawAxis(LEFT_JOY_VERT_AXIS);
    }

    public double getLeftHoriz() {
        return controller.getRawAxis(LEFT_JOY_HORIZ_AXIS);
    }

    public double getRightVert() {
        return -controller.getRawAxis(RIGHT_JOY_VERT_AXIS);
    }

    public double getRightHoriz() {
        return -controller.getRawAxis(RIGHT_JOY_HORIZ_AXIS);
    }

    public JoystickButton getButtonA() {
        return buttonA;
    }

    public JoystickButton getButtonB() {
        return buttonB;
    }

    public JoystickButton getButtonX() {
        return buttonX;
    }

    public JoystickButton getButtonY() {
        return buttonY;
    }

    public JoystickButton getLeftBumper() {
		return leftBumper;
	}

	public JoystickButton getRightBumper() {
		return rightBumper;
	}

	public JoystickButton getLeftTrigger() {
		return leftTrigger;
	}

	public JoystickButton getRightTrigger() {
		return rightTrigger;
	}

	public JoystickButton getLeftJoystickPress() {
		return leftJoystickPress;
	}

	public JoystickButton getRightJoystickPress() {
		return rightJoystickPress;
	}

	public JoystickButton getFpsButton() {
		return fpsButton;
	}

	public JoystickButton getStartButton() {
		return startButton;
	}
}

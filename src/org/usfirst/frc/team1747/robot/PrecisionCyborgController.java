package org.usfirst.frc.team1747.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PrecisionCyborgController {

	public static final int LEFT_JOY_HORIZ_AXIS = 0;
	public static final int LEFT_JOY_VERT_AXIS = 1;

	public static final int RIGHT_JOY_HORIZ_AXIS = 2;
	public static final int RIGHT_JOY_VERT_AXIS = 3;

	public static final int JOY_SQUARE_BUTTON = 1;
	public static final int JOY_X_BUTTON = 2;
	public static final int JOY_CIRCLE_BUTTON = 3;
	public static final int JOY_TRIANGLE_BUTTON = 4;

	public static final int JOY_LEFT_BUMPER = 5;
	public static final int JOY_RIGHT_BUMPER = 6;
	public static final int TRIGGER_LEFT_BUTTON=7;
	public static final int TRIGGER_RIGHT_BUTTON=8;

	public static final int BACK_BUTTON=9;
	public static final int START_BUTTON=10;

	public static final int LEFT_JOY_PRESS=11;
	public static final int RIGHT_JOY_PRESS=12;

	public static final int FPS_BUTTON=13;

	Joystick controller;
	JoystickButton buttonX, buttonCircle, buttonSquare, buttonTriangle;
	JoystickButton leftBumper, rightBumper, leftTrigger, rightTrigger;
	JoystickButton leftJoystickPress, rightJoystickPress;
	JoystickButton fpsButton, backButton, startButton;

	public PrecisionCyborgController(int portNum) {
		controller = new Joystick(portNum);
		buttonSquare = new JoystickButton(controller, JOY_SQUARE_BUTTON);
		buttonTriangle = new JoystickButton(controller, JOY_TRIANGLE_BUTTON);
		buttonX = new JoystickButton(controller, JOY_X_BUTTON);
		buttonCircle = new JoystickButton(controller, JOY_CIRCLE_BUTTON);
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
		return -controller.getRawAxis(LEFT_JOY_VERT_AXIS);
	}

	public double getLeftHoriz() {
		return controller.getRawAxis(LEFT_JOY_HORIZ_AXIS);
	}

	public double getRightVert() {
		return -controller.getRawAxis(RIGHT_JOY_VERT_AXIS);
	}

	public double getRightHoriz() {
		return controller.getRawAxis(RIGHT_JOY_HORIZ_AXIS);
	}
	
	public double getLeftAngleRad(){
		return Math.atan(this.getLeftVert()/this.getLeftHoriz());
	}
	
	public double getRightAngleRad(){
		return Math.atan(this.getRightVert()/this.getRightHoriz());
	}

	public int getDPad(){
		return controller.getPOV();
	}

	public JoystickButton getButtonSquare() {
		return buttonSquare;
	}

	public JoystickButton getButtonCircle() {
		return buttonCircle;
	}

	public JoystickButton getButtonX() {
		return buttonX;
	}

	public JoystickButton getButtonTriangle() {
		return buttonTriangle;
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
	
	public void setRumble(){
		
	}

	public void logToSmartDashboard(){
		SmartDashboard.putBoolean("X Button",buttonX.get());
		SmartDashboard.putBoolean("Circle Button",buttonCircle.get());
		SmartDashboard.putBoolean("Square Button",buttonSquare.get());
		SmartDashboard.putBoolean("Triangle Button",buttonTriangle.get());
		SmartDashboard.putBoolean("Left Bumper", leftBumper.get());
		SmartDashboard.putBoolean("Right Bumper", rightBumper.get());
		SmartDashboard.putBoolean("Left Trigger", leftTrigger.get());
		SmartDashboard.putBoolean("Right Trigger", rightTrigger.get());
		SmartDashboard.putBoolean("L3", leftJoystickPress.get());
		SmartDashboard.putBoolean("R3", rightJoystickPress.get());
		SmartDashboard.putBoolean("FPS Button", fpsButton.get());
		SmartDashboard.putBoolean("Back Button", backButton.get());
		SmartDashboard.putBoolean("Start Button", startButton.get());
		SmartDashboard.putNumber("Left Joystick X", getLeftHoriz());
		SmartDashboard.putNumber("Left Joystick Y", getLeftVert());
		SmartDashboard.putNumber("Right Joystick X", getRightHoriz());
		SmartDashboard.putNumber("Right Joystick Y", getRightVert());
		SmartDashboard.putNumber("D-Pad", getDPad());
	}
}
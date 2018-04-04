package org.usfirst.frc.team6135.robot;

import org.usfirst.frc.team6135.robot.commands.teleoperated.AutoCubeAlign;
import org.usfirst.frc.team6135.robot.commands.teleoperated.AutoSwitchAlign;
import org.usfirst.frc.team6135.robot.commands.teleoperated.CancelOperation;
import org.usfirst.frc.team6135.robot.commands.teleoperated.EmergencySwitch;
import org.usfirst.frc.team6135.robot.commands.teleoperated.GearShift;
import org.usfirst.frc.team6135.robot.commands.teleoperated.IntakingPosition;
import org.usfirst.frc.team6135.robot.commands.teleoperated.OperateIntakePneumatics;
import org.usfirst.frc.team6135.robot.commands.teleoperated.ResetGyro;
import org.usfirst.frc.team6135.robot.commands.teleoperated.ScalingPosition;
import org.usfirst.frc.team6135.robot.commands.teleoperated.SwitchingPosition;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	/**
	 * <b>CONTROLS</b><br><br>
	 * 
	 * Drive:
	 * <ul>
	 * 	<li>Left Analog Stick: Forwards/Backwards</li>
	 * 	<li>Right Analog Stick: Left/Right</li>
	 * 	<li>Left Bumper: Shift gear to slower configuration</li>
	 * 	<li>Right Bumper: Shift gear to faster configuration</li>
	 * 	<li>X Button: Auto Switch align
	 * 		- Note: The robot will first attempt to locate the alliance colour, but if
	 * 		the team switch cannot be found, it will ignore the colour and try to find any switch at all.</li>
	 * 	<li>Y Button: Auto Power Cube align</li>
	 * 	<li>B Button: Cancel auto align (in case something goes terribly wrong)</li>
	 * 	<li>XBOX Button: Enable rocket booster</li>
	 * </ul>
	 * Attachments:
	 * <ul>
	 * 	<li>Left Analog Stick: Elevator</li>
	 * 	<li>Right Analog Stick: Tilt Wrist</li>
	 * 	<li>Left Trigger: Intake Out (Analog)</li>
	 * 	<li>Right Trigger: Intake In (Analog)</li>
	 * 	<li>Left Bumper: Intake Open</li>
	 * 	<li>Right Bumper: Intake Close</li>
	 * 	<li>X Button: Raise elevator & wrist to place cube in switch position</li>
	 * 	<li>Y Button: Raise elevator & wrist to shooting position</li>
	 * 	<li>A Button: Lower elevator & wrist to intaking position</li>
	 * 	<li>B Button: The Emergency Button (Hold 2s, changes behavior of the wrist, in case of gyro issues)</li>
	 * 	<li>Start Button: Gyro reset (Use only if gyro drift gets too much, and wrist is flat.)</li>
	 * </ul>
	 */
	public static class Controls {
		public static final int FWD_REV = RobotMap.ControllerMap.LSTICK_Y_AXIS;
		public static final int LEFT_RIGHT = RobotMap.ControllerMap.RSTICK_X_AXIS;
		public static final int SLOW_GEAR = RobotMap.ControllerMap.LBUMPER;
		public static final int FAST_GEAR = RobotMap.ControllerMap.RBUMPER;
		public static final int AUTO_CUBE_ALIGN = RobotMap.ControllerMap.BUTTON_Y;
		public static final int AUTO_SWITCH_ALIGN = RobotMap.ControllerMap.BUTTON_X;
		public static final int CANCEL_ALIGN = RobotMap.ControllerMap.BUTTON_B;
		
		public static final int ELEVATOR = RobotMap.ControllerMap.LSTICK_Y_AXIS;
		public static final int WRIST = RobotMap.ControllerMap.RSTICK_Y_AXIS;
		public static final int INTAKE_IN = RobotMap.ControllerMap.RTRIGGER;
		public static final int INTAKE_OUT = RobotMap.ControllerMap.LTRIGGER;
		public static final int INTAKING_POSITION = RobotMap.ControllerMap.BUTTON_A;
		public static final int SHOOTING_POSITION = RobotMap.ControllerMap.BUTTON_Y;
		public static final int SWITCHING_POSITION = RobotMap.ControllerMap.BUTTON_X;
		public static final int EMERGENCY = RobotMap.ControllerMap.BUTTON_B;
		public static final int INTAKE_OPEN = RobotMap.ControllerMap.LBUMPER;
		public static final int INTAKE_CLOSE = RobotMap.ControllerMap.RBUMPER;
		//Note: No button is defined here for Gyro Reset since it requires the Start Button,
		//which has no mapping. An anonymous class extending Trigger is used instead.
	}
	
	public static XboxController driveController;
	public static XboxController attachmentsController;
	
	public static JoystickButton gearShiftFast;
	public static JoystickButton gearShiftSlow;
	
	public static JoystickButton autoCubeAlign;
	public static JoystickButton autoSwitchAlign;
	public static JoystickButton cancelAlign;
	
	public static JoystickButton emergencyButton;
	public static JoystickButton shootingPosition;
	public static JoystickButton intakingPosition;
	public static JoystickButton switchingPosition;
	public static JoystickButton intakeOpen, intakeClose;
	
	public OI() {
		//Port 0 is on the right of the programming laptop and port 1 is on the left.
		driveController = new XboxController(0);
		attachmentsController = new XboxController(1);
		
		//Fast gear = right bumper
		gearShiftFast = new JoystickButton(driveController, Controls.FAST_GEAR);
		gearShiftSlow = new JoystickButton(driveController, Controls.SLOW_GEAR);
		autoCubeAlign = new JoystickButton(driveController, Controls.AUTO_CUBE_ALIGN);
		autoSwitchAlign = new JoystickButton(driveController, Controls.AUTO_SWITCH_ALIGN);
		cancelAlign = new JoystickButton(driveController, Controls.CANCEL_ALIGN);
		emergencyButton = new JoystickButton(attachmentsController, Controls.EMERGENCY);
		shootingPosition = new JoystickButton(attachmentsController, Controls.SHOOTING_POSITION);
		intakingPosition = new JoystickButton(attachmentsController, Controls.INTAKING_POSITION);
		switchingPosition = new JoystickButton(attachmentsController, Controls.SWITCHING_POSITION);
		intakeOpen = new JoystickButton(attachmentsController, Controls.INTAKE_OPEN);
		intakeClose = new JoystickButton(attachmentsController, Controls.INTAKE_CLOSE);
		
		gearShiftFast.whenPressed(new GearShift(GearShift.GEAR_FAST));
		gearShiftSlow.whenPressed(new GearShift(GearShift.GEAR_SLOW));
		//gearShiftFast.whenReleased(new GearShift(GearShift.GEAR_STOPSHIFT));
		//gearShiftSlow.whenReleased(new GearShift(GearShift.GEAR_STOPSHIFT));
		
		Command autoCubeAlignCmd = new AutoCubeAlign(RobotMap.Speeds.AUTO_TURN_SPEED); 
		Command autoSwitchAlignCmd = new AutoSwitchAlign(RobotMap.Speeds.AUTO_TURN_SPEED);
		
		autoCubeAlign.whenPressed(autoCubeAlignCmd);
		autoSwitchAlign.whenPressed(autoSwitchAlignCmd);
		cancelAlign.whenPressed(new CancelOperation(autoCubeAlignCmd, autoSwitchAlignCmd));
		
		Command emergencyCmd = new EmergencySwitch();
		
		emergencyButton.whenPressed(emergencyCmd);
		emergencyButton.whenReleased(new CancelOperation(emergencyCmd));
		shootingPosition.whenPressed(new ScalingPosition());
		intakingPosition.whenPressed(new IntakingPosition());
		switchingPosition.whenPressed(new SwitchingPosition());
		intakeOpen.whenPressed(new OperateIntakePneumatics(OperateIntakePneumatics.OPEN));
		intakeClose.whenPressed(new OperateIntakePneumatics(OperateIntakePneumatics.CLOSE));
		
		//A trigger has to be used instead since there's no mapping for the start and back buttons
		Trigger resetGyro = new Trigger() {
			@Override
			public boolean get() {
				return attachmentsController.getStartButtonPressed();
			}
		};
		resetGyro.whenActive(new ResetGyro());
	}
}

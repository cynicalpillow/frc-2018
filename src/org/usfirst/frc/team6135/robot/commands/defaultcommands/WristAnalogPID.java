package org.usfirst.frc.team6135.robot.commands.defaultcommands;

import org.usfirst.frc.team6135.robot.OI;
import org.usfirst.frc.team6135.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WristAnalogPID extends Command {
	
	static final double DEADZONE = 0.15;
	
	boolean pidEnabled = false;

    public WristAnalogPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.wristSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public boolean getEnabled() {
    	return pidEnabled;
    }
    public void setEnabled(boolean b) {
    	pidEnabled = b;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double joystickVal = OI.attachmentsController.getRawAxis(OI.Controls.WRIST);
    	//Check if joystick is pushed
    	if(joystickVal > DEADZONE) {
    		//Disable the PID so that we can freely move the motor
    		if(Robot.wristSubsystem.isEnabled())
    			Robot.wristSubsystem.disable();
    		//Set the speed
    		Robot.wristSubsystem.setRaw(joystickVal);
    	}
    	//Do PID stuff only if adjustments are enabled
    	else if(pidEnabled) {
    		//IMPORTANT: Since if the joystick was previously pushed, the PID would be enabled,
    		//this would only run if the joystick went from having input to not having input.
    		if(!Robot.wristSubsystem.isEnabled()) {
    			//At this point we can set a new set point for the PID to hold the wrist in place
    			Robot.wristSubsystem.setSetpoint(Robot.wristSubsystem.getAngle());
    			//Enable and let the PID take over
    			//Reset to make sure no integral windup happens
    			Robot.wristSubsystem.getPIDController().reset();
    			Robot.wristSubsystem.enable();
    		}
    	}
    	//Otherwise, just set the motor to 0
    	else {
    		if(Robot.wristSubsystem.isEnabled())
    			Robot.wristSubsystem.disable();
    		Robot.wristSubsystem.setRaw(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Don't disable the PID, but change its setpoint to stop it from adjusting
    	Robot.wristSubsystem.setSetpoint(Robot.wristSubsystem.getAngle());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Don't disable the PID, but change its setpoint to stop it from adjusting
    	Robot.wristSubsystem.setSetpoint(Robot.wristSubsystem.getAngle());
    }
}
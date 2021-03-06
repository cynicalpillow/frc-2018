package org.usfirst.frc.team6135.robot.commands.autonomous;

import static org.usfirst.frc.team6135.robot.RobotMap.leftEncoder;
import static org.usfirst.frc.team6135.robot.RobotMap.rightEncoder;

import org.usfirst.frc.team6135.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Turns the robot a certain degrees with a certain speed, with encoders
 */
public class AutoTurn extends Command {
	
	public static final double ROBOT_DIAM = 23.25; //For turning, INCHES
	public static final double ROBOT_RADIUS = ROBOT_DIAM/2;
	public static final double DISTANCE_PER_DEGREE = (ROBOT_DIAM*Math.PI)/360;
	
	public int degrees;
	public double leftDistance;
	public double rightDistance;
	public double leftSpeed;
	public double rightSpeed;
	
	//Degrees follow the unit circle
	//i.e. Positive means counter-clockwise and negative means clockwise
    public AutoTurn(int degrees, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.degrees = degrees;
    	leftSpeed = degrees > 0 ? -speed : speed;
    	rightSpeed = degrees > 0 ? speed : -speed;
    	leftDistance = -DISTANCE_PER_DEGREE*degrees;
    	rightDistance = DISTANCE_PER_DEGREE*degrees;
    }
    @Deprecated
    public AutoTurn(double leftDistance, double rightDistance, double speed){
    	//Assume that leftDistance is negative
    	requires(Robot.drive);
    	this.leftDistance = leftDistance;
    	this.rightDistance = rightDistance;
    	this.leftSpeed = leftDistance > 0 ? speed : -speed;
    	this.rightSpeed = rightDistance > 0 ? speed : -speed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.setMotorsVBus(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(leftEncoder.getDistance()) >= Math.abs(leftDistance) 
        		|| Math.abs(rightEncoder.getDistance()) >= Math.abs(rightDistance));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setMotorsVBus(0, 0);
    	leftEncoder.reset();
    	rightEncoder.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.setMotorsVBus(0, 0);
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
}

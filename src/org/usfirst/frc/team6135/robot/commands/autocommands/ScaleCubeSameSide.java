package org.usfirst.frc.team6135.robot.commands.autocommands;

import org.usfirst.frc.team6135.robot.RobotMap;
import org.usfirst.frc.team6135.robot.commands.autonomous.AutoIntake;
import org.usfirst.frc.team6135.robot.commands.autonomous.AutoTurnPID;
import org.usfirst.frc.team6135.robot.commands.autonomous.Delay;
import org.usfirst.frc.team6135.robot.commands.autonomous.DriveStraightDistancePID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Hard-coded auto command that drives forward and turns 90 degrees, then shoots the Power Cube at full speed to the Scale. <br>
 *	Note that this command requires a different starting position; while others require the elevator to be down, this command
 *	requires the elevator and wrist to be fully raised to shoot the Power Cube properly.
 */
public class ScaleCubeSameSide extends CommandGroup {
	
	public static final int SIDE_LEFT = 1;
	public static final int SIDE_RIGHT = -1;

    public ScaleCubeSameSide(int side) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.z
    	addSequential(new DriveStraightDistancePID(RobotMap.ArenaDimensions.SCALE_CENTER_DISTANCE));
    	addSequential(new Delay(RobotMap.AUTO_DELAY));
    	addSequential(new AutoTurnPID(-90 * side));
    	addSequential(new Delay(RobotMap.AUTO_DELAY));
    	//addSequential(new DriveStraightDistanceEx(-4, -RobotMap.Speeds.AUTO_SPEED));
    	//Delay for a short period of time for the robot to stablize before shooting
    	addSequential(new Delay(RobotMap.AUTO_DELAY));
    	addSequential(new AutoIntake(2, -1.0));
    }
}

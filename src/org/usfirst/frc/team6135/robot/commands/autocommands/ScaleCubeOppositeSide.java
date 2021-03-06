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
public class ScaleCubeOppositeSide extends CommandGroup {
	
	public static final int SIDE_LEFT = 1;
	public static final int SIDE_RIGHT = -1;

    public ScaleCubeOppositeSide(int side) {
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
        // arm.
    	addSequential(new DriveStraightDistancePID(RobotMap.ArenaDimensions.SCALE_OPPOSITE_DIST1 - RobotMap.ROBOT_LENGTH / 2));
    	addSequential(new Delay(RobotMap.AUTO_DELAY));
    	addSequential(new AutoTurnPID(-90 * side));
    	addSequential(new Delay(RobotMap.AUTO_DELAY));
    	addSequential(new DriveStraightDistancePID(RobotMap.ArenaDimensions.SCALE_OPPOSITE_DIST2 - RobotMap.ROBOT_WIDTH / 2));
    	addSequential(new Delay(RobotMap.AUTO_DELAY));
    	addSequential(new AutoTurnPID(90 * side));
    	addSequential(new Delay(RobotMap.AUTO_DELAY));
    	//ROBOT_LENGTH / 2 is going to be similar to SCALE_OPPOSITE_DIST3 so probably no point in driving
    	//addSequential(new DriveStraightDistanceEx(RobotMap.ArenaDimensions.SCALE_OPPOSITE_DIST3 - RobotMap.ROBOT_LENGTH / 2, RobotMap.Speeds.AUTO_SPEED));
    	//addSequential(new Delay(RobotMap.AUTO_DELAY));
    	addSequential(new AutoIntake(RobotMap.AUTO_INTAKE_TIME, -1.0));
    }
}

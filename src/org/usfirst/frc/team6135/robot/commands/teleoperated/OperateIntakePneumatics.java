package org.usfirst.frc.team6135.robot.commands.teleoperated;

import org.usfirst.frc.team6135.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *	Operates the intake's pneumatics.
 */
public class OperateIntakePneumatics extends InstantCommand {
	
	public static final int OPEN = 0;
	public static final int CLOSE = 1;
	
	int state;

	/**
	 * Creates a new instance of this command that sets the intake to a state.<br>
	 * Please use the predefined constants {@code OPEN} and {@code CLOSE}. If the state is unrecognized this command will not do anything.
	 * @param state - Either OPEN or CLOSE, the state to set the intake to
	 */
    public OperateIntakePneumatics(int state) {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.intakePneumaticsSubsystem);
        this.state = state;
    }

    // Called once when the command executes
    protected void initialize() {
    	if(state == OPEN)
    		Robot.intakePneumaticsSubsystem.open();
    	else if(state == CLOSE)
    		Robot.intakePneumaticsSubsystem.close();
    }

}

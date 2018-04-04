package org.usfirst.frc.team6135.robot.subsystems;

import org.usfirst.frc.team6135.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	The subsystem that is the pneumatics on the intake. Can open or close.
 */
public class IntakePneumaticsSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //These can be reversed if the solenoid was connected the opposite way
    public void open() {
    	RobotMap.intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void close() {
    	RobotMap.intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}


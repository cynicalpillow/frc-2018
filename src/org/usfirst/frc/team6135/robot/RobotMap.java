package org.usfirst.frc.team6135.robot;

import org.usfirst.frc.team6135.robot.misc.PIDMotorController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;


/**
 * The RobotMap is a mapping from the motor controllers, sensors and other hardware
 * to objects in the code, as well as some constants such as the max drive speed, making checking
 * the wiring easier and significantly reduces the number of magic numbers floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static TalonSRX leftMotor = new TalonSRX(0);
	// public static TalonSRX rightMotor = new TalonSRX(1);
	
	/*
	 * A mapping of the XBox controller. Use this static class instead of 
	 * magic numbers or individual constants to keep everything clear.
	 */
	public static class ControllerMap {
		public static final int LSTICK_X_AXIS = 0;
		public static final int LSTICK_Y_AXIS = 1;
		public static final int RSTICK_X_AXIS = 4;
		public static final int RSTICK_Y_AXIS = 5;
		public static final int LTRIGGER = 2;
		public static final int RTRIGGER = 3;
		
		public static final int BUTTON_A = 1;
		public static final int BUTTON_B = 2;
		public static final int BUTTON_X = 3;
		public static final int BUTTON_Y = 4;
		public static final int LBUMPER = 5;
		public static final int RBUMPER = 6;
		public static final int BUTTON_BACK = 7;
		public static final int BUTTON_START = 8;
		public static final int BUTTON_LSTICK = 9;
		public static final int BUTTON_RSTICK = 10;
	}
	
	/*
	 * Holds values for the dimensions of the arena. 
	 * Used in fallback (hard-coded) auto
	 * All units are in inches
	 */
	public static class ArenaDimensions {
		//12.0 feet
		//Actual auto line is 10 feet from the alliance wall, but an extra 2ft is added just in case
		public static final double BASELINE_DISTANCE = 12.0 * 12;
		//Minus 28 inches since 14ft is to the middle of the switch
		public static final double SWITCH_DISTANCE = 14.0 * 12 - 28 - 4;
		public static final double VISION_SAMPLING_DISTANCE = SWITCH_DISTANCE / 4;
		public static final double SWITCH_CENTER_DISTANCE = 14.0 * 12;
		//Alliance station width = 22ft, switch (including walls) width = 12ft 9 1/2in
		//Leftover = 110 1/2in, so each side has 55 1/4in. Subtract away the Robot's width since we only want front of the Robot to be at the switch.
		public static final double SWITCH_EDGE_OFFSET = 55.25 - ROBOT_WIDTH;
		//Minus 3ft since we want to target the middle
		public static final double SWITCH_SIZE = 12.0 * 12 - 3.0 * 12;
		public static final double BASELINE_OFFSET = 3.0 * 12;
		//17ft minus half of robot length
		public static final double SCALE_CENTER_DISTANCE = 27.0 * 12 - ROBOT_LENGTH / 2;
		//Scale without robot length subtracted
		public static final double SCALE_CENTER_DISTANCE_RAW = 27.0 * 12;
		//Distance from the scale plate to shoot
		public static final double SHOOTING_DISTANCE = 1.5 * 12;
		//Length of entire scale
		public static final double SCALE_LENGTH = 15.0 * 12;
		//3.5ft from parallel to the alliance station edge to the scale plate, 
		//Minus 1.5ft shooting distance, and minus half of robot length to make sure the front is at the right spot
		public static final double SCALE_OFFSET = (3.5 * 12) - SHOOTING_DISTANCE - ROBOT_WIDTH / 2;
		//Distance robot travels before turning to scale cube opposite side
		public static final double SCALE_OPPOSITE_DIST1 = 22.0 * 12;
		//Distance robot travels to get to the opposite scale plate
		//Distance from starting position to scale + Scale length - 1/2 scale plate length
		public static final double SCALE_OPPOSITE_DIST2 = 42.0 + SCALE_LENGTH - (1.5 * 12);
		//Distance the robot travels to get into the right position for a shot
		public static final double SCALE_OPPOSITE_DIST3 = 3.0 * 12 - SHOOTING_DISTANCE;
	}
	
	//+7in for the bumpers
	public static final double ROBOT_LENGTH = 32.0 + 7.0;
	public static final double ROBOT_WIDTH = 27.0 + 7.0;
	
	public static final double INTAKE_LENGTH = 6;
	
	//Delay between each autonomous action, in milliseconds.
	public static final double AUTO_DELAY = 500;
	//Amount of time the elevator climbs for
	public static final double AUTO_ELEVATOR_TIME = 4.5; //4.5
	
	public static final double AUTO_INTAKE_TIME = 1.5;
	
	public static final double AUTO_WRIST_TIME = 3;
	/*
	 * Holds constants for the the top speeds of things to keep stuff organized 
	 */
	public static class Speeds {
		public static final double ELEVATOR_SPEED = 1.0;
		public static final double WRIST_SPEED = 1.0;
		//Driving
		public static final double AUTO_SPEED = 0.40; //0.6
		//Turning
		public static final double AUTO_TURN_SPEED = 0.35;
		//Intake
		public static final double AUTO_INTAKE_SPEED = 0.5;
		//Elevator
		public static final double AUTO_ELEVATOR_SPEED = 1.0; 
		public static final double DRIVE_SPEED = 1.0;
	}
	
	public static final int WHEEL_DIAMETER = 6; //INCHES
	public static final double WHEEL_CIRCUMFRENCE = WHEEL_DIAMETER*Math.PI;
	public static final double DRIVE_ENCODER_PPR = 2048;
	public static final double DISTANCE_PER_PULSE = WHEEL_CIRCUMFRENCE/DRIVE_ENCODER_PPR;
	
	public static final int CAMERA_WIDTH = 640;
	public static final int CAMERA_HEIGHT = 360;
	public static final double CAMERA_FOV = 61.0;
	public static final int CAMERA_CENTER = CAMERA_WIDTH / 2;
	public static final double CAMERA_FOCAL_LEN = CAMERA_WIDTH / (2 * Math.tan(Math.toRadians(CAMERA_FOV / 2)));
	public static final int VISION_WIDTH = 320;
	public static final int VISION_HEIGHT = 180;
	public static final int VISION_CENTER = VISION_WIDTH / 2;
	public static final double VISION_FOCAL_LEN = VISION_WIDTH / (2 * Math.tan(Math.toRadians(CAMERA_FOV / 2)));
	
	public static TalonSRX leftDriveTalon1 = new TalonSRX(3);
    public static TalonSRX leftDriveTalon2 = new TalonSRX(2);	
    public static TalonSRX rightDriveTalon1 = new TalonSRX(1);
    public static TalonSRX rightDriveTalon2 = new TalonSRX(4);
    public static VictorSPX leftDriveVictor = new VictorSPX(5);
    public static VictorSPX rightDriveVictor = new VictorSPX(6);
    
    public static PIDMotorController leftDrivePIDMotor = new PIDMotorController(leftDriveTalon1);
    public static PIDMotorController rightDrivePIDMotor = new PIDMotorController(rightDriveTalon1);
    
    public static DoubleSolenoid gearshiftSolenoid = new DoubleSolenoid(0, 1);
    public static DoubleSolenoid intakeSolenoid = new DoubleSolenoid(2, 3);
    
    public static VictorSP elevatorVictor = new VictorSP(7);
    public static VictorSP wristVictor = new VictorSP(8);
    public static VictorSP intakeLeft = new VictorSP(5);
    public static VictorSP intakeRight = new VictorSP(6);
    
    public static Encoder rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
    public static Encoder leftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
    //Not using this
    //public static Encoder elevatorEncoder = new Encoder(4, 5, false, EncodingType.k4X);
    
    public static DigitalInput elevatorTopSwitch = new DigitalInput(4);
    public static DigitalInput elevatorBottomSwitch = new DigitalInput(5);
    public static DigitalInput wristSwitch = new DigitalInput(6);
    
    
    //This is the gyroscope that is mounted in the SPI port of the roboRIO
    //Use the ADXRS450_Gyro class instead of AnalogGyro
    //public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    
    //This is the gyroscope that is mounted on the wrist to detect its angle
    //Due to the current design, wrist angle has to be constantly maintained
    public static ADXRS450_Gyro wristGyro = new ADXRS450_Gyro();
	
	public static void init() {
		//Set back motors to follow the front motors
		leftDriveTalon2.follow(leftDriveTalon1);
		rightDriveTalon2.follow(rightDriveTalon1);
		leftDriveVictor.follow(leftDriveTalon1);
		rightDriveVictor.follow(rightDriveTalon1);
		leftDriveVictor.setInverted(true);
		rightDriveVictor.setInverted(true);
		
		leftDriveTalon1.set(ControlMode.PercentOutput, 0);
		rightDriveTalon1.set(ControlMode.PercentOutput, 0);
		
		leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		
		intakeRight.setInverted(false);
		intakeLeft.setInverted(true);
		
		intakeSolenoid.set(DoubleSolenoid.Value.kForward);
	}
}

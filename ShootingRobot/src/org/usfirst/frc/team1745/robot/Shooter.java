/**
 * 
 */
package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * @author dg331474
 *
 */
public class Shooter {

	/**
	 */
	private CameraChecker checker;
	private P51Talon lMotor,rMotor, aMotor;
	private Arm arm;
	private BallDetector ballDetector;
	public enum Mode{INTAKE, SHOOTING, OFF}; // 3 ways that the shooting wheels can be enabled
	private Mode mode;// the mode currently selected
	private double wheelRadius; // radius of the wheel ( used for linear speed calculation)
	private double encoderClicksPerTurn;// encoder clicks per rotation; 
	public double getWheelRadius() {
		return wheelRadius;
	}
	public double getEncoderClicksPerTurn() {
		return encoderClicksPerTurn;
	}
	private double speed; // the speed of the wheels
	private double shootSpeed; // the speed when in shooting mode
	private double intakeSpeed; // the speed the wheels go when intaking
	public Shooter() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param lMotor
	 * @param rMotor
	 */
	public Shooter(P51Talon lMotor, P51Talon rMotor, P51Talon aMotor, BallDetector ballDetector) {
		super();
		this.lMotor = lMotor;
		this.rMotor = rMotor;
		this.aMotor = aMotor;
		this.ballDetector = ballDetector;
		this.encoderClicksPerTurn = 1024;
		this.wheelRadius = 4;
		lMotor.changeControlMode(CANTalon.TalonControlMode.Speed); // set talons to be set by speed
        rMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        rMotor.reverseSensor(true);// reverse the Right motor sensor
        rMotor.reverseOutput(true); //reverse the right motor output
        lMotor.enableBrakeMode(true); //enable fast breaking
        rMotor.enableBrakeMode(true);
        this.setMode(Mode.OFF);
        arm = new Arm(this.aMotor);
        
	}
	/**
	 * @return Distance Per Encoder Click in Inches
	 */
	public double getLinearDistancePerClick()
	{
		return this.getWheelRadius() / this.getEncoderClicksPerTurn();
	}
	/**
	 * @return Returns the Current Linear Speed of the Shooter Wheels in Inches per second. 
	 */
	public double getLinearSpeed()
	{
		return this.getSpeed()*this.getWheelRadius()/60;
	}
	/**
	 * @return Return the 
	 */
	public double getRPM()
	{
		return this.getSpeed();
	}
	/**
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
		switch(this.mode) {
		case INTAKE:
			this.setSpeed(this.getIntakeSpeed());
			break;
		case OFF:
			this.setSpeed(0);
			break;
		case SHOOTING:
			this.setSpeed(this.getShootSpeed());
			break;
		default:
			this.setSpeed(0);
			break;
		
		}
		
	}
	/**
	 * @return the shootSpeed
	 */
	public double getShootSpeed() {
		return shootSpeed;
	}
	/**
	 * @param shootSpeed the shootSpeed to set
	 */
	public void setShootSpeed(double shootSpeed) {
		this.shootSpeed = shootSpeed;
		if(this.getMode() == Mode.SHOOTING)
		{
			this.setSpeed(shootSpeed);
		}
	}
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return this.speed;
	}
	/**
	 * @param speed the speed to set
	 */
	private void setSpeed(double speed) {
		// look at http://www.ctr-electronics.com/Talon%20SRX%20Software%20Reference%20Manual.pdf for Speed documentation
		if(speed < -1) speed = -1; // prevent out of bounds on Speed
		if(speed > 1) speed =1;
		this.speed = speed;
		lMotor.set(this.speed);
		rMotor.set(-this.speed);
	}
	/**
	 * @return the intakeSpeed
	 */
	public double getIntakeSpeed() {
		return intakeSpeed;
		
	}
	/**
	 * @param intakeSpeed the intakeSpeed to set
	 */
	public void setIntakeSpeed(double intakeSpeed) {
		this.intakeSpeed = intakeSpeed;
		if(this.getMode() == Mode.INTAKE)
		{
			this.setSpeed(intakeSpeed);
		}
	}
	
	/**
	 * @return the left motor Velocity
	 */
	public double getLeftSensorSpeed() {
		return lMotor.getEncVelocity();		
	}
	/**
	 * @return the right motor Velocity
	 */
	public double getRightSensorSpeed() {
		return rMotor.getEncVelocity();		
	}
	
	/**
	 *  Set the Robot to use the Ball detector to switch modes
	 */
	public void autoGather()
	{
		//if the ball is detected change to shooting mode, if its not set to intake mode.
		if(this.ballDetector.getBallStatus())
		{
			this.setMode(Mode.OFF);
		}
		else
		{	
			this.setMode(Mode.INTAKE);
		}
	}
	
	
	
	

}

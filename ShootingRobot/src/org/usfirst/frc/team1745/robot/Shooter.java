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
	 * 
	 */
	CANTalon lMotor,rMotor;
	private enum Mode{INTAKE, SHOOTING, OFF};
	private Mode mode;
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
	public Shooter(CANTalon lMotor, CANTalon rMotor) {
		super();
		this.lMotor = lMotor;
		this.rMotor = rMotor;
		lMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        rMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        lMotor.enableBrakeMode(true);
        rMotor.enableBrakeMode(true);
        this.setMode(Mode.OFF);
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
	}
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	private void setSpeed(double speed) {
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
	}
	
	
	
	
	

}

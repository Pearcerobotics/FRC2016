package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Developer
 *
 */
public class PneumaticsSystem {

	Compressor compressor;
	DoubleSolenoid shooterPiston;
	PressureSensor lowPressureSensor;

	/**
	 * @param shooterPiston1
	 * @param PCMCanID
	 * @param lowPressureSensor
	 */
	public PneumaticsSystem(int reversePistonID, int forwardPistonID, int PCMCanID, int lowPressureSensor) {
		this.shooterPiston = new DoubleSolenoid(reversePistonID, forwardPistonID);
		this.compressor = new Compressor(PCMCanID);
		this.compressor.setClosedLoopControl(true);
		this.lowPressureSensor = new PressureSensor(lowPressureSensor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param targetPosition
	 */
	public void moveSolenoid(DoubleSolenoid.Value targetPosition) {
		this.shooterPiston.set(targetPosition);
		SmartDashboard.putString("Target Position", targetPosition.toString());
		SmartDashboard.putString("Piston", this.shooterPiston.get().toString());
	}
		

	/**
	 * @return position of the solenoid
	 */
	public DoubleSolenoid.Value returnPositionSolenoid() {
		return this.shooterPiston.get();
	}

	/**
	 * @return the system pressure
	 */
	public double getSystemPressure() {
		return this.lowPressureSensor.getPressure();
	}

	public String toString() {
		return "State of compressor:" + this.compressor.getClosedLoopControl() + "Active Pressure"
				+ this.getSystemPressure();
	}

	public void toDashBoard() {
		SmartDashboard.putBoolean("Compressor State", this.compressor.getClosedLoopControl());
		SmartDashboard.putBoolean("Pressure Switch Value", this.compressor.getPressureSwitchValue());
		SmartDashboard.putNumber("Active System Pressure", this.getSystemPressure());
		
	}

	public void startCompressor() {
		if (!this.compressor.getClosedLoopControl()) {
			this.compressor.setClosedLoopControl(true);
		}
	}
}

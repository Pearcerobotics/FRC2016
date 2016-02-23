package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticsSystem {

	Compressor compressor;
	DoubleSolenoid shooterPiston;
	PressureSensor lowPressureSensor;

	public PneumaticsSystem(DoubleSolenoid shooterPiston1, int PCMCanID, int lowPressureSensor) {
		this.shooterPiston = shooterPiston1;
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
	}
	/**
	 * @return position of the solenoid
	 */
	public DoubleSolenoid.Value returnPositionSolenoid() {
			return this.shooterPiston.get();
	}
	
	public double getSystemPressure()
	{
		return this.lowPressureSensor.getPressure();
	}
	
	public String toString() {
		return "State of compressor:"+this.compressor.getClosedLoopControl()+"Active Pressure"+this.getSystemPressure();
	}
	public void toDashBoard() {
		SmartDashboard.putBoolean("Compressor State", this.compressor.getClosedLoopControl());
		SmartDashboard.putNumber("Active System Pressor", this.getSystemPressure());
	}
	public void startCompressor()
	{
		if(!this.compressor.getClosedLoopControl())
		{
			this.compressor.setClosedLoopControl(true);
		}
	}
}

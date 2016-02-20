/**
 * 
 */
package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author robots
 *
 */
public class PressureSensor extends AnalogInput {

	/**
	 * @param channel
	 */
	public PressureSensor(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}
	
	public double getPressure()
	{
		double pressure;
		double voltage = 5;
		pressure = 250 *( this.getAverageVoltage()/voltage) -25;
		return pressure;
		
	}
	
	public String toString()
	{
		return "Pressure Sensor " + this.getChannel() + " pressure:" + this.getPressure();
	}
	public void toDashboard()
	{
		SmartDashboard.putNumber("PressureSensor_"+this.getChannel()+"_Pressure", this.getPressure());
	}
	
	

}

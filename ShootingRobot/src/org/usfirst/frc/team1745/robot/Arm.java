package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Arm {
private CANTalon arm; 
private double currentPos = 0;
private double targetPos;
private boolean beenReset;
private double voltage;
private double current;
	public Arm(P51Talon inputArm) {
		arm=inputArm;
		arm.changeControlMode(CANTalon.TalonControlMode.Position);
		beenReset = false;
		
		// TODO Auto-generated constructor scrub
	}
	public void setPos(double pos)//sets the position of the arm
	{
		if(!beenReset) return;//Make sure to do this for all methods in arm
		arm.setPosition(pos);
		currentPos += pos;
	}
	public double getPos()//gets the position of the arm
	{
		return arm.getPosition(); 
	}
	private void setPosToDefault()//sets the position of the arm to the default position
	{
		arm.setPosition(-currentPos);
		currentPos = 0;
	}

}

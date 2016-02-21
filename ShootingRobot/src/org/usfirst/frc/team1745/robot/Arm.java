package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Stuart
 *
 */
public class Arm {
private P51Talon arm; 
private double currentPos = 0;
private double targetPos;
private boolean beenReset;
private double voltage;
private double current;
private double lowRange=0;
private double highRange=1;
	public Arm(P51Talon aMotor) {
		arm=aMotor;
		arm.changeControlMode(CANTalon.TalonControlMode.Position);
		arm.enableBrakeMode(true);//enabling break mode
		beenReset = false;
		int absolutePosition = arm.getPulseWidthPosition() & 0xFFF;     
        arm.setEncPosition(absolutePosition);
        arm.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        arm.reverseSensor(false);
        //set soft limits to be in the allowable range
        arm.setForwardSoftLimit(lowRange);
        arm.setReverseSoftLimit(highRange);
        /* set the peak and nominal outputs, 12V means full */
        arm.configNominalOutputVoltage(+0f, -0f);
        arm.configPeakOutputVoltage(+12f, -12f);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         */
        arm.setAllowableClosedLoopErr(0); /* always servo */
        /* set closed loop gains in slot0 */
        arm.setProfile(0);
        arm.setF(0.0);
        arm.setP(0.1);
        arm.setI(0.0); 
        arm.setD(0.0);  
		SmartDashboard.putNumber("armP", 0.1);
		SmartDashboard.putNumber("armF", 0.0);
		SmartDashboard.putNumber("armI", 0.0);
		SmartDashboard.putNumber("armD", 0.0);
		
		// TODO Auto-generated constructor scrub
	}
	
	
	/**
	 * @param pos takes input -1 to 1 and adujests it to the lowRange and highRange
	 */
	public void setPos(double pos)//sets the position of the arm
	{
		pos = pos +1; //range is now 0-2
		pos = pos / 2; //range is now 0-1
		pos = pos * (this.highRange - this.lowRange) +this.lowRange; //range is now lowRange - highRange
		
		this.targetPos = pos;
		//arm.changeControlMode(TalonControlMode.Position);
		arm.set(targetPos);
	}
	public void setRange(double lowRange, double highRange)
	{
		this.lowRange=lowRange;
		this.highRange=highRange;
		arm.setForwardSoftLimit(this.lowRange);
		arm.setReverseSoftLimit(this.highRange);
	}
	public double getPos()//gets the position of the arm
	{
		return arm.getPosition(); 
	}
	public void setControl()
	{
		arm.setP(SmartDashboard.getNumber("armP"));
		arm.setI(SmartDashboard.getNumber("armI"));
		arm.setD(SmartDashboard.getNumber("armD"));
		SmartDashboard.putNumber("armPosition", arm.getPosition());
		SmartDashboard.putNumber("armTargetPosition", arm.getSetpoint());
	}

}

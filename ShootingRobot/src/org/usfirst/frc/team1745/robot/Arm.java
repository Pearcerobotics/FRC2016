package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
private P51Talon arm; 
private double currentPos = 0;
private double targetPos;
private boolean beenReset;
private double voltage;
private double current;
	public Arm(P51Talon aMotor) {
		arm=aMotor;
		arm.changeControlMode(CANTalon.TalonControlMode.Position);
		beenReset = false;
		int absolutePosition = arm.getPulseWidthPosition() & 0xFFF;     
        arm.setEncPosition(absolutePosition);
        arm.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        arm.reverseSensor(false);
        /* set the peak and nominal outputs, 12V means full */
        arm.configNominalOutputVoltage(+0f, -0f);
        arm.configPeakOutputVoltage(+12f, -12f);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
        arm.setAllowableClosedLoopErr(0); /* always servo */
        /* set closed loop gains in slot0 */
        arm.setProfile(0);
        arm.setF(0.0);
        arm.setP(0.1);
        arm.setI(0.0); 
        arm.setD(0.0);  
		
		
		// TODO Auto-generated constructor scrub
	}
	public void setPos(double pos)//sets the position of the arm
	{
		targetPos = pos;
		arm.changeControlMode(TalonControlMode.Position);
		arm.set(targetPos);
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
	public void setControl()
	{
		arm.setP(SmartDashboard.getNumber("armP"));
		arm.setI(SmartDashboard.getNumber("armI"));
		arm.setD(SmartDashboard.getNumber("armD"));
	}

}

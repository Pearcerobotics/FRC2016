package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallDetector {

	private DigitalInput beamSensor;
	
	/**
	 * @param DIOPort the DIOport of the Ball Detector 
	 */
	public BallDetector(int DIOPort) {
		
		beamSensor = new DigitalInput(DIOPort);
	}
	
	/**
	 * @return Returns if there is a ball detected
	 */
	public boolean getBallStatus(){
		return (beamSensor.get());
	}
	/**
	 * @return Returns the DIO port of the Ball Detector
	 */
	public int getBallDetectorPort(){
		return beamSensor.getChannel();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BallDetector [getBallStatus()=" + getBallStatus() + ", getBallDetectorPort()=" + getBallDetectorPort()
				+ "]";
	}

	public void toDashboard() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("BallDetected", getBallStatus());
		
	}
}

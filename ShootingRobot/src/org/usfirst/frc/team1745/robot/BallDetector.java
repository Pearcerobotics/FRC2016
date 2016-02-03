package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class BallDetector {

	private DigitalInput beamSensor;
	
	public BallDetector(int x) {
		// TODO Auto-generated constructor stub
		beamSensor = new DigitalInput(x);
	}
	
	public boolean getBallStatus(){
		return (beamSensor.get());
	}
}

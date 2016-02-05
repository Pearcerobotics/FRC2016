/**
 * 
 */
package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Sendable;

/**
 * @author robots
 *
 */
public class P51Talon extends CANTalon implements Sendable {

	/**
	 * @param deviceNumber
	 */
	
	private String myName;
	private int myPdpPort;
	public enum Motors{WC775Pro, miniCIM, CIM};
	private Motors myMotor;
	public enum Breakers{amp40, amp20, amp30};
	private Breakers myBreaker;
	
	public P51Talon(int deviceNumber) {
		super(deviceNumber);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param deviceNumber
	 * @param controlPeriodMs
	 */
	public P51Talon(int deviceNumber, int controlPeriodMs) {
		super(deviceNumber, controlPeriodMs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param deviceNumber
	 * @param controlPeriodMs
	 * @param enablePeriodMs
	 */
	public P51Talon(int deviceNumber, int controlPeriodMs, int enablePeriodMs) {
		super(deviceNumber, controlPeriodMs, enablePeriodMs);
		// TODO Auto-generated constructor stub
	}
	
	public P51Talon(int deviceNumber, String nameV, Motors motorV, Breakers breakerV, int PDPport) {
		super(deviceNumber);
		myName=nameV;
		myMotor=motorV;
		myBreaker = breakerV;
		myPdpPort = PDPport;
		
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.Sendable#getSmartDashboardType()
	 */
	@Override
	public String getSmartDashboardType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		return "Hello World";
	}

}

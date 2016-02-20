
package org.usfirst.frc.team1745.robot;

import java.io.IOException;

import org.usfirst.frc.team1745.robot.P51Talon.Breakers;
import org.usfirst.frc.team1745.robot.P51Talon.Motors;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    P51Talon fRight, bRight, fLeft, bLeft, lShooter, rShooter, aMotor;
    Shooter myShooter;
    Joystick lJoystick, rJoystick, sJoystick;
    RobotDrive myRobot;
    BallDetector ballDetector;
    private final NetworkTable grip = NetworkTable.getTable("grip");
    Arm arm;

   
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        fRight = new P51Talon(3, "Forward Right Motor", Motors.CIM, Breakers.amp40, 3);
        bRight = new P51Talon(4, "Back Right Motor", Motors.CIM, Breakers.amp40, 2 );
        fLeft = new P51Talon(5, "Forward Left Motor", Motors.CIM, Breakers.amp40, 1);
        bLeft = new P51Talon(6, "Back Left Motor", Motors.CIM, Breakers.amp40, 0);
        lShooter = new P51Talon(7,"Left Shooter", Motors.miniCIM, Breakers.amp40, 12);
        rShooter = new P51Talon(8, "Right Shooter", Motors.miniCIM, Breakers.amp40,13);
        aMotor = new P51Talon(9, "Arm Motor", Motors.WC775Pro, Breakers.amp40,14);
        lJoystick = new Joystick(0);
        rJoystick = new Joystick(1);
        sJoystick = new Joystick(2);
        ballDetector = new BallDetector(0);
        myRobot = new RobotDrive(bLeft, fLeft, bRight, fRight);
        myShooter = new Shooter(lShooter, rShooter, aMotor, ballDetector);
        arm = new Arm(aMotor);
        myShooter.setIntakeSpeed(-.1);
        myShooter.setShootSpeed(1);
        //turn on Grip image processing
        try {
            new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
        
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    	this.talonsToDashboard();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        myRobot.tankDrive(rJoystick, lJoystick, true);
       
        
        myShooter.setShootSpeed(lJoystick.getAxis(AxisType.kThrottle));
        
        //gather ball dumb
        if(lJoystick.getTrigger(null))
        {
        	myShooter.setMode(Shooter.Mode.SHOOTING);
        }
        else
        {
        	myShooter.setMode(Shooter.Mode.INTAKE);
        }
        //shoot ball dumb
        if(rJoystick.getTrigger(null))
        {
        	myShooter.shoot();
        }
        else
        {
        	myShooter.retract();;
        }
        //dumb arm control
        arm.setPos((rJoystick.getAxis(AxisType.kThrottle)+1)/8);
        arm.setControl();
        this.talonsToDashboard();
                
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
       	myRobot.tankDrive(lJoystick, rJoystick, true);
       // myShooter.setMode(Shooter.Mode.INTAKE);
        myShooter.setShootSpeed(sJoystick.getAxis(AxisType.kY));
        if(sJoystick.getTrigger(null))
        {
        	myShooter.setMode(Shooter.Mode.SHOOTING);
        }
    	arm.setPos(SmartDashboard.getNumber("armPosition", 0.0));
        arm.setControl();
        this.talonsToDashboard();
    }
    public void talonsToDashboard()
    {
    	fRight.toDashboard();
    	bRight.toDashboard();
    	fLeft.toDashboard();
    	bLeft.toDashboard();
    	lShooter.toDashboard();
    	rShooter.toDashboard();
    	aMotor.toDashboard();
    }
    
}

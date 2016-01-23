
package org.usfirst.frc.team1714.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

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
    Encoder encoder;
	Joystick stick;
    TalonSRX talon;
    double distance;
    boolean direction;
    Relay relay;
    
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        encoder = new Encoder(3,4);
    	stick = new Joystick(0);
    	talon = new TalonSRX(0);
    	encoder.reset();
    	relay = new Relay(0);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
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
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	encoder.getDistance();
    	encoder.getDirection();
        if(stick.getRawButton(1)){
        	if(encoder.getDistance()<100){
        		talon.set(1);
        	}
        	else if(encoder.getDistance()<300 && encoder.getDistance()>100){
        		talon.set(-1);
        	}
        	else if(encoder.getDistance()>300 && encoder.getDistance()<1000){
        		talon.set(0.8);
        	}
        	else if(encoder.getDistance()>1000 && encoder.getDistance()<2000){
        		talon.set(-0.8);
        	}
        	if(encoder.getDirection()){
        		relay.set(Relay.Value.kForward);
        	}
        	else{
        		relay.set(Relay.Value.kReverse);
        	}
        		
        }
        System.out.println(encoder.getDistance());
    }
    //4 stage of moving forward and backward. the light indicate the direction of the motor(on=forward)
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

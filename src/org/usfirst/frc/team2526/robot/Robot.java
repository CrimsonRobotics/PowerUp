/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2526.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2526.robot.subsystems.Intake;
import org.usfirst.frc.team2526.robot.subsystems.Pneumatics;
import org.usfirst.frc.team2526.robot.commands.DriveStraight;
import org.usfirst.frc.team2526.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2526.robot.subsystems.Elevator;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static final Elevator elevator = new Elevator(RobotMap.ELEVATOR_RIGHT,RobotMap.ELEVATOR_LEFT,RobotMap.LIMIT_ELEVATOR_T,RobotMap.LIMIT_ELEVATOR_B);
	public static final DriveTrain driveTrain = new DriveTrain(RobotMap.DRIVETRAIN_BACKLEFT,RobotMap.DRIVETRAIN_BACKRIGHT,RobotMap.DRIVETRAIN_FRONTLEFT,RobotMap.DRIVETRAIN_FRONTRIGHT,4);	
	public static final Pneumatics pneumatics = new Pneumatics(2,3,0,1,4,5);
	SerialPort serial = new SerialPort(9600, SerialPort.Port.kMXP,8);
	public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
	/*
	DigitalInput input; 
	DigitalInput input1;
	DigitalInput input2;*/
	public static final Intake intake = new Intake(RobotMap.INTAKE_LEFT,RobotMap.INTAKE_RIGHT);
	
	public static OI m_oi;
		Command m_autonomousCommand = new DriveStraight();
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		serial.writeString("Purple");	
		/*input = new DigitalInput(0);data
		input1 = new DigitalInput(1);
		input2 = new DigitalInput(2);*/

		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		CameraServer.getInstance().startAutomaticCapture("GearCamera", "/dev/video0").setResolution(768, 432);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}


	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		/*SmartDashboard.putBoolean("Hoo", !input.get());
		 SmartDashboard.putBoolean("Haa", !input1.get());
		 SmartDashboard.putBoolean("Hee", !input2.get());*/
		Scheduler.getInstance().run();
		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

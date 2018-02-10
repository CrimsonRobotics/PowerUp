/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2526.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	
	
	public static final int DRIVETRAIN_FRONTLEFT = 2;
	public static final int DRIVETRAIN_FRONTRIGHT = 7;
	public static final int DRIVETRAIN_BACKLEFT = 1;
	public static final int DRIVETRAIN_BACKRIGHT = 8;
	public static final PID DRIVETRAIN_GAINS_LEFT = new PID(1, 0, 0, .7, 0, 0, 0);
	public static final PID DRIVETRAIN_GAINS_RIGHT = new PID(0, 0, 0, .8, 0, 0, 0);
	
	public static int INTAKERIGHT = 4;
	public static int INTAKELEFT = 5;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

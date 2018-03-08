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
	
	public static final int DRIVETRAIN_FRONTLEFT = 1;
	public static final int DRIVETRAIN_FRONTRIGHT = 2;
	public static final int DRIVETRAIN_BACKLEFT = 7;
	public static final int DRIVETRAIN_BACKRIGHT = 8;
	public static final int ELEVATOR_RIGHT = 6;
	public static final int ELEVATOR_LEFT = 3;
	public static final int INTAKE_RIGHT = 4;
	public static final int INTAKE_LEFT = 5;
	public static final int LIMIT_ELEVATOR_B =0;
	public static final int LIMIT_ELEVATOR_T =1;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static final int DS_L_ONE = 1; //Double Solenoid Left
	public static final int DS_L_TWO = 2; //Double Solenoid Left ONE DS
//	public static final int DS_R_ONE = 3; //Double Solenoid Right
//	public static final int DS_R_TWO = 4; //Double Solenoid Right
	public static final int SS_P = 3; //Single Solenoid Plate
	public static final int D_G_S = 5; //Drop Gear Sensor
}

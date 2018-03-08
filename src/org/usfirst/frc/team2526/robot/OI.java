/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2526.robot;

import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team2526.robot.commands.AdjustCubeLeft;
import org.usfirst.frc.team2526.robot.commands.AdjustCubeRight;
import org.usfirst.frc.team2526.robot.commands.CarriageClose;
import org.usfirst.frc.team2526.robot.commands.DropCarriage;
import org.usfirst.frc.team2526.robot.commands.ElevatorButton;
import org.usfirst.frc.team2526.robot.commands.ElevatorControl;
import org.usfirst.frc.team2526.robot.commands.GearIn;
import org.usfirst.frc.team2526.robot.commands.GearStop;
import org.usfirst.frc.team2526.robot.commands.IntakeOut;
import org.usfirst.frc.team2526.robot.commands.IntakeStack;
import org.usfirst.frc.team2526.robot.commands.ShiftDrive;
import org.usfirst.frc.team2526.robot.commands.ShiftElevator;
import org.usfirst.frc.team2526.robot.commands.ShootCubeAdjust;
import org.usfirst.frc.team2526.robot.commands.StopElevator;
import org.usfirst.frc.team2526.robot.commands.UpIntake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick driverRight = new Joystick(1);
	private Joystick driverLeft = new Joystick(0);
	private Joystick coDriver = new Joystick(2);
	private JoystickButton shiftDrive = new JoystickButton(driverLeft,3);
	private JoystickButton gearIn = new JoystickButton(coDriver,1);
	private JoystickButton cubeStack = new JoystickButton(coDriver,2);
	private JoystickButton intakeControl = new JoystickButton(coDriver,3);
	private JoystickButton gearOut = new JoystickButton(coDriver,4);
	private JoystickButton adjustCubeLeft = new JoystickButton(coDriver,5);
	private JoystickButton adjustCubeRight = new JoystickButton(coDriver,6);
	private JoystickButton elevatorShift = new JoystickButton(coDriver,7);
	private JoystickButton elevatorClose = new JoystickButton(coDriver,8);
	//private JoystickButton moveElevatorTop = new JoystickButton(coDriver,12);
	private JoystickButton dropCarriage = new JoystickButton(coDriver,12);
	
	//private JoystickButton stopElevator = new JoystickButton(coDriver,7);
	//private JoystickPOV elevatorUp = new JoystickButton(coDriver,6);
	//private JoystickButton adjustCube2 = new JoystickButton(coDriver,5);
	public OI() {
		shiftDrive.whileHeld(new ShiftDrive());
		gearIn.whileHeld(new GearIn());
		gearIn.whenReleased(new GearStop());
		gearOut.whileHeld(new IntakeOut());
		gearOut.whenReleased(new GearStop());
		adjustCubeLeft.whileHeld(new AdjustCubeLeft());
		adjustCubeLeft.whenReleased(new GearStop());
		adjustCubeRight.whileHeld(new AdjustCubeRight());
		adjustCubeRight.whenReleased(new GearStop());
		dropCarriage.whenPressed(new DropCarriage());
		elevatorShift.whenPressed(new ShiftElevator());
		elevatorClose.whenPressed(new CarriageClose());
		intakeControl.whenPressed(new UpIntake());
		cubeStack.whileHeld(new IntakeStack());
		cubeStack.whenReleased(new GearStop());
		}
	//	moveElevatorTop.whenPressed(new ElevatorButton());
	//	stopElevator.whenPressed(new StopElevator());
		//adjustCube2.whenPressed(new ShootCubeAdjust());
	
	
	public Joystick getCoDriver(){
		return coDriver;
	}
	public Joystick getDriverRight() {
		return driverRight;
	}
	public Joystick getDriverLeft() {
		return driverLeft;
	}


	
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	
	
}

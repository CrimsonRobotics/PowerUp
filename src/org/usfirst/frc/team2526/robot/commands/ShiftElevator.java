/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2526.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2526.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class ShiftElevator extends Command {
	public ShiftElevator() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.pneumatics);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.pneumatics.elevatorShiftUp();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.pneumatics.elevatorShiftDown();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.pneumatics.elevatorShiftDown();
	}
}

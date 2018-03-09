package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GoForward extends Command {

	int rightTarget;
	int leftTarget;
	boolean isDone = false;
	
    public GoForward(double time) {
    	super(time);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }
  

    // Called just before this Command runs the first time
    protected void initialize() {	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.fL.set(ControlMode.PercentOutput,.6);
		Robot.driveTrain.fR.set(ControlMode.PercentOutput,-.6);
		//Timer.delay(20);
		//isDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	/*if (isDone){
    		return true;
    	}
    	*///Timer.delay(20);
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.fL.set(ControlMode.PercentOutput,0);
		Robot.driveTrain.fR.set(ControlMode.PercentOutput,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.fL.set(ControlMode.PercentOutput,0);
		Robot.driveTrain.fR.set(ControlMode.PercentOutput,0);
    }
}

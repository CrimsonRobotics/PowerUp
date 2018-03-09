package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnLeft extends Command {

	int rightTarget;
	int leftTarget;
	
    public TurnLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }
  

    // Called just before this Command runs the first time
    protected void initialize() {	
    	leftTarget = 1000+Robot.driveTrain.fL.getSensorCollection().getQuadraturePosition();
    	rightTarget = 1000+Robot.driveTrain.fR.getSensorCollection().getQuadraturePosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.fL.set(ControlMode.Position,leftTarget);
    	Robot.driveTrain.fR.set(ControlMode.Position,rightTarget);
    	SmartDashboard.putNumber("Encoder Left", Robot.driveTrain.fL.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Encoder Right", Robot.driveTrain.fR.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Encoder Right Target", rightTarget);
    	SmartDashboard.putNumber("Encoder Right Distance To Target", Math.abs(Robot.driveTrain.fR.getSensorCollection().getQuadraturePosition()-rightTarget));
    	SmartDashboard.putNumber("Encoder Left Distance To Target", Math.abs(Robot.driveTrain.fL.getSensorCollection().getQuadraturePosition()-leftTarget));

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(Robot.driveTrain.fL.getSensorCollection().getQuadraturePosition()-leftTarget) < 20){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDrive extends Command {

	int rightTarget;
	int leftTarget;
	int leftDistance;
	int rightDistance;
	int leftChange;
	int rightChange;
	int accuracy;
	double power;
	double leftPower;
	double rightPower;
	int leftAccumulator = 0;
	int rightAccumulator = 0;
	int prevPositionLeft;
	int prevPositionRight;
	//int range;
	double range;
	double targetAngle;
	boolean willSlow;
	
	WPI_TalonSRX encoderTalonLeft = Robot.driveTrain.bL;
	WPI_TalonSRX encoderTalonRight = Robot.driveTrain.fR;
	
    public AutoDrive(int leftChange,int rightChange,int accuracy, double power, boolean willSlow) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.leftChange = leftChange;
    	this.rightChange = -rightChange;
    	this.accuracy = accuracy;
    	this.power = power;
    	leftPower = power;
    	rightPower = power;
    	this.willSlow = willSlow;
    }
  

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftTarget = leftChange+encoderTalonLeft.getSensorCollection().getQuadraturePosition();
    	rightTarget = rightChange+encoderTalonRight.getSensorCollection().getQuadraturePosition();
    	SmartDashboard.putString("Correcting Action", "None");
    	targetAngle = Robot.gyro.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	leftPower = power;
    	rightPower = power;
    	
    	rightDistance = encoderTalonRight.getSensorCollection().getQuadraturePosition()-rightTarget;
    	leftDistance = encoderTalonLeft.getSensorCollection().getQuadraturePosition()-leftTarget;

    	//SmartDashboard.putNumber("rightAccumulator", rightAccumulator);
    	SmartDashboard.putString("Slowing?", "false");
    	
    	if (willSlow){
	    	SmartDashboard.putNumber(".5*leftChange", .5*leftChange);
	    	SmartDashboard.putNumber(".5*rightChange", .5*rightChange);
	    	if (Math.abs(leftDistance) < Math.abs(.20*leftChange) && Math.abs(rightDistance) < Math.abs(.15*rightChange)){
	    		leftPower*=.3;
	    		rightPower*=.3;
	        	SmartDashboard.putString("Slowing?", "true");
	    	}else if (Math.abs(leftDistance) < Math.abs(.40*leftChange) && Math.abs(rightDistance) < Math.abs(.33*rightChange)){
	    		leftPower*=.8;
	    		rightPower*=.8;
	        	SmartDashboard.putString("Slowing?", "true");
	    	}
    	}
    	
    	
    	
    	
    	/*leftAccumulator += Math.abs(encoderTalonLeft.getSensorCollection().getQuadraturePosition()-prevPositionLeft);
    	rightAccumulator += Math.abs(encoderTalonRight.getSensorCollection().getQuadraturePosition()-prevPositionRight);
    	SmartDashboard.putNumber("leftAccumulator", leftAccumulator);
    	SmartDashboard.putNumber("rightAccumulator", rightAccumulator);
    	SmartDashboard.putString("Correcting Action", "None");
    	
    	range = 50;
    	if (leftAccumulator > rightAccumulator+range){
    		leftPower*=.8;
        	SmartDashboard.putString("Correcting Action", "Too Far Right");
    	}else if (rightAccumulator > leftAccumulator+range){
    		rightPower*=.8;
        	SmartDashboard.putString("Correcting Action", "Too Far Left");
    	}
    	
    	SmartDashboard.putNumber("leftPower", leftPower);
    	SmartDashboard.putNumber("rightPower", rightPower);*/
    	
    	
    	range = 1;
    	if (Robot.gyro.getAngle() > targetAngle+range){
    		rightPower*=.8;
        	SmartDashboard.putString("Correcting Action", "Too Far Right");
    	}else if (Robot.gyro.getAngle() < targetAngle-range){
    		leftPower*=.8;
        	SmartDashboard.putString("Correcting Action", "Too Far Left");
    	}			

    	if (rightDistance > 0){
    		Robot.driveTrain.fR.set(ControlMode.PercentOutput,-rightPower);
    	}else if (rightDistance < 0){
    		Robot.driveTrain.fR.set(ControlMode.PercentOutput,rightPower);
    	}
    	if (leftDistance > 0){
    		Robot.driveTrain.fL.set(ControlMode.PercentOutput,-leftPower);
    	}else if (leftDistance < 0){
    		Robot.driveTrain.fL.set(ControlMode.PercentOutput,leftPower);
    	}
    	
    	
    	
    	SmartDashboard.putNumber("Encoder Left", encoderTalonLeft.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Encoder Right", encoderTalonRight.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Encoder Front Left", Robot.driveTrain.fL.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Encoder Front Right", Robot.driveTrain.fR.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Encoder Back Left", Robot.driveTrain.bL.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Encoder Back Right", Robot.driveTrain.bR.getSensorCollection().getQuadraturePosition());
    	SmartDashboard.putNumber("Encoder Right Target", rightTarget);
    	SmartDashboard.putNumber("Encoder Right Distance To Target", rightDistance);
    	SmartDashboard.putNumber("Encoder Left Distance To Target", leftDistance);

    	prevPositionRight = encoderTalonLeft.getSensorCollection().getQuadraturePosition();
    	prevPositionLeft = encoderTalonRight.getSensorCollection().getQuadraturePosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((Math.abs(leftDistance) < accuracy)&&(Math.abs(rightDistance) < accuracy)){
        	Robot.driveTrain.fL.set(ControlMode.PercentOutput,0);
    		Robot.driveTrain.fR.set(ControlMode.PercentOutput,0);
    		return true;
    	}else if (Math.abs(leftDistance) < accuracy){
        	Robot.driveTrain.fL.set(ControlMode.PercentOutput,0);
    	}else if (Math.abs(rightDistance) < accuracy){
        	Robot.driveTrain.fR.set(ControlMode.PercentOutput,0);
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

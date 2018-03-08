/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2526.robot.commands;

import java.io.File;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

/**
 *
 */
public class MotionProfileDriver extends Command {
	private Trajectory trajectoryLeft; //Left wheel speed
	private Trajectory trajectoryRight; //Right wheel speed
	private Trajectory trajectoryCenter; //Used for determining heading
	private int iterator = 0; //Used for iteration and determining when to end command.
	private boolean finished = false;
	private double CORRECTION_FACTOR = 1;
	private double inchesPerMeter = 39.37;
	private double wheelInches;
	
	public MotionProfileDriver(String pathLeft, String pathRight, String pathCenter, double wheelInches){
		requires(Robot.driveTrain);
		File curveFileLeft = new File(pathLeft);
		File curveFileRight = new File(pathRight);
		File curveFileCenter = new File(pathCenter);
		trajectoryLeft = Pathfinder.readFromCSV(curveFileLeft);
		trajectoryRight = Pathfinder.readFromCSV(curveFileRight);
		trajectoryCenter = Pathfinder.readFromCSV(curveFileCenter);
		this.wheelInches = wheelInches;
	}
	public MotionProfileDriver(Trajectory trajectoryLeft, Trajectory trajectoryCenter, Trajectory trajectorRight, Trajectory trajectoryRight){
		this.trajectoryLeft = trajectoryLeft;
		this.trajectoryCenter = trajectoryCenter;
		this.trajectoryRight = trajectoryRight;
	}
	// Called just before this Command runs the first time
	@Override
	protected void initialize(){
		iterator = 0;
		finished = false;
		Robot.gyro.reset();
		Robot.gyro.calibrate();
	}
	@Override
	protected boolean isFinished(){
		// TODO Auto-generated method stub
		return finished;
	}

	/*@Override
	protected void execute() {
		double angle = Math.IEEEremainder(Robot.gyro.getAngle(), 360);
		double headingDegrees = 0;
		SmartDashboard.putNumber("Heading", headingDegrees);
		double headingError = headingDegrees - angle;
		
		double headingCorrection = headingError* CORRECTION_FACTOR;
		Robot.driveTrain.speedDrive(100+headingCorrection, 100+headingCorrection);
	}*/
	@Override
	protected void execute() {
		//double angle = (Robot.gyro.getAngle()%360.0);	
		double angle = Math.IEEEremainder(Robot.gyro.getAngle(), 360);
		//double angle = -90;
		//System.out.println(Robot.gyro.getRate());
		iterator += 1;
		if(iterator < trajectoryLeft.segments.length){
			double headingDegrees = trajectoryCenter.segments[iterator].heading * 180/Math.PI;
			SmartDashboard.putNumber("Heading", headingDegrees);
			double headingError = headingDegrees - angle;
			
			double headingCorrection = headingError* CORRECTION_FACTOR;
			//double headingCorrection = 0;
			SmartDashboard.putNumber("Heading Correction", headingCorrection);
			//double leftSpeed = trajectoryLeft.segments[iterator].velocity + headingCorrection;
			//double rightSpeed = trajectoryRight.segments[iterator].velocity - headingCorrection;
			double leftSpeed = calculateRPM(trajectoryLeft.segments[iterator].velocity)+ headingCorrection;// + headingCorrection
			double rightSpeed = calculateRPM(trajectoryRight.segments[iterator].velocity)- headingCorrection;// - headingCorrection
			Robot.driveTrain.speedDrive(leftSpeed, rightSpeed);
			//double leftSpeedVelocity = calculateVelocity(leftSpeed);
			//double rightSpeedVelocity = calculateVelocity(rightSpeed);
			//Robot.driveTrain.speedDrive(leftSpeedVelocity, rightSpeedVelocity);
			SmartDashboard.putNumber("Velocity", ((trajectoryRight.segments[iterator].velocity)));
			SmartDashboard.putBoolean("Finished", finished);
			SmartDashboard.putNumber("Iterator", iterator);
			Timer.delay(trajectoryLeft.segments[iterator].dt);
		}else{
			finished = true;
			SmartDashboard.putBoolean("Finished", finished);
			SmartDashboard.putNumber("Iterator", iterator);
		}
	}
	
	/*private double calculateRPM(double velocity){
		double inchesPerSecond = velocity * inchesPerMeter;
		double inchesPerRevolution = wheelInches * Math.PI;
		double rpm = (inchesPerSecond * 60) / inchesPerRevolution;
		return rpm;
	}*/
	private double calculateRPM(double velocity){
		SmartDashboard.putNumber("Pre-Velocity", velocity);
		double rpm = (60*velocity)/(2*Math.PI*2);//in meters
		return rpm;
	}
	
//	private double calculateVelocity(double rpm){
//		double velocity = ((2*Math.PI)/60)*2*rpm;
//		SmartDashboard.putNumber("Post-Velocity", velocity);
//		SmartDashboard.putNumber("RPM", rpm);
//		return velocity;
//	}
	/*private double calculateVelocity(double rpm){
		double inchesPerSecond = rpm/inchesPerMeter;
		double inchesPerRevolution = wheelInches * Math.PI;
		double velocity = (inchesPerSecond/60) * inchesPerRevolution;
		SmartDashboard.putNumber("Velocity", velocity);
		SmartDashboard.putNumber("RPM", rpm);
		return velocity;
	}*/
}

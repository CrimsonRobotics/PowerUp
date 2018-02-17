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
	private String whatToTune;
	
	static int countDown = 700;// Iterations until monitoring starts
	
	static int variable = 100;// The value to be analyzed

	static int checkTime = 50;// Number of iterations before calculating first standard deviations
	static int checkTimeTwo = 10;// Number of iterations of checkTime iterations before calculating second standard deviation

	static int[] array;// Values for first standard deviation
	static int[] newArray;// Each value corresponds to the square of the value of the same index in array 
						  // minus the mean of array(a step in the standard deviation process) 

	static float[] arrayTwo;// Standard deviation values gathered from array
	static float[] newArrayTwo;// Same as newArray but using floats derived from standard deviation values

	static int newCheck = checkTime;// The value counting down until the first standard deviation is calculated
	static int newCheckTwo = checkTimeTwo;// The value counting down until the second standard deviation is calculated

	static int total = 0;// An integer sum determined during the calculation of standard deviation
	static float totalTwo = 0;// A float sum determined during the calculation of standard deviation of the standard deviation

	static int mean = 0;// An integer average determined during the calculation of standard deviation
	static float meanTwo = 0;// A float average determined during the calculation of the standard deviation of the standard deviation

	static float deviation1 = 0;// The standard deviation for this iteration
	static float deviation2 = 0;// The standard deviation for this iteration of iterations
	
	static float bestValue = 9999;// Ex. a steadiness of oscillation of 4.1
	static double bestValueAmount = .0;// Ex. f = 1.25
	
	public MotionProfileDriver(String pathLeft, String pathRight, String pathCenter, double wheelInches){
		requires(Robot.driveTrain);
		File curveFileLeft = new File(pathLeft);
		File curveFileRight = new File(pathRight);
		File curveFileCenter = new File(pathCenter);
		trajectoryLeft = Pathfinder.readFromCSV(curveFileLeft);
		trajectoryRight = Pathfinder.readFromCSV(curveFileRight);
		trajectoryCenter = Pathfinder.readFromCSV(curveFileCenter);
		this.wheelInches = wheelInches;
		SmartDashboard.putString("What to tune?", "None");
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
		SmartDashboard.putBoolean("Finished", finished);
		SmartDashboard.putNumber("Iterator", iterator);

		whatToTune = SmartDashboard.getString("What to tune?", "None");
		
		array = new int[checkTime];
		arrayTwo = new float[checkTimeTwo];
		newArray = new int[checkTime];
		newArrayTwo = new float[checkTimeTwo];
		
		SmartDashboard.putNumber("Amount of Oscillation",0);
		SmartDashboard.putNumber("Steadiness of Oscillation",0);
		SmartDashboard.putNumber("Better f",-1);
		SmartDashboard.putNumber("Better p",-1);
		SmartDashboard.putNumber("Better i",-1);
		SmartDashboard.putNumber("Better d",-1);
		SmartDashboard.putString("Recomendation","Start Measuring");
		
		
	}
	@Override
	protected boolean isFinished(){
		// TODO Auto-generated method stub
		return finished;
	}
	@Override
	protected void execute() {
		Robot.driveTrain.speedDrive(25, 25);
		SmartDashboard.putNumber("Best Value",bestValue);
		switch(whatToTune){
			case"f": SmartDashboard.putNumber("Better f",bestValueAmount);
				break;
			case"p": SmartDashboard.putNumber("Better p",bestValueAmount);
				break;
			case"i": SmartDashboard.putNumber("Better i",bestValueAmount);
				break;
			case"d": SmartDashboard.putNumber("Better d",bestValueAmount);
				break;
		}
		
		SmartDashboard.putNumber("fPID", Robot.driveTrain.fPID);
		SmartDashboard.putNumber("pPID", Robot.driveTrain.pPid);
		SmartDashboard.putNumber("iPID", Robot.driveTrain.iPid);
		SmartDashboard.putNumber("dPID", Robot.driveTrain.dPid);
		//Timer.delay(2);
		//Robot.driveTrain.fPID = 
		if (countDown <= 0){
			SmartDashboard.putNumber("Speed",Robot.driveTrain.getSpeed());
			
			variable = Robot.driveTrain.getSpeed();// Update variable
			
			array[newCheck-1] = variable;// Add value to array
			newCheck--;
			
			if (newCheck == 0){// When time to calculate first standard deviation
				total = 0;
				for (int i=0; i < checkTime; i++){// Find sum of all values in array
					total += array[i];
				}
				mean = total/checkTime;
				total = 0;
				for (int i=0; i < checkTime; i++){
					newArray[i] = (array[i]-mean)*(array[i]-mean);
					total += newArray[i];
				}
				mean = total/checkTime;
				deviation1 = (float) Math.sqrt(mean);
	
				SmartDashboard.putNumber("Amount of Oscillation",deviation1);
				arrayTwo[newCheckTwo-1] = deviation1;	
				newCheckTwo--;
				if (newCheckTwo == 0){
					totalTwo = 0;
					for (int i=0; i < checkTimeTwo; i++){// Find sum of all values in arrayTwo
						totalTwo += arrayTwo[i];
						System.out.println(arrayTwo[i]);// Display all first standard deviations used in the 
														// calculation of the second standard deviation
					} 
					meanTwo = totalTwo/checkTimeTwo;
					totalTwo = 0;
					for (int i=0; i < checkTimeTwo; i++){
						newArrayTwo[i] = (arrayTwo[i]-meanTwo)*(arrayTwo[i]-meanTwo);
						totalTwo += newArrayTwo[i];
					}
					meanTwo = totalTwo/checkTimeTwo;
					deviation2 = (float) Math.sqrt(meanTwo);
					
					newCheckTwo = checkTimeTwo;
					
					//System.out.println("Standard Deviation 2: "+deviation2);
					SmartDashboard.putNumber("Steadiness of Oscillation",deviation2);
					if (deviation2<bestValue){// If deviation is better
						bestValue = deviation2;
						bestValueAmount = Robot.driveTrain.pPid;
						switch(whatToTune){
							case"f": 
								Robot.driveTrain.fPID += .005;
								SmartDashboard.putString("Recomendation","Raising f");
								break;
							case"p": 
								Robot.driveTrain.pPid += .005;
								SmartDashboard.putString("Recomendation","Raising p");
								break;
							case"i": 
								Robot.driveTrain.iPid += .005;
								SmartDashboard.putString("Recomendation","Raising i");
								break;
							case"d": 
								Robot.driveTrain.dPid += .005;
								SmartDashboard.putString("Recomendation","Raising d");
								break;
						}
					}else if (deviation2>bestValue*1.2){
						switch(whatToTune){
							case"f": 
								Robot.driveTrain.fPID -= .005;
								SmartDashboard.putString("Recomendation","Lowering f");
								break;
							case"p": 
								Robot.driveTrain.pPid -= .005;
								SmartDashboard.putString("Recomendation","Lowering p");
								break;
							case"i": 
								Robot.driveTrain.iPid -= .005;
								SmartDashboard.putString("Recomendation","Lowering i");
								break;
							case"d": 
								Robot.driveTrain.dPid -= .005;
								SmartDashboard.putString("Recomendation","Lowering d");
								break;
						}
					}else{
						SmartDashboard.putString("Recomendation","Very Similar, Double Checking");
					}
					
				}
				
					
				
				
				newCheck = checkTime;
			}
		}else{
			countDown--;
		}
	}
	
	/*@Override
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
			
			//double headingCorrection = headingError* CORRECTION_FACTOR;
			double headingCorrection = 0;
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
	}*/
	
	/*private double calculateRPM(double velocity){
		double inchesPerSecond = velocity * inchesPerMeter;
		double inchesPerRevolution = wheelInches * Math.PI;
		double rpm = (inchesPerSecond * 60) / inchesPerRevolution;
		return rpm;
	}*/
	
/*	private double calculateRPM(double velocity){
		SmartDashboard.putNumber("Pre-Velocity", velocity);
		double rpm = (60*velocity)/(2*Math.PI*2);//in meters
		return rpm;
	}*/
	
	/*private double calculateVelocity(double rpm){
		double velocity = ((2*Math.PI)/60)*2*rpm;
		SmartDashboard.putNumber("Post-Velocity", velocity);
		SmartDashboard.putNumber("RPM", rpm);
		return velocity;
	}
	private double calculateVelocity(double rpm){
		double inchesPerSecond = rpm/inchesPerMeter;
		double inchesPerRevolution = wheelInches * Math.PI;
		double velocity = (inchesPerSecond/60) * inchesPerRevolution;
		SmartDashboard.putNumber("Velocity", velocity);
		SmartDashboard.putNumber("RPM", rpm);
		return velocity;
	}*/
}

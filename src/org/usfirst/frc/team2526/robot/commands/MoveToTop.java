package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.DigitalInput; 

/**
 *
 */
public class MoveToTop extends Command {
	WPI_TalonSRX Elevator1;
	WPI_TalonSRX Elevator2;
	Boolean isDone = false;
	//DigitalInput limitSwitch;
    public MoveToTop() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
       // WPI_TalonSRX(eL1).set(0.5); //?? 
       
   
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//limitSwitch = new DigitalInput(1);
   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.moveTop();
    	isDone = !Robot.elevator.gettopLimit();
    	//while (limitSwitch.get()) {
    		//Timer.delay(10);
    	}
    	
  
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

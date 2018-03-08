package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.commands.ElevatorControl;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pneumatics extends Subsystem {

	private DoubleSolenoid intakeLift;
	private DoubleSolenoid elevatorShift;
	private Solenoid driveShift;
	private Solenoid carriageDrop;
	public boolean liftIntake;
	public boolean elevatorButton;
	public boolean shiftElevator;
	public boolean dropCarriage;
	public boolean carriageLimit = true;
	
	public Pneumatics(int intakeLiftChannel1,int intakeLiftChannel2, int elevatorShiftChannel1,int elevatorShiftChannel2, int driveShiftChannel, int dropCarriageChannel) {
		intakeLift  = new DoubleSolenoid(intakeLiftChannel1,intakeLiftChannel2);
		elevatorShift = new DoubleSolenoid(elevatorShiftChannel1,elevatorShiftChannel2);
		driveShift = new Solenoid(driveShiftChannel);
		carriageDrop = new Solenoid(dropCarriageChannel);
	    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       // setDefaultCommand();
    	
    }
    
    //Intake Pneumatics
    public void setIntake(boolean intakeb) {
    	liftIntake = intakeb;
    }
    public boolean getIntakebool() {
    	return liftIntake;
    }
    public void intakeLift() {
    	intakeLift.set(DoubleSolenoid.Value.kForward);
    }
    public void intakeDrop() {
    	intakeLift.set(DoubleSolenoid.Value.kReverse);
    }
    
    //Elevator Pneumatics
    public void setElevator(boolean elevatorBool) {
    	elevatorButton = elevatorBool;
    }
    public boolean getElevatorBool() {
    	return elevatorButton;
    }
    public void elevatorShiftUp() {
    	shiftElevator = true;
    	elevatorShift.set(DoubleSolenoid.Value.kForward);
    }
    public void elevatorShiftDown() {
    	shiftElevator = false;
    	elevatorShift.set(DoubleSolenoid.Value.kReverse);
    }
    
    //DriveTrain Pneumatics
    public void driveShiftUp() {
    	driveShift.set(false);
    }
    public void driveShiftDown() {
    	driveShift.set(true);
    }
    
    //Carriage Pneumatics
    public void carriageDrop() {
    	dropCarriage = true;
    	carriageLimit = false;
    	carriageDrop.set(dropCarriage);
    }
    public void carriageclose() {
    	dropCarriage = false;
    	carriageDrop.set(dropCarriage);
    }
    public boolean getCarriageBool() {
    	return carriageLimit;
    }
    


    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
  
   }

    	


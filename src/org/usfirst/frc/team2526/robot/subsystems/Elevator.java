package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.ElevatorControl;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {

	WPI_TalonSRX Elevator1;
	WPI_TalonSRX Elevator2;
	DigitalInput topElevator;
	DigitalInput bottomElevator;
	DigitalInput nnewl;
	
	double elevatorTopPosition = 3000;
	double slowDownArea = 600;
	double antiGrav = .05;
	public Elevator(int el1, int el2, int lmB, int lmT) {
    	Elevator1 = new WPI_TalonSRX(el1);
    	Elevator2 = new WPI_TalonSRX(el2);
	   	topElevator = new DigitalInput(1);
	   	bottomElevator = new DigitalInput(0);
	   	Elevator1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
	   	Elevator2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
	   	Elevator1.setSensorPhase(true);
	   	Elevator2.setSensorPhase(true);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ElevatorControl());
    	
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void moveElevatorTop() {
    	if (Math.abs(elevatorTopPosition-Elevator1.getSelectedSensorPosition(0))<slowDownArea){
        	Elevator1.set(Math.pow(2, ((elevatorTopPosition-Elevator1.getSelectedSensorPosition(0))/90)));
        	Elevator2.set(Math.pow(2, ((elevatorTopPosition-Elevator1.getSelectedSensorPosition(0))/90)));
    		
    	}else{
        	Elevator1.set(1);
        	Elevator2.set(1);
    	}
    	
    	
    }
    public void moveTop(){
    	Elevator1.set(.4);
    	Elevator2.set(.4);
    }
    public void moveElevator(Joystick coStick) {
    	SmartDashboard.putNumber("Elevator1 Encoder Value", Elevator1.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("Elevator2 Encoder Value", Elevator2.getSelectedSensorPosition(0));
    	if(topElevator.get() && bottomElevator.get()){
    		Elevator1.set(-coStick.getY());
        	Elevator2.set(-coStick.getY());
    		
    	}else{
    		if(!topElevator.get()) {
    			if(-coStick.getY() > 0) {
    				Elevator1.set(0);
    				Elevator2.set(0);
    			}else {
    				Elevator1.set(-coStick.getY());
    	        	Elevator2.set(-coStick.getY());
    			}
    		}else if(!bottomElevator.get()) {
    			if(-coStick.getY() < 0 && Robot.pneumatics.getCarriageBool()) {
    				Elevator1.set(antiGrav);
    				Elevator2.set(antiGrav);
    			}else {
    				Elevator1.set(-coStick.getY());
    	        	Elevator2.set(-coStick.getY());
    			}
    		}
    	}
    	
    	if (bottomElevator.get()) {
			if (Elevator1.get() > -.05 && Elevator1.get() < .05){
				Elevator1.set(antiGrav);
				Elevator2.set(antiGrav);
			}
    	}
    	}	
   public boolean gettopLimit() {
    	return topElevator.get();
    }
   public boolean getbottomLimit() {
   	return bottomElevator.get();
   }
   public boolean getnewLimit() {
	   	return nnewl.get();
	   }
   public void moveTopElevator() {
	   if(!topElevator.get()) {
		  Elevator1.set(antiGrav);
		  Elevator2.set(antiGrav);
	   }else {	
		Elevator1.set(1);
       	Elevator2.set(1);
	   }
   }
   public void stopElevator() {
	   Elevator1.set(antiGrav);
	   Elevator2.set(antiGrav);
   }
}
    	


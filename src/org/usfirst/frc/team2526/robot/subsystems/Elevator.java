package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.commands.ElevatorControl;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	WPI_TalonSRX Elevator1;
	WPI_TalonSRX Elevator2;
	DigitalInput limitSwitch1;
	DigitalInput limitSwitch2;
	public Elevator(int el1, int el2) {
	    	Elevator1 = new WPI_TalonSRX(el1);
	    	Elevator2 = new WPI_TalonSRX(el2);
	    	limitSwitch1 = new DigitalInput(0);
	    	limitSwitch2 = new DigitalInput(1);
	    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ElevatorControl());
    	
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void moveElevator(Joystick costick) {
    	Elevator1.set(-costick.getY());
    	Elevator2.set(-costick.getY());
    	/*if(!limitSwitch1.get() || !limitSwitch2.get()) {
    		Elevator1.set(-costick.getY());
        	Elevator2.set(-costick.getY());
    	}else {
    		Elevator1.set(0);
        	Elevator2.set(0);
    	}*/
    	
    		
    	}
    	}	
    	



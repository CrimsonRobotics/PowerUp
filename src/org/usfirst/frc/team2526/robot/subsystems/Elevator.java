package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.commands.ElevatorControl;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	WPI_TalonSRX Elevator1;
	WPI_TalonSRX Elevator2;
	DigitalInput limitSwitch;
	public Elevator(int el1, int el2) {
	    	Elevator1 = new WPI_TalonSRX(el1);
	    	Elevator2 = new WPI_TalonSRX(el2);
	    	limtSwitch  = new DigitalInput(0);
	    	limitSwitch.
	    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ElevatorControl());
    	
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void moveElevator(Joystick costick) {
    	if(limitSwtich = true){
    	Elevator1.set(-costick.getY());
    	Elevator2.set(-costick.getY());
    	}else (limitSwitch = false){
    	Elevator.set()
    		
    	}
    	}	
    	
}


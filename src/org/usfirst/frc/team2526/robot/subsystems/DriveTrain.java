package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.commands.TeleopDrive;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	WPI_TalonSRX frontleft;
	WPI_TalonSRX frontright;
	WPI_TalonSRX backleft;
	WPI_TalonSRX backright;
	
	DifferentialDrive drive;
	
	SpeedControllerGroup left = new SpeedControllerGroup(frontleft, backleft);
	SpeedControllerGroup right = new SpeedControllerGroup(frontright, backright);
	
		public DriveTrain(int drivetrainBackleft, int drivetrainBackright, int drivetrainFrontleft, int drivetrainFrontright){
			
			frontleft = new WPI_TalonSRX(1);
			backleft = new WPI_TalonSRX(3);
			frontright = new WPI_TalonSRX(2);
			backright = new WPI_TalonSRX(4);
			
			backleft.follow(frontleft);
			backright.follow(frontright);
		
			drive = new DifferentialDrive(this.left , this.right);
			teleopDriveInit();
			
		}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleopDrive());
    }
    
    public void teleopDriveInit(){
    	
    	frontleft.setInverted(true);
    	backleft.setInverted(true);
    	frontright.setInverted(true);
    	backleft.setInverted(true);
    	
    }
    
  
	
	
    public void teleopCraneDrive(Joystick left, Joystick right){
		drive.arcadeDrive(left.getY(), right.getX(), true);
}
    
}


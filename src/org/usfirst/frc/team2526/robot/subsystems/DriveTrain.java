package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.TeleopDrive;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//
	WPI_TalonSRX frontleft;
	WPI_TalonSRX frontright;
	WPI_TalonSRX backleft;
	WPI_TalonSRX backright;
	DifferentialDrive drive;
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	
	
	
		public DriveTrain(int bLID, int bRID, int fLID, int fRID){
			
			frontleft = new WPI_TalonSRX(fLID);
			backleft = new WPI_TalonSRX(bLID);
			frontright = new WPI_TalonSRX(fRID);
			backright = new WPI_TalonSRX(bRID);
			left = new SpeedControllerGroup(frontleft, backleft);
			right = new SpeedControllerGroup(frontright, backright);
			backleft.follow(frontleft);
			backright.follow(frontright);
			left = new SpeedControllerGroup(frontleft, backleft);
			right = new SpeedControllerGroup(frontright, backright);
			drive = new DifferentialDrive(this.left , this.right);
			frontleft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
			frontright.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
		//	SmartDashboard.putNumber("speedR", frontright.getSelectedSensorVelocity(0));
	//		SmartDashboard.putNumber("speedL", frontleft.getSelectedSensorVelocity(0));		
			//teleopDriveInit();
			
		}
		public int getRightSpeed(){
			return frontright.getSelectedSensorVelocity(0);
			
		}
		public int getLeftSpeed(){
			return frontleft.getSelectedSensorVelocity(0);
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
		drive.arcadeDrive(left.getY(), -right.getX(), true);
}
    
}


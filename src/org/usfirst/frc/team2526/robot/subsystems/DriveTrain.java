package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.TeleopDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
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
	public WPI_TalonSRX frontLeft;
	public WPI_TalonSRX frontRight;
	WPI_TalonSRX backLeft;
	WPI_TalonSRX backRight;
	DifferentialDrive drive;
	SpeedControllerGroup left;
	SpeedControllerGroup right;

	public Double fPID = 100.0;
	public Double pPid = 0.0;
	public Double iPid = 0.0;
	public Double dPid = 0.0;
	
		public DriveTrain(int bLID, int bRID, int frontLeftID, int frontRightID, int channel){
			
			frontLeft = new WPI_TalonSRX(frontLeftID);
			backLeft = new WPI_TalonSRX(bLID);
			frontRight = new WPI_TalonSRX(frontRightID);
			backRight = new WPI_TalonSRX(bRID);
			left = new SpeedControllerGroup(frontLeft, backLeft);
			right = new SpeedControllerGroup(frontRight, backRight);
			backLeft.follow(frontLeft);
			backRight.follow(frontRight);
			left = new SpeedControllerGroup(frontLeft, backLeft);
			right = new SpeedControllerGroup(frontRight, backRight);
			drive = new DifferentialDrive(this.left , this.right);
			frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
			frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
		//	SmartDashboard.putNumber("speedR", frontright.getSelectedSensorVelocity(0));
	//		SmartDashboard.putNumber("speedL", frontleft.getSelectedSensorVelocity(0));		
			//teleopDriveInit();
			
		}
		public int getRightSpeed(){
			return frontRight.getSelectedSensorVelocity(0);
			
		}
		public int getLeftSpeed(){
			return frontLeft.getSelectedSensorVelocity(0);
		}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleopDrive());
    }
    
    public void speedDrive(double speedRight,double speedLeft){
		frontLeft.set(ControlMode.Position,-(speedRight)*1440);
		frontRight.set(ControlMode.Position,(speedLeft)*1440);///60)/100)
		//SmartDashboard.putNumber("speedLeftVelocity", ((speedLeftVelocity*60)*100));
		//SmartDashboard.putNumber("speedLeftVelocity", ((speedLeft)));
	}

    public void teleopDriveInit(){
   
    	/*frontleft.setInverted(true);
    	backleft.setInverted(true);
    	frontright.setInverted(true);
    	backleft.setInverted(true);
    	*/
    }
    
    public void pidInit(){
		fPID = SmartDashboard.getNumber("fPID", 0);
		pPid = SmartDashboard.getNumber("pPID", 0);
		iPid = SmartDashboard.getNumber("iPID", 0);
		dPid = SmartDashboard.getNumber("dPID", 0);
		//set pid for Left Side
		//frontLeft.setPID(gainsLeft.p, gainsLeft.i, gainsLeft.d, gainsLeft.f, gainsLeft.iZone, gainsLeft.rampRate, gainsLeft.profile);
		frontLeft.config_kF(0,fPID,0); 
		frontLeft.config_kP(0,pPid,0);
		frontLeft.config_kI(0,iPid,0);
		frontLeft.config_kD(0,dPid,0);
		frontLeft.selectProfileSlot(0,0);
	
		//Set pid for Right Side
		//frontRight.setPID(gainsRight.p, gainsRight.i, gainsRight.d, gainsRight.f, gainsRight.iZone, gainsRight.rampRate, gainsRight.profile);
		frontRight.config_kF(0,fPID,0); 
		frontRight.config_kP(0,pPid,0);
		frontRight.config_kI(0,0,0);
		frontRight.config_kD(0,dPid,0);
		frontRight.selectProfileSlot(0,0);
		
		//frontLeft.setFeedbacxkDevice(CANTalon.FeedbackDevice.QuadEncoder);
		backLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		//frontRight.setFeedbacxkDevice(CANTalon.FeedbackDevice.QuadEncoder);
	//	frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		//frontLeft.reverseSensor(false);
		frontLeft.setSensorPhase(false);
		//frontRight.reverseSensor(true);
		frontRight.setSensorPhase(true);
		
		//disable safety
		frontLeft.setSafetyEnabled(false);
		frontRight.setSafetyEnabled(false);
		backLeft.setSafetyEnabled(false);
		backRight.setSafetyEnabled(false);
	}
	
	
    public void teleopCraneDrive(Joystick left, Joystick right){
		drive.arcadeDrive(left.getY(), -right.getX(), true);
}
    
}


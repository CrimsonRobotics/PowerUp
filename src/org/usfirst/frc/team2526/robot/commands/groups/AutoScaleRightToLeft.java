package org.usfirst.frc.team2526.robot.commands.groups;


import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.AutoDrive;
import org.usfirst.frc.team2526.robot.commands.DownIntake;
import org.usfirst.frc.team2526.robot.commands.GoForward;
import org.usfirst.frc.team2526.robot.commands.MoveToTop;
import org.usfirst.frc.team2526.robot.commands.Shoot;
import org.usfirst.frc.team2526.robot.commands.UpIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleRightToLeft extends CommandGroup {

    public AutoScaleRightToLeft() {
    	
    	setInterruptible(false);
    	
    	addSequential(new UpIntake());
    	addSequential(new UpIntake());
    	addSequential(new AutoDrive(12500,12500,200,1));
    	addSequential(new AutoDrive(1100,-1100,100,.5));
    	addParallel(new MoveToTop());
    	addParallel(new MoveToTop());
    	addSequential(new AutoDrive(10500,10500,200,1));
    	addSequential(new AutoDrive(-1100,1100,100,.5));
    	addSequential(new AutoDrive(1500,1500,100,1));
    	addSequential(new Shoot(3));
    	
    	
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

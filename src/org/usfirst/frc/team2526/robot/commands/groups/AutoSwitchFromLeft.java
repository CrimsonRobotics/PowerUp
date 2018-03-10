package org.usfirst.frc.team2526.robot.commands.groups;


import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.AutoDrive;
import org.usfirst.frc.team2526.robot.commands.DownIntake;
import org.usfirst.frc.team2526.robot.commands.GoForward;
import org.usfirst.frc.team2526.robot.commands.MoveToTop;
import org.usfirst.frc.team2526.robot.commands.ShiftDown;
import org.usfirst.frc.team2526.robot.commands.Shoot;
import org.usfirst.frc.team2526.robot.commands.ShootSlow;
import org.usfirst.frc.team2526.robot.commands.UpIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchFromLeft extends CommandGroup {

    public AutoSwitchFromLeft() {
    	
    	setInterruptible(false);
    	addSequential(new ShiftDown());
    	addSequential(new UpIntake());
    	addSequential(new UpIntake());
    	addParallel(new MoveToTop());
    	addParallel(new MoveToTop());
    	addSequential(new AutoDrive(7530,7530,200,.85,false));
    	//addSequential(new MoveToTop());
    	//addSequential(new MoveToTop());
    	addSequential(new AutoDrive(-1100,1100,100,.4,false));
    	addSequential(new GoForward(2));
    	addSequential(new ShootSlow(3));
    	
    	//addSequential(new MoveToTop());
    	
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

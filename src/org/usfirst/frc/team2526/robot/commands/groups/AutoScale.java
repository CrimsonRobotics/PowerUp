package org.usfirst.frc.team2526.robot.commands.groups;


import org.usfirst.frc.team2526.robot.commands.AutoDrive;
import org.usfirst.frc.team2526.robot.commands.DownIntake;
import org.usfirst.frc.team2526.robot.commands.MoveToTop;
import org.usfirst.frc.team2526.robot.commands.Shoot;
import org.usfirst.frc.team2526.robot.commands.UpIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScale extends CommandGroup {

    public AutoScale() {
    	
    	setInterruptible(false);
    	//addSequential(new DownIntake(2));
    	addSequential(new UpIntake());
    	addSequential(new UpIntake());
    	addSequential(new AutoDrive(15800,15800,100,.7));
    	addSequential(new MoveToTop());
    	addSequential(new AutoDrive(-1100,1100,100,.4));
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

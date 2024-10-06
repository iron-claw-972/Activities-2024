
package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.BangBangRotateCommand;
import frc.robot.subsystems.DriveSub;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;


public class SubsystemTab extends ShuffleBoardTabs {
    // TODO 2.3.11: Create variable for subsystem
    
    public DriveSub driveSub;

    public SubsystemTab(DriveSub driveSub){
        this.driveSub = driveSub;
    }
    
    public void createEntries(){
        tab = Shuffleboard.getTab("Subsystem");

        // TODO 2.4.7: Add Mechanism2d
        tab.add("Motor Visualization", driveSub.getMechanism());



        // TODO 3.3.13: Add command buttons
        tab.add("Spin Motor", new BangBangRotateCommand(driveSub, 5)); // spin motor 5 rotations

        // TODO 5.3.1: Add PID

    }


    public void update(){}
}

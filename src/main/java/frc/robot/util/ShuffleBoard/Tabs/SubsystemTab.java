package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.BangBangRotateCommand;
import frc.robot.commands.BangBangSubsystemCommand;
import frc.robot.subsystems.DriveSub;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;


public class SubsystemTab extends ShuffleBoardTabs {
    // TODO 2.3.11: Create variable for subsystem
    
    private DriveSub driveSub;

    public SubsystemTab(DriveSub driveSub){
        this.driveSub = driveSub;
    }
    
    public void createEntries(){
        tab = Shuffleboard.getTab("Subsystem");
        tab.add("Example PID", driveSub.subsystemPID);
        tab.add("Subsystem", driveSub.getMechanism());
    }


    public void update(){}
}

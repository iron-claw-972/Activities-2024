
package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.subsystems.MySubsystem;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;


public class SubsystemTab extends ShuffleBoardTabs {
    // TODO 2.3.11: Create variable for subsystem
    private MySubsystem brian_subsystem;

    public SubsystemTab(MySubsystem subsystem){
        brian_subsystem = subsystem;
    }

    public void createEntries(){
        tab = Shuffleboard.getTab("Subsystem");

        // TODO 2.4.7: Add Mechanism2d
        tab.add("wheel", MySubsystem.getMech2d());

        // TODO 3.3.13: Add command buttons

        // TODO 5.3.1: Add PID
        tab.add("pid", MySubsystem.getPID());
    }


    public void update(){}
}

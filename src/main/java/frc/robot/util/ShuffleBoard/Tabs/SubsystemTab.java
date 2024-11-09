
package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.subsystems.OrangeArm;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;


public class SubsystemTab extends ShuffleBoardTabs {
    // TODO 2.3.11: Create variable for subsystem
    private OrangeArm orange;

    public SubsystemTab(OrangeArm orange){
        this.orange = orange;
    }

    public void createEntries(){
        tab = Shuffleboard.getTab("Subsystem");
        tab.add("Getting mechanism2d", orange.getMechanism2d());
        // TODO 2.4.7: Add Mechanism2d

        // TODO 3.3.13: Add command buttons

        // TODO 5.3.1: Add PID

    }


    public void update(){}
}

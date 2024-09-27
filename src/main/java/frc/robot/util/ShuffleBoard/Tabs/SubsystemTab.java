
package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.subsystems.Ting;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;


public class SubsystemTab extends ShuffleBoardTabs {
    Ting ting;

    public SubsystemTab(int motorId){
        ting = new Ting(motorId);
    }

    public void createEntries(){
        tab = Shuffleboard.getTab("Subsystem");

        tab.add("Ting", ting.ting1);
        // TODO 2.4.7: Add Mechanism2d

        // TODO 3.3.13: Add command buttons

        // TODO 5.3.1: Add PID

    }


    public void update(){}
}

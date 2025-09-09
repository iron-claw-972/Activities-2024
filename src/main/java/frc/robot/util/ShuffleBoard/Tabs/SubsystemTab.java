
package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.subsystems.motorSubsystem;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;


public class SubsystemTab extends ShuffleBoardTabs {
    public motorSubsystem motor;
    // TODO 2.3.11: Createvariable for subsystem
    public SubsystemTab(motorSubsystem motor){
        this.motor = motor;
    }

    public void createEntries(){
        tab = Shuffleboard.getTab("Subsystem");

        // TODO 2.4.7: Add Mechanism2d

        // TODO 3.3.13: Add command buttons

        // TODO 5.3.1: Add PID

    }


    public void update(){}
}

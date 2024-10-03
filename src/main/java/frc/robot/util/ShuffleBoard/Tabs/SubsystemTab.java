
package frc.robot.util.ShuffleBoard.Tabs;

import java.awt.Robot;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.TurnyCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Ting;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;


public class SubsystemTab extends ShuffleBoardTabs {
    Ting ting;

    public SubsystemTab(Ting tingy){
        ting = tingy;
    }

    public void createEntries(){
        tab = Shuffleboard.getTab("Subsystem");

        tab.add("Ting", ting.getMechanism2d());
        // TODO 2.4.7: Add Mechanism2d

        // TODO 3.3.13: Add command buttons

        // TODO 5.3.1: Add PID

    }


    public void update(){}
}

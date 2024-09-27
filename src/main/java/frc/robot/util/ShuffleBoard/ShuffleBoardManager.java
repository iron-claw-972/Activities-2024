package frc.robot.util.ShuffleBoard;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Orange;
import frc.robot.util.ShuffleBoard.Tabs.AutoTab;
import frc.robot.util.ShuffleBoard.Tabs.DriveTab;
import frc.robot.util.ShuffleBoard.Tabs.SubsystemTab;

public class ShuffleBoardManager {

    private ArrayList<ShuffleBoardTabs> tabs = new ArrayList<>();
    
    private Field feild;

    private DriveTab driveTab;
    private AutoTab autoTab;
    private SubsystemTab subsystemTab;

    // TODO 2.3.12: Add parameter to constructor
    public ShuffleBoardManager(Drivetrain drive, Orange orange){

        driveTab = new DriveTab(drive);
        autoTab = new AutoTab(drive);
        subsystemTab = new SubsystemTab(orange);
        tabs.add(driveTab);
        tabs.add(autoTab);
        tabs.add(subsystemTab);

        for (ShuffleBoardTabs tab : tabs){
            tab.createEntries();
        }
        
        feild = new Field(drive);

        // This doesn't need to be stored in its own class
        Shuffleboard.getTab("Scheduler").add("Command Scheduler", CommandScheduler.getInstance());
        SmartDashboard.putData("Command Scheduler", CommandScheduler.getInstance());
    }

    public void update(){
        for (ShuffleBoardTabs tab : tabs){
            tab.update();
        }
        feild.updateFeild();
    }

    public Command getSelectedCommand(){
        return autoTab.getChooser().getSelected();
    }
}

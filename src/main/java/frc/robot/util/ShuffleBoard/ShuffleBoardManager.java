package frc.robot.util.ShuffleBoard;

import java.util.ArrayList;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
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
    public ShuffleBoardManager(Drivetrain drive){
        driveTab = new DriveTab(drive);
        autoTab = new AutoTab(drive);
        subsystemTab = new SubsystemTab();
        tabs.add(driveTab);
        tabs.add(autoTab);
        tabs.add(subsystemTab);

        for (ShuffleBoardTabs tab : tabs){
            tab.createEntries();
        }
        
        feild = new Field(drive);
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

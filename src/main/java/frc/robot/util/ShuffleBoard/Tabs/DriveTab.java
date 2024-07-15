package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.SysIDDriveCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;



public class DriveTab extends ShuffleBoardTabs {
    
    private Drivetrain drive;

    private GenericEntry xOdemetry;
    private GenericEntry yOdemetry;
    private GenericEntry rotOdemetry;

    public DriveTab(Drivetrain drive){
        this.drive = drive;
    }

    public void createEntries(){
        tab = Shuffleboard.getTab("Drive");

        xOdemetry = tab.add("x odemetry", 0).withPosition(0, 3).getEntry();
        yOdemetry = tab.add("y odemetry", 0).withPosition(1, 3).getEntry();
        rotOdemetry = tab.add("chassis rotation", 0).withPosition(3, 3).getEntry();

        tab.add("Characterize", new SysIDDriveCommand(drive));
    }


    public void update(){
        xOdemetry.setDouble(truncate(drive.getPose().getX()));
        yOdemetry.setDouble(truncate(drive.getPose().getY()));
        rotOdemetry.setDouble(truncate(drive.getPose().getRotation().getDegrees()));
    }
}

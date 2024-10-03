package frc.robot.util.ShuffleBoard.Tabs;

import java.awt.geom.Point2D;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.Robot;
import frc.robot.commands.BangBangDriveCommand;
import frc.robot.commands.FunnyCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Ting;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;

public class AutoTab extends ShuffleBoardTabs {

    private final SendableChooser<Command> autoCommand = new SendableChooser<>();

    private Drivetrain drive;

    public AutoTab(Drivetrain drive){
        this.drive = drive;
    }
    
    public void createEntries(){  
        tab = Shuffleboard.getTab("Auto");
    
        // Default auto
        autoCommand.setDefaultOption("Do Nothing", new PrintCommand("This will do nothing!"));
        // Add auto commands here
        // TODO 3.2.7: Add your auto command here

        autoCommand.addOption("funny", new FunnyCommand(drive, Robot.ting));

        // TODO 3.3.8: Add your Bang-Bang drive command here

        autoCommand.addOption("bingbongboom", new BangBangDriveCommand(drive, new Pose2d(5,5, new Rotation2d(0,0))));
        // TODO 3.3.11: Add your Bang-Bang command for your subsystem here
        
        tab.add(autoCommand);
    }

    public void update(){
    }

    public SendableChooser<Command> getChooser(){
        return autoCommand;
    }
}

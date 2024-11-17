package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.commands.AutoForwardCommand;
import frc.robot.commands.AutoSquareCommand;
import frc.robot.commands.BangBangDriveCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;

public class AutoTab extends ShuffleBoardTabs {

    private final SendableChooser<Command> autoCommand = new SendableChooser<>();

    private Drivetrain drive;

    public AutoTab(Drivetrain drive) {
        this.drive = drive;
    }

    public void createEntries() {
        tab = Shuffleboard.getTab("Auto");

        // Default auto
        autoCommand.setDefaultOption("Do Nothing", new PrintCommand("This will do nothing!"));
        // Add auto commands here
        // TODO 3.2.7: Add your auto command here
        autoCommand.addOption("Drive Forward", new AutoForwardCommand(drive, 1, 2)); // moves 50% for 2 seconds
        autoCommand.addOption("Drive Square", new AutoSquareCommand(drive));
        autoCommand.addOption("Bang-Bang Drive", new BangBangDriveCommand(drive, 2.0)); // Drive 2 meters as an example

        // TODO 3.3.8: Add your Bang-Bang drive command here

        // TODO 3.3.11: Add your Bang-Bang command for your subsystem here

        tab.add(autoCommand);
    }

    public void update() {
    }

    public SendableChooser<Command> getChooser() {
        return autoCommand;
    }
}

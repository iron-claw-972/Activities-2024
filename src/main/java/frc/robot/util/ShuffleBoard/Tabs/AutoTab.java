package frc.robot.util.ShuffleBoard.Tabs;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.commands.BangBangController;
import frc.robot.commands.BangBangSpinMotor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Orange;
import frc.robot.util.ShuffleBoard.ShuffleBoardTabs;

public class AutoTab extends ShuffleBoardTabs {

    private final SendableChooser<Command> autoCommand = new SendableChooser<>();

    private Drivetrain drive;
    private Orange orange;

    private BangBangController bangBangController;
    private BangBangSpinMotor bangBangSpinMotor;

    public AutoTab(Drivetrain drive, Orange orange){
        this.drive = drive;
        this.orange = orange;
        this.bangBangController = new BangBangController(drive,50);
        this.bangBangSpinMotor = new BangBangSpinMotor(orange, 50);
    }
    
    public void createEntries(){  
        tab = Shuffleboard.getTab("Auto");
    
        // Default auto
        autoCommand.setDefaultOption("Do Nothing", new PrintCommand("This will do nothing!"));
        // Add auto commands here
        // TODO 3.2.7: Add your auto command here
        autoCommand.addOption("Bang Bang", bangBangController);
        autoCommand.addOption("Bang Bang 2", bangBangSpinMotor);
        // TODO 3.3.8: Add your Bang-Bang drive command here

        // TODO 3.3.11: Add your Bang-Bang command for your subsystem here
        
        tab.add(autoCommand);
    }

    public void update(){
    }

    public SendableChooser<Command> getChooser(){
        return autoCommand;
    }
}

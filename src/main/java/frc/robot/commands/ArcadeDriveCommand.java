package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCommand extends Command{
    public ArcadeDriveCommand(Drivetrain drive){
        addRequirements(drive);
    }

    public void execute(){
        
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCommand extends Command {
    private Drivetrain drive;
    public ArcadeDriveCommand(Drivetrain drive){
        addRequirements(drive);
        
    }

    @Override
    public void execute(){
        drive.arcadeDrive(Robot.driver.getForwardTranslation(), Robot.driver.getTurn());
    }
     
    
}

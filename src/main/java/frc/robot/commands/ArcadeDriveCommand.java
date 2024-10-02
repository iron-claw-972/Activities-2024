package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCommand extends Command{
    
    Drivetrain driv;
    public ArcadeDriveCommand(Drivetrain drev){
        driv = drev;
        addRequirements(driv);
    }

    @Override
    public void execute(){
        driv.arcadeDrive(Robot.driver.getForwardTranslation(), Robot.driver.getTurn());
    }
}

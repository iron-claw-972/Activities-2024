package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
public class ArcadeDriveCommand extends Command {
    public Drivetrain driveTrain;
    public ArcadeDriveCommand(Drivetrain driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        
        this.driveTrain.arcadeDrive(Robot.driver.getForwardTranslation(), Robot.driver.getTurn());
    }
}

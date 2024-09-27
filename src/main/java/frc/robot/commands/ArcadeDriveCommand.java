package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCommand extends Command {

    public Drivetrain drivetrain;

    public ArcadeDriveCommand(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(null);
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        super.execute();
        drivetrain.arcadeDrive(Robot.driver.getForwardTranslation(), Robot.driver.getTurn());
    }
}
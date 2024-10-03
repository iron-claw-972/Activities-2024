package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCommand extends Command {
    private final Drivetrain drivetrain;

    public ArcadeDriveCommand(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double throttle = Robot.driver.getForwardTranslation();
        double turn = Robot.driver.getTurn();
        drivetrain.arcadeDrive(throttle, turn);
    }
}

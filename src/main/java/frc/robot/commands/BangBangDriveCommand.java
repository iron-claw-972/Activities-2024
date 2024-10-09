package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class BangBangDriveCommand extends Command{
    private final Drivetrain drivetrain;
    private final double setpoint; // target distance to drive (meters)
    private final double power = 0.5; // motor power for Bang-Bang control

    public BangBangDriveCommand(Drivetrain drivetrain, double setpoint) {
        this.drivetrain = drivetrain;
        this.setpoint = setpoint;
        addRequirements(drivetrain); // ensure one command at a time
    }

    @Override
    public void initialize() {
        drivetrain.resetEncoders(); //reset encoders at start
    }

    @Override
    public void execute() {
        double currentPosition = drivetrain.getAveragePosition(); // get current average position
        if (currentPosition < setpoint) {
            drivetrain.tankDrive(power, power); // drive forward if position is below setpoint
        } else {
            drivetrain.tankDrive(-power, -power); // drive backward if overshot
        }
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0); // stop drivetrain when command ends
    }

    @Override
    public boolean isFinished() {
        double currentPosition = drivetrain.getAveragePosition();
        return Math.abs(currentPosition - setpoint) < 0.005; // finish within 5 mm of setpoint
    }
}

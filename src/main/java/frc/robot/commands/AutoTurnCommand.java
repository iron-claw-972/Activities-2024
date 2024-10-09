package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.geometry.Rotation2d;

public class AutoTurnCommand extends Command {
    private final Drivetrain drivetrain;
    private final double angle; // angle to turn (positive for left turn)
    private Rotation2d startAngle;

    public AutoTurnCommand(Drivetrain drivetrain, double angle) {
        this.drivetrain = drivetrain;
        this.angle = angle; // degrees to turn
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {

        startAngle = drivetrain.getGyroAngle(); // get the starting angle from the gyro
    }

    @Override
    public void execute() {
        // turn the robot to the left at a constant speed until it reaches the target angle
        drivetrain.arcadeDrive(0, -0.5); // negative value for turning left (you can adjust speed here)
    }

    @Override
    public boolean isFinished() {
        // calculate how far the robot has turned
        Rotation2d currentAngle = drivetrain.getGyroAngle();
        double angleTurned = currentAngle.minus(startAngle).getDegrees();

        // stop turning once we've turned the desired angle (negative for left)
        return Math.abs(angleTurned) >= Math.abs(angle);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0); // stop the drivetrain when done
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSub;
import edu.wpi.first.math.util.Units;

public class BangBangSubsystemCommand extends Command{
    private final DriveSub subsystem;
    private double setpoint;
    private final double power = 0.01;

    public BangBangSubsystemCommand(DriveSub subsystem, double setpoint) {
        this.subsystem = subsystem;
        this.setpoint = setpoint;
        addRequirements(subsystem);
    }

    public void setPoint(double setpoint) {
        this.setpoint = setpoint;
    }

    @Override
    public void execute() {
        double currentPosition = subsystem.getPosition();
        if (currentPosition < setpoint) {
            subsystem.setSpeed(power); // drive forward
        } else {
            subsystem.setSpeed(-power); // drive backward
        }
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.setSpeed(0); // stop the motor
    }

    @Override
    public boolean isFinished() {
        double currentPosition = subsystem.getPosition();
        return Math.abs(currentPosition - setpoint) < Units.degreesToRotations(1); // tolerance
    }
}


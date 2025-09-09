package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motorSubsystem;

public class pidCommand extends Command {
    private motorSubsystem subsystem;
    private double setPoint;
    public pidCommand(motorSubsystem subsystem, double setPoint) {
        this.subsystem = subsystem;
        this.setPoint = setPoint;
    }

    @Override
    public void initialize() {
        subsystem.setSetpoint(setPoint);
    }

    @Override
    public boolean isFinished() {
        return subsystem.atSetPoint();
    }
}

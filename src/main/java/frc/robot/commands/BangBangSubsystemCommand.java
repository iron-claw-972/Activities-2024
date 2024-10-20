package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSub;

public class BangBangSubsystemCommand extends Command{
    private final DriveSub subsystem;
    private final double setpoint;
    private final double power = 0.5;

    public BangBangSubsystemCommand(DriveSub subsystem, double setpoint) {
        this.subsystem = subsystem;
        this.setpoint = setpoint;
        addRequirements(subsystem);
    }

    public void setPoint(double setpoint) {

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
        return Math.abs(currentPosition - setpoint) < .5; // tolerance
    }
}

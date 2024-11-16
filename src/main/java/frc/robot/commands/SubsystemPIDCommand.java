package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSub;

public class SubsystemPIDCommand extends Command {
    private final DriveSub driveSub;
    private final double setpoint;

    // constructor
    public SubsystemPIDCommand(DriveSub driveSub, double setpoint) {
        this.driveSub = driveSub;
        this.setpoint = setpoint;
        addRequirements(driveSub);
    }

    // called once when command initially scheduled
    @Override
    public void initialize() {
        driveSub.spinTo(setpoint); // set the PID setpoint
    }

    // called once per scheduler run to check if the command is finished
    @Override
    public boolean isFinished() {
        return driveSub.atSetpoint(); // checks if the subsystem has reached the target
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MySubsystem;

public class PIDCommandWheel extends Command{
    private MySubsystem subsystem;
    double setpoint;

    public PIDCommandWheel(MySubsystem subsystem, double setpoint){
        this.subsystem = subsystem;
        this.setpoint = setpoint;
        addRequirements(subsystem);

    }

    public void initalize(){
        subsystem.spinTo(setpoint);
    }

    public boolean isFinished(){
        return subsystem.atSetpoint();
    }
}

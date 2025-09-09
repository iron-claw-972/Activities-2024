package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.MotorSubsystem;

public class PIDCommands extends Command{
    public final MotorSubsystem motorSubsystem;
    public final double setPoint;

    public PIDCommands(MotorSubsystem motorSubsystem, double setPoint){
        this.motorSubsystem = motorSubsystem;
        this.setPoint = setPoint;
        addRequirements(motorSubsystem);
    }
    public void initialize(){
        motorSubsystem.spinTo(2);
    }
    public boolean isFinished(){
        return motorSubsystem.atSetpoint();
    }
}

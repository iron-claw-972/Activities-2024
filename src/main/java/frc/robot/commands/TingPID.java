package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Ting;

public class TingPID extends Command{
    private Ting ting;
    private double setpoint;

    public TingPID(Ting tingy, double setpointy){
        ting = tingy;
        setpoint = setpointy;
    }

    @Override
    public void initialize(){
        ting.setTarget(setpoint);
    }

    @Override
    public boolean isFinished(){
        return ting.atSetpoint();
    }
}

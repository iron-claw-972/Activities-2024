package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.OrangeArm;

public class MoveOrangeArm extends Command{
    private OrangeArm orange;
    private double setpoint;
    public MoveOrangeArm(OrangeArm orange, double setpoint){
        addRequirements(orange);
        this.orange = orange;
        this.setpoint = setpoint;
    }
    public void initialize(){
        orange.spinTo(setpoint);
    }
    public boolean isFinished(){
        return orange.atSetpoint();
    }

    
}

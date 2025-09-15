package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Drivetrain;

public class AutoCommand extends Command{
    Drivetrain base;
    private double init_turn;

    public AutoCommand(Drivetrain base){
        this.base = base;
        addRequirements(base);
    }

    public void initialize(){
        init_turn = 0.5;
    }

    public void execute(){
        init_turn+=0.0001;
        base.arcadeDrive(0,init_turn);
    }

    public void end(){
        base.arcadeDrive(0,0);
    }
    public boolean isFinished(){
        if(init_turn>=1){
            return true;
        }
        else{
            return false;
        }
    }
}

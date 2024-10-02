package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Ting;

public class FunnyCommand extends Command{
        
    Drivetrain driv;
    Ting ting;
    int tim = 0;
    public FunnyCommand(Drivetrain drev, Ting tingy){
        driv = drev;
        ting = tingy;
        addRequirements(driv, ting);
    }

    @Override
    public void initialize(){
        driv.arcadeDrive(1, 0);
        ting.setMotor(0);
        tim = 0;
    }

    @Override
    public void execute(){
        tim++;
        tim = tim % 100;
        if (50 >= tim){
            driv.arcadeDrive(1, 1);
            ting.setMotor(1);
        }else{
            driv.arcadeDrive(1, -1);
            ting.setMotor(-1);
        }
    }

    @Override
    public void end(boolean interrupted){
        driv.arcadeDrive(0, 0);
        ting.setMotor(0);
    }

    @Override
    public boolean isFinished(){
        if (tim > 160){
            return true;
        }
        return false;
    }

}

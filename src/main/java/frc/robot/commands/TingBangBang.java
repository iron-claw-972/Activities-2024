package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Ting;

public class TingBangBang extends Command{
    private Ting ting;
    private double targetAngle;

    public TingBangBang(Ting ting, double target){
        this.ting = ting;
        this.targetAngle = target;
    }

    @Override
    public void initialize(){
        ting.stop();
    }
    
    @Override
    public void execute(){
        if (turnDirection(this.targetAngle)){
            ting.setMotor(-1);
        }else{
            ting.setMotor(1);
        }
    }

    @Override
    public boolean isFinished(){
        if (targetAngle == ting.getPos()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void end(boolean interrupted){
        ting.stop();
    }


    public boolean turnDirection(double target) {
        double currentAngle = (ting.getPos() % 360 + 360) % 360;
        target = (target % 360 + 360) % 360;
        
        double difference = target - currentAngle;
        
        difference = (difference + 540) % 360 - 180;
        
        return difference < 0;
    }
}

package frc.robot.commands;

import frc.robot.subsystems.Ting;

public class TurnyCommand extends TingBangBang{
    private int spins;
    private Ting ting;
    private int tim;
    
    public TurnyCommand(int spins, Ting ting){
        super(ting,0);
        this.spins = spins;
        this.ting = ting;
    }

    @Override
    public void initialize(){
        ting.stop();;
        tim = 50 * spins;

    }

    @Override
    public void execute(){
        ting.setMotor(1);
        tim--;
    }

    @Override
    public boolean isFinished(){
        if (tim <= 0){
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted){
        ting.stop();
    }
}

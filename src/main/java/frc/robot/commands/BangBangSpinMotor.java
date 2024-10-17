package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Orange;

public  class BangBangSpinMotor extends Command{
    private Orange orange; 
    private double setpoint;
     
    public BangBangSpinMotor (Orange orange, double setpoint) {
         this.orange = orange;
         this.setpoint = setpoint;

    }
    
    @Override
    public void initialize(){
        // drive.resetEncoders();
    }

    @Override
    public void execute(){
        double position = orange.encoderPosition();
        double speed = 0.25;
        if (position < setpoint){
            orange.setMotor(speed);
        }
        else{
            orange.setMotor(-speed);
        }   

    }

    @Override
    public void end(boolean interrupted){
        orange.motorStop();
    }

    @Override
    public boolean isFinished(){
        double position = orange.encoderPosition();
        double error = Math.abs(position - setpoint);

        return (error < 0.02);


    
    }


    
}

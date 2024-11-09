package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OrangeArm;

public  class BangBangSpinMotorReal extends Command{
    private OrangeArm orange; 
    private double setpoint;
     
    public BangBangSpinMotorReal (OrangeArm orange, double setpoint) {
         this.orange = orange;
         this.setpoint = setpoint;

    }
    
    @Override
    public void initialize(){
        // drive.resetEncoders();
    }

    @Override
    public void execute(){
        double position = orange.getGyroAngle().getDegrees();
        double power = 0.25;
        if (position < setpoint){
            orange.speed(-power);
        }
        else{
            orange.tankDrive(power, -power);
        }   

    }

    @Override
    public void end(boolean interrupted){
        orange.power(0,0);
    }

    @Override
    public boolean isFinished(){
        double position = orange.getGyroAngle().getDegrees();
        double error = Math.abs(position - setpoint);

        return (error < 0.5);
    }


    
}

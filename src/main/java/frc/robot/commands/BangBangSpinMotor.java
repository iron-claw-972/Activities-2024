package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Orange;

public  class BangBangSpinMotor extends Command{
    private Drivetrain drive; 
    private double setpoint;
     
    public BangBangSpinMotor (Drivetrain drive, double setpoint) {
         this.drive = drive;
         this.setpoint = setpoint;

    }
    
    @Override
    public void initialize(){
        // drive.resetEncoders();
    }

    @Override
    public void execute(){
        double position = drive.getGyroAngle().getDegrees();
        double power = 0.25;
        if (position < setpoint){
            drive.tankDrive(-power, power);
        }
        else{
            drive.tankDrive(power, -power);
        }   

    }

    @Override
    public void end(boolean interrupted){
        drive.tankDrive(0,0);
    }

    @Override
    public boolean isFinished(){
        double position = drive.getGyroAngle().getDegrees();
        double error = Math.abs(position - setpoint);

        return (error < 0.5);
    }


    
}

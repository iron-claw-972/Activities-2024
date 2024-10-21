package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class BangBangController extends Command {
    private Drivetrain drive; 
    private double setpoint;
     
    public BangBangController (Drivetrain drive, double setpoint){
         this.drive = drive;
         this.setpoint = setpoint;
        addRequirements(drive);
    }
    
    @Override
    public void initialize(){
        drive.resetEncoders();
    }

    @Override
    public void execute(){
        double position = drive.getAveragePosition();
        double power = 1.0;
        if (position < setpoint){
            drive.tankDrive(power,power);
        }
        else{
            drive.tankDrive(-power, -power);
        }   

    }

    @Override
    public void end(boolean interrupted){
        drive.tankDrive(0, 0);
    }

    @Override
    public boolean isFinished(){
        double position = drive.getAveragePosition();
        double error = Math.abs(position - setpoint);

        return (error < 0.02);

       
    
    }


}

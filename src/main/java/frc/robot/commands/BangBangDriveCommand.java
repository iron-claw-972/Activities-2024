package frc.robot.commands;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;
import java.awt.geom.Point2D;

public class BangBangDriveCommand extends Command{
    Drivetrain driv;
    Pose2d setpoint;
    int tim;
    public BangBangDriveCommand(Drivetrain drev, Pose2d setpoint){
        driv = drev;
        this.setpoint = setpoint;
    }

    @Override
    public void initialize(){
        driv.resetEncoders();
        tim = 0;
    }

    @Override
    public void execute(){
        tim ++;
        if (tim <= 250){
            driv.arcadeDrive(0, normalize(calculateTurnAngle(setpoint, (driv.getGyroAngle().getDegrees())), -180, 180));
        }else{
            driv.arcadeDrive(1, normalize(calculateTurnAngle(setpoint, (driv.getGyroAngle().getDegrees())), -180, 180));

        }

    }
    @Override
    public void end(boolean interrupted){
        driv.arcadeDrive(0, 0);
    }

    public static double calculateTurnAngle(Pose2d target, double startingAngle) {
        double targetAngle = Math.toDegrees(Math.atan2(target.getY(), target.getX()));
        
        double turnAngle = targetAngle - startingAngle;

        while (turnAngle > 180) {
            turnAngle -= 360;
        }
        while (turnAngle < -180) {
            turnAngle += 360;
        }

        return turnAngle;
    }
    
    @Override
    public boolean isFinished(){
        if (getDistance() < .5){
            return true;
        }
        return false;
    }

    public static double normalize(double value, double min, double max) {
        return Math.max(-1, Math.min(1, value));
    }


    private double getDistance(){
        return Math.sqrt(setpoint.getX() * setpoint.getX() + setpoint.getY() * setpoint.getY());
    }
}

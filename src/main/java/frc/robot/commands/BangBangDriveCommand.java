package frc.robot.commands;

import edu.wpi.first.math.Pair;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;
import java.awt.geom.Point2D;

public class BangBangDriveCommand extends Command{
    Drivetrain driv;
    Point2D setpoint;
    public BangBangDriveCommand(Drivetrain drev, Point2D setpoint){
        driv = drev;
        this.setpoint = setpoint;
    }

    @Override
    public void initialize(){
        driv.resetEncoders();
    }

    @Override
    public void execute(){
        

    }

    private double calculateTrajectory(){
        double angle = Math.atan2(setpoint.getY(), setpoint.getX());
        if ((setpoint.getY() > 1) && (setpoint.getX() > 1){

        }
        
    }

    private double getDistance(){
        return setpoint.distance(0, 0);
    }
}

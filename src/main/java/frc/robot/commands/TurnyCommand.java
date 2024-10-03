package frc.robot.commands;

import java.awt.geom.Point2D;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.subsystems.Drivetrain;

public class TurnyCommand extends BangBangDriveCommand{
    int spins;

    
    public TurnyCommand(int spins, Drivetrain driv){
        super(driv, new Pose2d(new Translation2d(0, 0), new Rotation2d(0)));
        this.spins = spins;
    }

    @Override
    public void initialize(){
        driv.resetEncoders();
        tim = 50 * spins;

    }

    @Override
    public void execute(){
        driv.arcadeDrive(0, 1);
    }

    @Override
    public boolean isFinished(){
        if (tim <= 0){
            return true;
        }
        return false;
    }
}

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class BangBangDriveCommand extends Command{
    protected Drivetrain driv;
    private Pose2d setpoint;
    protected int tim;
    private final double finishDistance = .5;
    private final int rotateTime = 100;

    public BangBangDriveCommand(Drivetrain drev, Pose2d setpoint){
        driv = drev;
        this.setpoint = setpoint;

    }

    @Override
    public void initialize(){
        tim = 0;
    }

    @Override
    public void execute(){
        tim ++;
        if (tim <= rotateTime){
            driv.arcadeDrive(0, MathUtil.clamp(calculateTurnAngle(setpoint, (driv.getGyroAngle().getDegrees())), -180, 180));
        }else{
            driv.arcadeDrive(1, MathUtil.clamp(calculateTurnAngle(setpoint, (driv.getGyroAngle().getDegrees())), -180, 180));

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
        if (getDistance() < finishDistance){
            return true;
        }
        return false;
    }


    private double getDistance(){
        return setpoint.getTranslation().getDistance(driv.getPose().getTranslation());
    }
}

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveTwoMeters extends Command {
    /** This is the robot's drivetrain*/
    private Drivetrain drive;
    /** This is the target position */
    double target;

    /** Make the DriveTwoMeters command */
    public DriveTwoMeters (Drivetrain drive) {
        // This command uses the drivetrain
        addRequirements(drive);
        this.drive = drive;
    }

    @Override
    public void initialize() {
        // Gets the current position and adds 2 meters
        target = drive.getAveragePosition()+2;
    }
    @Override
    public void execute(){
        double power = 0.5;
        drive.tankDrive(power,power);
    }
    @Override
    public void end(boolean interrupted) {
        double power = 0;
        drive.tankDrive(power,power);
    }
    @Override
    public boolean isFinished() {
        return drive.getAveragePosition() >= target;
    }

}


package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class AutoForwardCommand extends Command {
    private final Drivetrain drivetrain; 
    private final double speed; // speed forward
    private final double duration; // time forward sec
    private final Timer timer = new Timer(); // time track command duration
    
    // constructor
    public AutoForwardCommand(Drivetrain drivetrain, double speed, double duration)  { 
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.duration = duration;
        addRequirements(drivetrain); //reserve drivetrain for this command
    }

    // called when comman is initally sceduled
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        drivetrain.tankDrive(speed, speed);
    }

    // called every 20 millsecodnds while command is runnning
    public void execute() {

    }

    // callled once command ends
    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        timer.stop();
    }

    // return true when the command should end

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(duration);
    }
}

package frc.robot.commands;

import frc.robot.subsystems.DriveSub;

public class BangBangRotateCommand extends BangBangSubsystemCommand {
    
    private DriveSub subsystem;
    private double rotations;



    public BangBangRotateCommand(DriveSub subsystem, double rotations) {
        super(subsystem, 0);
        this.subsystem = subsystem;
        this.rotations = rotations;
    }

    @Override
    public void initialize() {
      setPoint(subsystem.getPosition() + rotations);
    }
}

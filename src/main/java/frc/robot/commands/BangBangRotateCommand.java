package frc.robot.commands;

import frc.robot.subsystems.DriveSub;

public class BangBangRotateCommand extends BangBangSubsystemCommand {
    private final DriveSub subsystem;
    private final double rotations;
    public BangBangRotateCommand(DriveSub subsystem, double rotations) {
        super(subsystem, 0);
        this.subsystem = subsystem;
        this.rotations = rotations;
    }

    @Override
    public void execute() {
        subsystem.setPosition(rotations);
    }
}
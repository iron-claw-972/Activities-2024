package frc.robot.commands;

import frc.robot.subsystems.DriveSub; // Replace with your actual subsystem class

public class BangBangRotateCommand extends BangBangSubsystemCommand {
    public BangBangRotateCommand(DriveSub subsystem, double rotations) {
        super(subsystem, rotations * 360); // convert rotations to degrees
    }
}

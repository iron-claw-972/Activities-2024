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
    

        public double getPoint() {
        return rotations;
        }

    @Override
    public void initialize() {
        subsystem.resetEncoder();
        getPoint();

    }
}

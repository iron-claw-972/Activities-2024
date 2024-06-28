// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util.ShuffleBoard;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import frc.robot.subsystems.Drivetrain;

/** Add your docs here. */
public class Field {
    private Drivetrain drive;
    private Field2d field = new Field2d();
    private Pose2d chassisPose = new Pose2d();
    
    public Field(Drivetrain drive){
        this.drive = drive;
        Shuffleboard.getTab("Drive").add(field);
    } 
    
    public void updateModulePositions(){
        
        if (drive.getPose() != null){
            chassisPose = drive.getPose();
        }
    }

    public void updateFeild(){
        updateModulePositions();
        field.setRobotPose(chassisPose);
    }

}

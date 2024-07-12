/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.FieldConstants;

public class Drivetrain extends SubsystemBase {
  
  private final TalonFX leftMotor1;
  private final TalonFX leftMotor2;
  private final TalonFX rightMotor1;
  private final TalonFX rightMotor2;

  public Drivetrain() {
    // TODO 1.1.2: Initialize motors
    leftMotor1 = null;
    rightMotor1 = null;
    leftMotor2 = null;
    rightMotor2 = null;

    // TODO 1.1.3: Make motor2s follow motor1s

    // TODO 1.2.4: Invert motors if necessary

  }

  /**
   * This will be called every 20ms, or 50 times per second
   */
  @Override
  public void periodic(){
    // TODO 1.2.2: Call tankDrive()
    
    // TODO 2.1.1: Remove all of the code in this method
  }

  /**
   * Drives the robot using tank drive controls. Tank drive is slightly easier to code but less
   * intuitive to control than arcade drive.
   *
   * @param leftPower the commanded power to the left motors (-1 to 1)
   * @param rightPower the commanded power to the right motors (-1 to 1)
   */
  public void tankDrive(double leftPower, double rightPower) {
    // TODO 1.2.1: Implement tankDrive
    
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param forward the commanded forward movement
   * @param turn the commanded turn rotation
   */
  public void arcadeDrive(double throttle, double turn) {
    // TODO 2.1.2: Implement arcadeDrive
  }

  public void resetEncoders(){
    // TODO 2.3.7: Reset encoders

  }

  // TODO 2.3.8: Implement these
  public double getLeftPosition(){
    return 0;
  }
  public double getRightPosition(){
    return 0;
  }
  public double getAveragePosition(){
    return 0;
  }

  // TODO 5.?.?: Implement this
  public void tankDriveVolts(double left, double right){
  }

  // Another method we might use later
  public Pose2d getPose(){
    return new Pose2d(FieldConstants.FIELD_LENGTH/2, FieldConstants.FIELD_WIDTH/2, new Rotation2d());
  }
}

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
import frc.robot.constants.Constants;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.FieldConstants;
import frc.robot.util.MotorFactory;

public class Drivetrain extends SubsystemBase {

  private final TalonFX m_leftMotor1;
  private final TalonFX m_leftMotor2;
  private final TalonFX m_rightMotor1;
  private final TalonFX m_rightMotor2;

  public Drivetrain() {

    m_leftMotor1 = MotorFactory.createTalonFX(DriveConstants.kLeftMotor1, Constants.RIO_CAN);
    m_leftMotor2 = MotorFactory.createTalonFX(DriveConstants.kLeftMotor2, Constants.RIO_CAN);
    m_rightMotor1 = MotorFactory.createTalonFX(DriveConstants.kRightMotor1, Constants.RIO_CAN);
    m_rightMotor2 = MotorFactory.createTalonFX(DriveConstants.kRightMotor2, Constants.RIO_CAN);

    SupplyCurrentLimitConfiguration supplyCurrentLimit = new SupplyCurrentLimitConfiguration(true, 40, 45, 1);

    m_leftMotor1.configSupplyCurrentLimit(supplyCurrentLimit);
    m_leftMotor2.configSupplyCurrentLimit(supplyCurrentLimit);
    m_rightMotor1.configSupplyCurrentLimit(supplyCurrentLimit);
    m_rightMotor2.configSupplyCurrentLimit(supplyCurrentLimit);

    m_leftMotor2.follow(m_leftMotor1);
    m_rightMotor2.follow(m_rightMotor1);
  }

  /**
   * Drives the robot using tank drive controls Tank drive is slightly easier to code but less
   * intuitive to control, so this is here as an example for now
   *
   * @param leftPower the commanded power to the left motors
   * @param rightPower the commanded power to the right motors
   */
  public void tankDrive(double leftPower, double rightPower) {
    m_leftMotor1.set(ControlMode.PercentOutput, leftPower);
    m_rightMotor1.set(ControlMode.PercentOutput, rightPower);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param forward the commanded forward movement
   * @param turn the commanded turn rotation
   */
  public void arcadeDrive(double throttle, double turn) {
    m_leftMotor1.set(ControlMode.PercentOutput, throttle + turn);
    m_rightMotor1.set(ControlMode.PercentOutput, throttle - turn);
  }

  public void tankDriveVolts(double left, double right){
    kLeftMotor1.setVoltage(left);
    kRightMotor1.setVoltage(right);
  }
  public Pose2d getPose(){
    return new Pose2d(FieldConstants.FIELD_LENGTH/2, FieldConstants.FIELD_WIDTH/2, new Rotation2d());
  }
  public void resetOdometry(Pose2d newPose){}
}

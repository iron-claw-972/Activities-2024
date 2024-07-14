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

  // TODO 5.1.5: Create FeedForward and PIDs

  // TODO 5.2.1: Create DifferentialDriveKinematics


  public Drivetrain() {
    // TODO 1.1.2: Initialize motors
    leftMotor1 = null;
    rightMotor1 = null;
    leftMotor2 = null;
    rightMotor2 = null;

    // TODO 1.1.3: Set motors to brake mode

    // TODO 1.1.4: Make motor2s follow motor1s

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

  public void tankDriveVolts(double left, double right){
    // TODO 5.1.1: Implement this

  }

  // TODO 5.2.2: 
  public double getLeftSpeed(){
    return 0;
  }
  public double getRightSpeed(){
    return 0;
  }

  public void feedForwardDrive(double throttle, double turn){
    // TODO 5.2.3: Create wheel speeds

    // TODO 5.2.4: Calculate voltages and call tankDriveVolts()

  }

  // Another method we might use later
  public Pose2d getPose(){
    return new Pose2d(FieldConstants.FIELD_LENGTH/2, FieldConstants.FIELD_WIDTH/2, new Rotation2d());
  }
}

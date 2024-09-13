package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.Constants;
import frc.robot.constants.DriveConstants;

public class Drivetrain extends SubsystemBase {
  
  // private TalonFX leftMotor1;
  // private TalonFX leftMotor2;
  // private TalonFX rightMotor1;
  // private TalonFX rightMotor2;
  CANSparkMax leftMotor1;
  CANSparkMax rightMotor1;

  DifferentialDrivetrainSim driveTrainSim;

  // TODO 2.2.1: Create gyro (AHRS)

  // TODO 2.2.3: Create DifferentialDriveKinematics
  AHRS ahrs = new AHRS(SPI.Port.kMXP);
  // TODO 2.2.4: Create DifferentialDrivePoseEstimator
  DifferentialDrivePoseEstimator drivePoseEstimator = new DifferentialDrivePoseEstimator(null, getGyroAngle(), getLeftPosition(), getAveragePosition(), getPose());
  // TODO 6.1.5: Create Feedforward and PIDs


  public Drivetrain() {
    // TODO 1.1.2: Initialize motors
    if (RobotBase.isReal()) {
      leftMotor1 = new CANSparkMax(DriveConstants.LEFT_MOTOR_1_ID, MotorType.kBrushless);
      rightMotor1 = new CANSparkMax(DriveConstants.RIGHT_MOTOR_1_ID, MotorType.kBrushless);
      // TODO 1.1.3: Set motors to brake mode
      leftMotor1.setIdleMode(IdleMode.kBrake);
      rightMotor1.setIdleMode(IdleMode.kBrake);
    }
    // TODO 1.1.4: Make motor2s follow motor1s
    
    // TODO 1.2.4: Invert motors if necessary

    // TODO 2.1.1: Create DifferentialDriveSim if the robot isn't real
    else {
        driveTrainSim = new DifferentialDrivetrainSim(
        DriveConstants.DRIVETRAIN_PLANT,
        DriveConstants.MOTOR, 
        DriveConstants.GEAR_RATIO, 
        DriveConstants.TRACK_WIDTH, 
        DriveConstants.WHEEL_DIAMETER/2, 
        DriveConstants.MEASUREMENT_STD_DEVS
      );
    }
  }

  /**
   * This will be called every 20ms, or 50 times per second
   */
  @Override
  public void periodic() {
    // TODO 2.2.5: Update odometry
    drivePoseEstimator.update(getGyroAngle(), getLeftPosition(), getRightPosition());
    // TODO 1.2.2: Call tankDrive()
    tankDrive(Robot.driver.getLeftTranslation(), Robot.driver.getRightTranslation());
    // TODO 3.1.1: Remove all of the tank drive code in this method

    // TODO 2.1.3: Update sim if in simulation
    if (RobotBase.isSimulation() == false) {
      driveTrainSim.update(Constants.LOOP_TIME);
    }
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
    if (RobotBase.isReal()) {
      leftMotor1.set((leftPower) * .25);
      rightMotor1.set((rightPower) * .25);
    }

    else {
      driveTrainSim.setInputs(leftPower * Constants.ROBOT_VOLTAGE, rightPower * Constants.ROBOT_VOLTAGE);
    }
    // TODO 2.1.2: If in sim, set sim inputs    
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param forward the commanded forward movement
   * @param turn the commanded turn rotation
   */
  public void arcadeDrive(double throttle, double turn) {
    // TODO 3.1.2: Implement arcadeDrive
    
  }

  public Pose2d getPose(){
    // TODO 2.2.6: Implement this method
    return new Pose2d();
  }

  public void resetEncoders(){
    // TODO 3.3.7: Reset encoders

  }

  // TODO 2.2.2: Implement these 4 methods
  public double getLeftPosition(){
    return (leftMotor1.getEncoder().getPosition()) * (Math.PI * DriveConstants.WHEEL_DIAMETER);
  }
  public double getRightPosition(){
    return rightMotor1.getEncoder().getPosition() * (Math.PI * DriveConstants.WHEEL_DIAMETER);
  }
  public double getAveragePosition(){
    return 0;
  }
  public Rotation2d getGyroAngle(){
    if (RobotBase.isReal()) {
      return ahrs.getRotation2d();
    }
    else {
      return driveTrainSim.getHeading();
    }
  }

  public void tankDriveVolts(double left, double right){
    // TODO 6.1.1: Implement this

  }

  // TODO 6.2.1: Implement these 2 methods
  public double getLeftSpeed(){
    return 0;
  }
  public double getRightSpeed(){
    return 0;
  }

  public void feedforwardDrive(double throttle, double turn){
    // TODO 6.2.2: Create wheel speeds

    // TODO 6.2.3: Calculate voltages and call tankDriveVolts()

  }
}

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.constants.Constants;
import frc.robot.constants.DriveConstants;

import com.ctre.phoenix6.configs.DifferentialConstantsConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  
  private CANSparkMax leftMotor1;
  private CANSparkMax leftMotor2;
  private CANSparkMax rightMotor1;
  private CANSparkMax rightMotor2;

  // TODO 2.1.1: Create DifferentialDrivetrainSim object (don't define it here)
  private DifferentialDrivetrainSim driveSim;

  // TODO 2.2.1: Create gyro (AHRS)
  private AHRS gyro;

  // TODO 2.2.3: Create DifferentialDriveKinematics
  private DifferentialDriveKinematics kinematics;

  // TODO 2.2.4: Create DifferentialDrivePoseEstimator
  private DifferentialDrivePoseEstimator pose;
  private Field2d m_field;

  // TODO 6.1.5: Create Feedforward and PIDs
  // Creates a PIDController with gains kP, kI, and kD
 PIDController pid;


  public Drivetrain() {

    // TODO 1.1.2: Initialize motors
      leftMotor1 = new CANSparkMax(DriveConstants.LEFT_MOTOR_1_ID, MotorType.kBrushless);
      leftMotor2 = new CANSparkMax(DriveConstants.LEFT_MOTOR_2_ID, MotorType.kBrushless);
      rightMotor1 = new CANSparkMax(DriveConstants.RIGHT_MOTOR_1_ID, MotorType.kBrushless);
      rightMotor2 = new CANSparkMax(DriveConstants.RIGHT_MOTOR_2_ID, MotorType.kBrushless);

    // TODO 1.1.3: Set motors to brake mode
    leftMotor1.setIdleMode(IdleMode.kBrake);
    leftMotor2.setIdleMode(IdleMode.kBrake);
    rightMotor1.setIdleMode(IdleMode.kBrake);
    rightMotor2.setIdleMode(IdleMode.kBrake);
  
    // TODO 1.1.4: Make motor2s follow motor1s

    // TODO 1.2.4: Invert motors if necessary

    // TODO 2.1.1: Define DifferentialDrivetrainSim if the robot isn't real
    driveSim = new DifferentialDrivetrainSim(DriveConstants.DRIVETRAIN_PLANT, DriveConstants.MOTOR, DriveConstants.GEAR_RATIO, DriveConstants.TRACK_WIDTH, DriveConstants.WHEEL_DIAMETER/2.0, DriveConstants.MEASUREMENT_STD_DEVS);

    gyro = new AHRS(SPI.Port.kMXP);

    kinematics = new DifferentialDriveKinematics(DriveConstants.TRACK_WIDTH);

    pose = new DifferentialDrivePoseEstimator(kinematics, getGyroAngle(), getLeftPosition(), getAveragePosition(), new Pose2d(2.0,2.0,new Rotation2d()));
    
    m_field = new Field2d();

    SmartDashboard.putData("Field", m_field);

    double kP = 0.1, kI = 0, kD = 0;
    pid = new PIDController(kP, kI, kD);
  }

   /**
   * This will be called every 20ms, or 50 times per second
   */

  @Override
  public void periodic(){
    // TODO 2.2.5: Update odometry
    pose.update(getGyroAngle(), getLeftPosition(), getRightPosition());
    m_field.setRobotPose(pose.getEstimatedPosition());

    // TODO 1.2.2: Call tankDrive()
    //arcadeDrive(Robot.driver.getForwardTranslation(), Robot.driver.getTurn());
    //tankDrive(Robot.driver.getLeftTranslation(), Robot.driver.getRightTranslation());
    if(!Robot.isReal()){
      driveSim.update(Constants.LOOP_TIME);
    }
  }

  public void simulationPeriodic() {
    // Set the inputs to the system. Note that we need to convert
    // the [-1, 1] PWM signal to voltage by multiplying it by the
    // robot controller voltage.


    //arcadeDrive(Robot.driver.getForwardTranslation(), Robot.driver.getTurn());

    //tankDrive(Robot.driver.getLeftTranslation(), Robot.driver.getRightTranslation());
    // Advance the model by 20 ms. Note that if you are running this
    // subsystem in a separate thread or have changed the nominal timestep
    // of TimedRobot, this value needs to match it.
    driveSim.update(0.02);
    // Update all of our sensors.
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
    if(Robot.isReal()){
    leftMotor1.set(leftPower*0.25);
    rightMotor1.set(rightPower*0.25);
    }
    else{
    driveSim.setInputs(leftPower*Constants.ROBOT_VOLTAGE*0.25,rightPower*Constants.ROBOT_VOLTAGE*0.25);
    }

  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param forward the commanded forward movement
   * @param turn the commanded turn rotation
   */
  public void arcadeDrive(double throttle, double turn) {
    // TODO 3.1.2: Implement arcadeDrive
    tankDrive(throttle+turn, throttle-turn);
  }

  public Pose2d getPose(){
    // TODO 2.2.6: Implement this method
    return pose.getEstimatedPosition();
  }

  public void resetEncoders(){
    // TODO 3.3.7: Reset encoders

  }

  // TODO 2.2.2: Implement these 4 methods
  public double getLeftPosition(){
    return driveSim.getLeftPositionMeters();
  }
  public double getRightPosition(){
    return driveSim.getRightPositionMeters();
  }
  public double getAveragePosition(){
    return 0;
  }
  public Rotation2d getGyroAngle(){
    return driveSim.getHeading();
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

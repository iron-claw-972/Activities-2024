package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.Constants;
import frc.robot.constants.DriveConstants;

public class Drivetrain extends SubsystemBase {
  
  private CANSparkMax leftMotor1;
  private CANSparkMax rightMotor1;

  // TODO 2.1.1: Create DifferentialDrivetrainSim object (don't define it here)
  private DifferentialDrivetrainSim sim;

  // TODO 2.2.1: Create gyro (AHRS)
  private AHRS gyro = new AHRS(SPI.Port.kMXP);

  // TODO 2.2.3: Create DifferentialDriveKinematics
  private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(DriveConstants.TRACK_WIDTH);

  // TODO 2.2.4: Create DifferentialDrivePoseEstimator
  private DifferentialDrivePoseEstimator odometry = new DifferentialDrivePoseEstimator(kinematics, new Rotation2d(), 0, 0, new Pose2d());

  // TODO 6.1.5: Create Feedforward and PIDs


  public Drivetrain() {
    // TODO 1.1.2: Initialize motors
    if(RobotBase.isReal()){
      leftMotor1 = new CANSparkMax(DriveConstants.LEFT_MOTOR_1_ID, MotorType.kBrushless);
      rightMotor1 = new CANSparkMax(DriveConstants.RIGHT_MOTOR_1_ID, MotorType.kBrushless);

      // TODO 1.1.3: Set motors to brake mode
      leftMotor1.setIdleMode(IdleMode.kBrake);
      rightMotor1.setIdleMode(IdleMode.kBrake);
    
      // TODO 1.1.4: Make motor2s follow motor1s

      // TODO 1.2.4: Invert motors if necessary
      rightMotor1.setInverted(true);
    }else{
      // TODO 2.1.1: Define DifferentialDrivetrainSim if the robot isn't real
      sim = new DifferentialDrivetrainSim(DriveConstants.DRIVETRAIN_PLANT, DriveConstants.MOTOR, DriveConstants.GEAR_RATIO, DriveConstants.TRACK_WIDTH, DriveConstants.WHEEL_DIAMETER/2, DriveConstants.MEASUREMENT_STD_DEVS);
    }
  }

   /**
   * This will be called every 20ms, or 50 times per second
   */
  @Override
  public void periodic(){
    // TODO 2.2.5: Update odometry
    odometry.update(getGyroAngle(), getLeftPosition(), getRightPosition());

    // TODO 1.2.2: Call tankDrive()
    tankDrive(Robot.driver.getLeftTranslation(), Robot.driver.getRightTranslation());

    // TODO 3.1.1: Remove all of the tank drive code in this method

    // TODO 2.1.3: Update sim if in simulation
    if(RobotBase.isSimulation()){
      sim.update(Constants.LOOP_TIME);
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
    if(RobotBase.isReal()){
      leftMotor1.set(leftPower*0.25);
      rightMotor1.set(rightPower*0.25);
    }else{
      // TODO 2.1.2: If in sim, set sim inputs
      sim.setInputs(leftPower*0.25*Constants.ROBOT_VOLTAGE, rightPower*0.25*Constants.ROBOT_VOLTAGE);
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
    
  }

  public Pose2d getPose(){
    // TODO 2.2.6: Implement this method
    return odometry.getEstimatedPosition();
  }

  public void resetEncoders(){
    // TODO 3.3.7: Reset encoders
    if(RobotBase.isReal()){
      leftMotor1.getEncoder().setPosition(0);
      rightMotor1.getEncoder().setPosition(0);
    }else{
      sim.setState(VecBuilder.fill(0, 0, sim.getHeading().getRadians(), sim.getLeftVelocityMetersPerSecond(), sim.getRightVelocityMetersPerSecond(), 0, 0));
    }
    odometry.resetPosition(getGyroAngle(), getLeftPosition(), getRightPosition(), getPose());
  }

  // TODO 2.2.2: Implement these 4 methods
  public double getLeftPosition(){
    if(RobotBase.isReal()){
      return leftMotor1.getEncoder().getPosition()/DriveConstants.GEAR_RATIO*Math.PI*DriveConstants.WHEEL_DIAMETER;
    }else{
      return sim.getLeftPositionMeters();
    }
  }
  public double getRightPosition(){
    if(RobotBase.isReal()){
      return rightMotor1.getEncoder().getPosition()/DriveConstants.GEAR_RATIO*Math.PI*DriveConstants.WHEEL_DIAMETER;
    }else{
      return sim.getRightPositionMeters();
    }
  }
  public double getAveragePosition(){
    return (getLeftPosition()+getRightPosition())/2;
  }
  public Rotation2d getGyroAngle(){
    if(RobotBase.isReal()){
      return gyro.getRotation2d();
    }else{
      return sim.getHeading();
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

package frc.robot.constants;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.numbers.N7;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;

public class DriveConstants {

  // TODO 1.1.1: Set these
  public static final int LEFT_MOTOR_1_ID = 11;
  public static final int RIGHT_MOTOR_1_ID = 6;
  public static final int LEFT_MOTOR_2_ID = -1;
  public static final int RIGHT_MOTOR_2_ID = -1;

  // TODO 6.1.3: Add FF and PID constants


  // The gear ratio on the drivetrain
  public static final double GEAR_RATIO = 62.0/9;

  // Diameter of the wheels in meters
  public static final double WHEEL_DIAMETER = Units.inchesToMeters(4);

  // Width of the robot in meters.
  public static final double TRACK_WIDTH = Units.inchesToMeters(24.405);

  // The motor type and amount, used for simulation
  @SuppressWarnings("unused")
  public static final DCMotor MOTOR = DCMotor.getNEO(LEFT_MOTOR_2_ID < 0 ? 1 : 2);

  // Drivetrain dynamics, used for simulation
  public static final LinearSystem<N2, N2, N2> DRIVETRAIN_PLANT = LinearSystemId.createDrivetrainVelocitySystem(
    MOTOR,
    20,
    WHEEL_DIAMETER/2,
    TRACK_WIDTH/2,
    4,
    GEAR_RATIO
  );

  // Simulation noise standard deviations
  public static final Matrix<N7, N1> MEASUREMENT_STD_DEVS = VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005);
}

package frc.robot.constants;

import edu.wpi.first.math.util.Units;

public class DriveConstants {

  // TODO 1.1.1: Set these
  public static final int LEFT_MOTOR_1_ID = -1;
  public static final int LEFT_MOTOR_2_ID = -1;
  public static final int RIGHT_MOTOR_1_ID = -1;
  public static final int RIGHT_MOTOR_2_ID = -1;

  // TODO 5.1.3: Add FF and PID constants
  

  // The gear ratio on the drivetrain
  public static final double GEAR_RATIO = 62.0/9;

  // Diameter of the wheels in meters
  public static final double WHEEL_DIAMETER = Units.inchesToMeters(4);

  // Width of the robot in meters.
  public static final double TRACK_WIDTH = Units.inchesToMeters(24.405);
}

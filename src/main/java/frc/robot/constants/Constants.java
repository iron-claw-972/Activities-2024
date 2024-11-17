package frc.robot.constants;

public class Constants {

    // constants:

    public static final double GRAVITY_ACCELERATION = 9.8;
    public static final double ROBOT_VOLTAGE = 12.0;
    public static final double LOOP_TIME = 0.02;

    public static final double CANCODER_RESOLUTION = 4096;

    // CAN bus names
    public static final String CANIVORE_CAN = "CANivore";
    public static final String RIO_CAN = "rio";

    public static final boolean DO_LOGGING = false;
    public static final boolean USE_TELEMETRY = true;

    // port for the LED controller, the Blinkin
    public static final int BLINKIN_PORT = 0;

    // falcon constants:

    public static final int FIRMWARE_VERSION = 5633; // version 22.1.1.0
    public static final boolean BREAK_ON_WRONG_FIRMWARE = false;

    public static final double RESOLUTION = 2048;
    // Kraken Speed
    public static double MAX_RPM = 5800.0; // Rotations per minute

    /*
     * Talon Stator / Supply Limits explanation
     * Supply current is current that's being drawn at the input bus voltage. Stator
     * current is current that's being drawn by the motor.
     * Supply limiting (supported by Talon FX and SRX) is useful for preventing
     * breakers from tripping in the PDP.
     * Stator limiting (supported by Talon FX) is useful for limiting
     * acceleration/heat.
     */

    // These are the default values

    // Stator
    public static final boolean STATOR_LIMIT_ENABLE = false; // enabled?
    public static final double STATOR_CURRENT_LIMIT = 100; // Limit(amp)
    public static final double STATOR_TRIGGER_THRESHOLD = 100; // Trigger Threshold(amp)
    public static final double STATOR_TRIGGER_DURATION = 0; // Trigger Threshold Time(s)

    // Supply
    public static final boolean SUPPLY_LIMIT_ENABLE = false; // enabled?
    public static final double SUPPLY_CURRENT_LIMIT = 40; // Limit(amp), current to hold after trigger hit
    public static final double SUPPLY_TRIGGER_THRESHOLD = 55; // (amp), amps to activate trigger
    public static final double SUPPLY_TRIGGER_DURATION = 3; // (s), how long after trigger before reducing
    // OIConstants:

    public static final int DRIVER_JOY = 0;

    public static final int OPERATOR_JOY = 1;
    public static final int TEST_JOY = 2;
    public static final int MANUAL_JOY = 3;
    public static final double DEADBAND = 0.00005;
    public static final double HEADINGDEADBAND = 0.05;

    public static final double TRANSLATIONAL_SENSITIVITY = 1;
    public static final double TRANSLATIONAL_EXPO = 2;
    public static final double TRANSLATIONAL_DEADBAND = 0.05;
    public static final double TRANSLATIONAL_SLEWRATE = 20;
    public static final boolean FIELD_RELATIVE = true;
    public static final double ROTATION_SENSITIVITY = 1;

    public static final double ROTATION_EXPO = 4;
    public static final double ROTATION_DEADBAND = 0.01;
    public static final double ROTATION_SLEWRATE = 10;
    public static final double HEADING_SENSITIVITY = 4;

    public static final double HEADING_EXPO = 2;
    public static final double HEADING_DEADBAND = 0.05;
    public static final boolean CONSTANT_HEADING_MAGNITUDE = false;
    public static final boolean INVERT = false;

}

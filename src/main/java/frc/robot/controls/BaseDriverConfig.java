package frc.robot.controls;

import edu.wpi.first.math.MathUtil;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.MathUtils;

/**
 * Abstract class for different controller types.
 */
public abstract class BaseDriverConfig {

    private final Drivetrain drive;

    /**
     * @param drive               the drivetrain instance
     * @param controllerTab       the shuffleboard controller tab
     * @param shuffleboardUpdates whether to update the shuffleboard
     */
    public BaseDriverConfig(Drivetrain drive) {
        this.drive = drive;
    }

    public double getForwardTranslation() {
        return MathUtils.expoMS(MathUtil.applyDeadband(getRawForwardTranslation(), Constants.DEADBAND), 2);
    }

    public double getLeftTranslation() {
        return MathUtils.expoMS(MathUtil.applyDeadband(getRawLeftTranslation(), Constants.DEADBAND), 2);
    }

    public double getRightTranslation() {
        return MathUtils.expoMS(MathUtil.applyDeadband(getRawRightTranslation(), Constants.DEADBAND), 2);
    }

    public double getTurn() {
        return MathUtils.expoMS(MathUtil.applyDeadband(getRawTurn(), Constants.HEADINGDEADBAND), 2);
    }

    protected Drivetrain getDrivetrain() {
        return drive;
    }

    /**
     * Configures the controls for the controller.
     */
    public abstract void configureControls();

    public double getRawForwardTranslation() {
        return getRawLeftTranslation();
    }

    public abstract double getRawLeftTranslation();

    public abstract double getRawRightTranslation();

    public abstract double getRawTurn();

    public abstract boolean getIsSlowMode();
}
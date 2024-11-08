package frc.robot.controls;

import frc.robot.constants.Constants;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.Drivetrain;
import lib.controllers.GameController;
import lib.controllers.GameController.Axis;

/**
 * Driver controls for the generic game controller.
 */
public class GameControllerDriverConfig extends BaseDriverConfig {
  private final GameController controller = new GameController(Constants.DRIVER_JOY);
  private final DriveSub sub;

  public GameControllerDriverConfig(Drivetrain drive, DriveSub sub) {
      super(drive);
      this.sub = sub;
  }

  @Override
  public void configureControls() {
 }

  @Override
  public double getRawLeftTranslation() {
    // - because down is positive
    return -controller.get(Axis.LEFT_Y);
  }
  @Override
  public double getRawRightTranslation() {
    // - because down is positive
    return -controller.get(Axis.RIGHT_Y);
  }

  @Override
  public double getRawTurn() {
    return controller.get(Axis.RIGHT_X);
  }

  @Override
  public boolean getIsSlowMode() {
    return controller.RIGHT_TRIGGER_BUTTON.getAsBoolean();
  }
}

package frc.robot.controls;

import frc.robot.commands.BangBangController;
import frc.robot.commands.BangBangSpinMotor;
import frc.robot.commands.DoNothing;
import frc.robot.commands.MoveOrangeArm;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OrangeArm;
import lib.controllers.GameController;
import lib.controllers.GameController.Axis;
import lib.controllers.GameController.Button;
import lib.controllers.GameController.DPad;

/**
 * Driver controls for the generic game controller.
 */
public class GameControllerDriverConfig extends BaseDriverConfig {
  private final GameController controller = new GameController(Constants.DRIVER_JOY);
  private OrangeArm orange;


  public GameControllerDriverConfig(Drivetrain drive, OrangeArm orange) {
    super(drive);
    this.orange = orange;
  }

  @Override
  public void configureControls() {
    // TODO 4.1.1: Change to your auto command
    controller.get(Button.A).onTrue(new MoveOrangeArm(orange, 100));
    // TODO 4.1.3: Add Bang-Bang drive command

    // TODO 4.1.4: Add subsystem Bang-Bangs
    controller.get(Button.B).onTrue(new MoveOrangeArm(orange, 5));
    controller.get(Button.B).onFalse(new MoveOrangeArm(orange, -5));
    // 4.1.5 Make robot spin
    controller.get(Button.Y).onTrue(new BangBangSpinMotor(getDrivetrain(), 90));
    controller.get(Button.Y).onFalse(new BangBangSpinMotor(getDrivetrain(), 0));
    // TODO 4.2.2: Make robot spin while a button is pressed
    controller.get(Button.X).whileTrue(new BangBangSpinMotor(getDrivetrain(), 200));
    controller.get(Button.X).whileFalse(new BangBangSpinMotor(getDrivetrain(), 0));
    // TODO 4.3.1: Add more triggers
    controller.get(DPad.UP).whileTrue(new MoveOrangeArm(orange, 6));
    controller.get(DPad.DOWN).whileFalse(new MoveOrangeArm(orange, -6));
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

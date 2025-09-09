package frc.robot.controls;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.commands.DoNothing;
import frc.robot.commands.PIDCommands;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.MotorSubsystem;
import lib.controllers.GameController;
import lib.controllers.GameController.Axis;
import lib.controllers.GameController.Button;

/**
 * Driver controls for the generic game controller.
 */
public class GameControllerDriverConfig extends BaseDriverConfig {
  private final GameController controller = new GameController(Constants.DRIVER_JOY);
  private final MotorSubsystem motorSubsystem;

  public GameControllerDriverConfig(Drivetrain drive, MotorSubsystem motorSubsystem) {
    super(drive); 
    this.motorSubsystem = motorSubsystem;
  }

  @Override
  public void configureControls() {
    // TODO 4.1.1: Change to your auto command
    controller.get(Button.A).onTrue(new DoNothing());
    // TODO 4.1.3: Add Bang-Bang drive command
    controller.get(Button.B).onTrue(new PIDCommands(motorSubsystem, 2));
    // TODO 4.1.4: Add subsystem Bang-Bangs

    // TODO 4.2.2: Make robot spin while a button is pressed

    // TODO 4.3.1: Add more triggers
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

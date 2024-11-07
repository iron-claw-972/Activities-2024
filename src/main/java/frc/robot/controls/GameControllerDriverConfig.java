package frc.robot.controls;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.commands.AutoForwardCommand;
import frc.robot.commands.AutoSquareCommand;
import frc.robot.commands.AutoTurnCommand;
import frc.robot.commands.BangBangRotateCommand;
import frc.robot.commands.BangBangSubsystemCommand;
import frc.robot.commands.DoNothing;
import frc.robot.constants.Constants;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.Drivetrain;
import lib.controllers.GameController;
import lib.controllers.GameController.Axis;
import lib.controllers.GameController.Button;

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
    // TODO 4.1.1: Change to your auto command
    controller.get(Button.LB).onTrue(new AutoForwardCommand(drive, -1.0, 3.0));
    controller.get(Button.RB).onTrue(new AutoForwardCommand(drive, 1.0, 3.0));
    controller.get(Button.B).onTrue(new AutoSquareCommand(drive));
    controller.get(Button.X).onTrue(new AutoTurnCommand(drive, 152.17)); //turns 180 degrees (angles for some reason broken)

    // TODO 4.1.3: Add Bang-Bang drive command
    controller.get(Button.START).onTrue(new BangBangSubsystemCommand(sub, 1.0));
    controller.get(Button.BACK).onTrue(new BangBangRotateCommand(sub, 3.0));

    // TODO 4.1.4: Add subsystem Bang-Bangs
    controller.get(Button.START).onFalse(new BangBangSubsystemCommand(sub, 0));

    // TODO 4.2.2: Make robot spin while a button is pressed
    controller.get(Button.A).whileTrue(new RunCommand(() -> drive.arcadeDrive(0, 1.0), drive));
    controller.get(Button.Y).whileTrue(new RunCommand(() -> drive.arcadeDrive(0, -1.0), drive));

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

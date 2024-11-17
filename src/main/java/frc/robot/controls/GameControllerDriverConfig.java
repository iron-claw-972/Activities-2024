package frc.robot.controls;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.AutoForwardCommand;
import frc.robot.commands.AutoSquareCommand;
import frc.robot.commands.AutoTurnCommand;
import frc.robot.commands.BangBangRotateCommand;
import frc.robot.commands.BangBangSubsystemCommand;
import frc.robot.commands.SubsystemPIDCommand;
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
    controller.get(Button.LB).onTrue(new AutoForwardCommand(getDrivetrain(), -1.0, 3.0));
    controller.get(Button.RB).onTrue(new AutoForwardCommand(getDrivetrain(), 1.0, 3.0));
    controller.get(Button.B).onTrue(new AutoSquareCommand(getDrivetrain()));
    controller.get(Button.X).onTrue(new AutoTurnCommand(getDrivetrain(), 152.17)); // turns 180 degrees**

    // TODO 4.1.3: Add Bang-Bang drive command
    controller.get(Button.START).onTrue(new SubsystemPIDCommand(sub, 1.0));
    controller.get(Button.BACK).onTrue(new SubsystemPIDCommand(sub, -1.0));

    // TODO 4.1.4: Add subsystem Bang-Bangs
    controller.get(Button.START).onFalse(new BangBangSubsystemCommand(sub, 0));

    // TODO 4.2.2: Make robot spin while a button is pressed
    controller.get(Button.A).whileTrue(new RunCommand(() -> getDrivetrain().arcadeDrive(0, 1.0), getDrivetrain()));
    controller.get(Button.Y).whileTrue(new RunCommand(() -> getDrivetrain().arcadeDrive(0, -1.0), getDrivetrain()));

    // TODO 4.3.1: Add more triggers

    // SequentialCommandGroup: Drive forward, then turn 90 degrees
    controller.get(Button.RIGHT_JOY).onTrue(new SequentialCommandGroup(
        new AutoForwardCommand(getDrivetrain(), 1.0, 3.0), // drive forward
        new AutoTurnCommand(getDrivetrain(), 64), // turn 90 degrees**
        new InstantCommand(() -> getDrivetrain().arcadeDrive(0, 0), getDrivetrain()) // stop at the end
    ));
    // ParallelCommandGroup: Drive forward and rotate simultaneously
    controller.get(Button.LEFT_JOY).onTrue(new ParallelCommandGroup(
        new AutoForwardCommand(getDrivetrain(), 1.0, 2.0),
        new BangBangRotateCommand(sub, 1.0)));

    // ConditionalCommand: Drive forward if LEFT_TRIGGER_BUTTON is pressed,
    // otherwise turn 90 degrees
    controller.get(controller.LEFT_TRIGGER_BUTTON).onTrue(
        new ConditionalCommand(
            new AutoForwardCommand(getDrivetrain(), 1.0, 2.0),
            new AutoTurnCommand(getDrivetrain(), -64), // turn 90 degrees**
            () -> getDrivetrain().getPose().getX() > 5));

    // PrintCommand: Print message when DPAD_UP is pressed
    controller.get(GameController.DPad.UP).onTrue(new PrintCommand("DPAD_UP button pressed!"));

    // WaitCommand: Wait for 1 second, then drive forward
    controller.get(GameController.DPad.LEFT).onTrue(new WaitCommand(1.0)
        .andThen(new AutoForwardCommand(getDrivetrain(), 1.0, 2.0)));

    // WaitUntilCommand: Wait until RIGHT_TRIGGER_BUTTON is pressed, then turn
    controller.get(GameController.DPad.DOWN).onTrue(new WaitUntilCommand(
        controller.get(controller.RIGHT_TRIGGER_BUTTON))
        .andThen(new AutoTurnCommand(getDrivetrain(), 152.17)));
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

package frc.robot.controls;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.BangBangDriveCommand;
import frc.robot.commands.FunnyCommand;
import frc.robot.commands.TingBangBang;
import frc.robot.commands.TurnyCommand;
import frc.robot.commands.TingPID;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Ting;
import lib.controllers.GameController;
import lib.controllers.GameController.Axis;
import lib.controllers.GameController.Button;
import lib.controllers.GameController.DPad;

/**
 * Driver controls for the generic game controller.
 */
public class GameControllerDriverConfig extends BaseDriverConfig {
  private final GameController controller = new GameController(Constants.DRIVER_JOY);

  public GameControllerDriverConfig(Drivetrain drive) {
    super(drive);
  }

  @Override
  public void configureControls() {
    // TODO 4.1.1: Change to your auto command
    controller.get(Button.B).onTrue(new FunnyCommand(getDrivetrain(), new Ting(40)));
    // TODO 4.1.3: Add Bang-Bang drive command
    controller.get(Button.A).onTrue(new BangBangDriveCommand(getDrivetrain(), new Pose2d(10,10,new Rotation2d(0))));
    // TODO 4.1.4: Add subsystem Bang-Bangs
    controller.get(Button.X).onTrue(new TingPID(new Ting(16), 45)).onFalse(new TingBangBang(new Ting(13), (double) 0));
    controller.get(Button.Y).whileTrue(new TurnyCommand(1, new Ting(16)));
    // TODO 4.2.2: Make robot spin while a button is pressed
    controller.get(Button.LB).whileTrue(new RunCommand(() -> {getDrivetrain().tankDrive(-1, 1);}, getDrivetrain()));
    // TODO 4.3.1: Add more triggers
    controller.get(Button.START).onTrue(new ParallelCommandGroup(new TurnyCommand(1, new Ting(16)), new RunCommand(() -> {getDrivetrain().tankDrive(1, 1);}, getDrivetrain())));
    controller.get(DPad.DOWN).onTrue(new SequentialCommandGroup());
    controller.get(DPad.UP).onTrue(new ConditionalCommand(null, null, () -> (getDrivetrain().getLeftSpeed() >= 0)));
    controller.get(DPad.LEFT).onTrue(new PrintCommand("hello there!"));
    controller.get(DPad.RIGHT).onTrue(new WaitCommand(6));
    controller.get(controller.LEFT_TRIGGER_BUTTON).onTrue(new WaitUntilCommand(40));


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

package frc.robot.controls;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.DoNothing;
import frc.robot.constants.Constants;
import lib.controllers.GameController;
import lib.controllers.GameController.Button;

public class Driver {
  private static GameController driver = new GameController(Constants.DRIVER_JOY);

  public static void configureControls() {
    
    // example button binding implementation
    driver.get(Button.A).onTrue(new DoNothing());

    // example test type implementation
    // tests drivetrain, when in TEST_DRIVE test mode and 
    driver.get(Button.B).onTrue(
      new SequentialCommandGroup(
        new RunCommand(() -> Robot.drive.tankDrive(0.5, 0.5), Robot.drive).withTimeout(1),
        new RunCommand(() -> Robot.drive.tankDrive(-0.5, -0.5), Robot.drive).withTimeout(1),
        new RunCommand(() -> Robot.drive.tankDrive(0.5, -0.5), Robot.drive).withTimeout(1),
        new RunCommand(() -> Robot.drive.tankDrive(-0.5, 0.5), Robot.drive).withTimeout(1)
      )
    );
  }
}

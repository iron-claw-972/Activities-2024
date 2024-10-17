// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.controls.BaseDriverConfig;
import frc.robot.controls.GameControllerDriverConfig;
import frc.robot.controls.Operator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Orange;
import frc.robot.util.ShuffleBoard.ShuffleBoardManager;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command autoCommand;
  public static ShuffleBoardManager shuffleboard;
  public static Drivetrain drive;
  // TODO 2.3.9: Create variable for your subsystem
  public static BaseDriverConfig driver;
  public static Operator operator;
  public static Orange orange;

  private static boolean isTestMode = false;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    // This is really annoying so it's disabled
    DriverStation.silenceJoystickConnectionWarning(true);

    // make subsystems
    drive = new Drivetrain();
    // TODO 2.3.10: Create your subsystem
    orange = new Orange();
    shuffleboard = new ShuffleBoardManager(drive,orange);
    driver = new GameControllerDriverConfig(drive);
    operator = new Operator();
    

    driver.configureControls();
    operator.configureControls();

    // TODO 3.1.6: Set the drivetrain's default command
    
    drive.setDefaultCommand(new ArcadeDriveCommand(drive));
    

    // TODO 4.2.1: Change default command to use RunCommand with a lambda expression
    // TODO 6.3.1: Change to Feedforward command
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    
    // Update Shuffleboard
    shuffleboard.update();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
    isTestMode = false;
  }

  @Override
  public void disabledPeriodic() {
    autoCommand = getAutonomousCommand();
  }

  /** This autonomous runs the autonomous command selected by your {@link Robot} class. */
  @Override
  public void autonomousInit() {
    isTestMode = false;
    if (autoCommand != null) {
      autoCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autoCommand != null) {
      autoCommand.cancel();
    }
    isTestMode = false;
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

    // it may be needed to disable LiveWindow (we don't use it anyway)
    //LiveWindow.setEnabled(false)

    isTestMode = true;

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return shuffleboard.getSelectedCommand();
  }

  public static boolean isTestMode() {
    return isTestMode;
  }
}

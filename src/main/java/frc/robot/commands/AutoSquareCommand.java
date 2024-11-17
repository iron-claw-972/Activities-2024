package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class AutoSquareCommand extends SequentialCommandGroup {

    public AutoSquareCommand(Drivetrain drivetrain) {
        // Each command here represents driving forward and turning 90 degrees
        addCommands(
                // Move forward 1 meter (adjust duration or distance as needed)
                new AutoForwardCommand(drivetrain, 1, 2),
                // Turn 90 degrees to the right
                new AutoTurnCommand(drivetrain, 63),

                // Move forward again
                new AutoForwardCommand(drivetrain, 1, 2),
                // Turn 90 degrees to the right
                new AutoTurnCommand(drivetrain, 63),

                // Repeat final
                new AutoForwardCommand(drivetrain, 1, 2),
                new AutoTurnCommand(drivetrain, 63),
                // This will face the original direction

                // Complete the square with the final leg
                new AutoForwardCommand(drivetrain, 1, 2),
                new AutoTurnCommand(drivetrain, 62));
    }
}

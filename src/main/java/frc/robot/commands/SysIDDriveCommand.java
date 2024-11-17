package frc.robot.commands;

import com.ctre.phoenix6.SignalLogger;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Config;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.SysId;

public class SysIDDriveCommand extends SequentialCommandGroup {

    private Config config = new Config();
    private SysId sysId;

    public SysIDDriveCommand(Drivetrain drive) {
        config = new Config(
                Units.Volts.of(0.5).per(Units.Seconds.of(1)),
                Units.Volts.of(3),
                Units.Seconds.of(6),
                (x) -> SignalLogger.writeString("state", x.toString()));
        sysId = new SysId(
                "Drivetrain",
                x -> {
                    drive.tankDriveVolts(x.magnitude(), x.magnitude());
                },
                drive,
                config);
        addCommands(
                sysId.runQuasisStatic(Direction.kForward),
                new WaitCommand(0.5),
                sysId.runQuasisStatic(Direction.kReverse),
                new WaitCommand(0.5),
                sysId.runDynamic(Direction.kForward),
                new WaitCommand(0.5),
                sysId.runDynamic(Direction.kReverse));
    }

}

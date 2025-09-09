package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DriveConstants;

public class motorSubsystem extends SubsystemBase {
    private CANSparkMax motor67;
    private PIDController pid = new PIDController(0.02, 0, 0);
    private double PositionDegrees;
    public motorSubsystem() {
        motor67 = new CANSparkMax(DriveConstants.MOTOR_67_ID, CANSparkMax.MotorType.kBrushless);
        motor67.getEncoder().setPosition(0);
        pid.setTolerance(Units.degreesToRadians(3));
    }

    public void setMotor(double speed) {
        motor67.set(speed);
    }

    public void stopMotor() {
        setMotor(0);
    }
    public double getPosition() {
        return PositionDegrees;
    }
    public void setSetpoint(double setpoint) {
        pid.reset();
        pid.setSetpoint(Units.degreesToRadians(setpoint));
    }
    public PIDController getPID() {
        return this.pid;
    }

    public boolean atSetPoint() {
        return pid.atSetpoint();
    }
    @Override
    public void periodic() {
        PositionDegrees = Units.rotationsToDegrees(motor67.getEncoder().getPosition());
        motor67.set(pid.calculate(Units.degreesToRadians(getPosition())));
    }
    
}

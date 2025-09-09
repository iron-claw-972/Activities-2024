package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DriveConstants;

public class MotorSubsystem extends SubsystemBase{
    private CANSparkMax motor7;
    private PIDController pid1;
    public MotorSubsystem(){
        this.motor7 = new CANSparkMax(DriveConstants.MOTOR_7_ID, MotorType.kBrushless);
        this.pid1 = new PIDController(0.02, 0, 0);
        motor7.getEncoder().setPosition(0);
        pid1.setTolerance(0.002);
    }
    public void SetMotor(CANSparkMax motor, double speed){
        motor.set(speed);
    }
    public void StopMotor(CANSparkMax motor){
        SetMotor(motor, 0);
    }
    public double GetPosition(CANSparkMax motor){
        return motor.getEncoder().getPosition();
    }
    public void periodic(){
        SetMotor(motor7, 0.05);
        spinTo(pid1.calculate(GetPosition(motor7)));
    }
    public void spinTo(double setPoint){
        pid1.reset();
        pid1.setSetpoint(setPoint);
    }
    public boolean atSetpoint(){
        return pid1.atSetpoint();
    }
    public PIDController getPID(){
        return pid1;
    }
}

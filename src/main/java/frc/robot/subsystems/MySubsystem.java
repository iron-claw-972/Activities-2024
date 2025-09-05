package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MySubsystem extends SubsystemBase{
    private CANSparkMax motor;
    final private int motorId=-1;

    public MySubsystem(){
        final int motorId=-1;
        motor = new CANSparkMax(motorId,MotorType.kBrushless);

        motor.getEncoder().setPosition(0);
    }

    public void setMotorSpeed(double speed){
        motor.set(speed);
    }

    public void stopMotor(){
        setMotorSpeed(0);
    }

    public double getEncoderPosition(){
        return motor.getEncoder().getPosition();
    }
}

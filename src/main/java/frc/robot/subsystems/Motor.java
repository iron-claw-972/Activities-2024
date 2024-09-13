package frc.robot.subsystems;

import javax.management.ConstructorParameters;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
    
    CANSparkMax motor;

    public Motor() {
        motor = new CANSparkMax(11, MotorType.kBrushless);
        motor.getEncoder().setPosition(0);
    }

    public void setMotorSpeed() {

    }

    public void stopMotor() {

    }

    public double getMotorPosition() {
        return motor.getEncoder().getPosition();
    }

}

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Ting extends SubsystemBase {
    CANSparkMax motor;
    public Ting(int motorId){
        motor = new CANSparkMax(motorId, MotorType.kBrushless);
        motor.getEncoder().setPosition(0);

    }

    public void periodic(){
        setMotor(.05);
    }

    public void setMotor(double speed){
        motor.set(speed);
    }

    public void stop(){
        setMotor(0);

    }

    public double getPos(){
        return motor.getEncoder().getPosition();
    }




}

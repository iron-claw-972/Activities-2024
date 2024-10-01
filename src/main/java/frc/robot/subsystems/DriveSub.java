package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

    public class DriveSub extends SubsystemBase {

        private CANSparkMax motor;

        //constructor
        public DriveSub(int motorID) {
            motor = new CANSparkMax(motorID, MotorType.kBrushless);
            
            //reset enconder to 0
            motor.getEncoder().setPosition(0);
        }

        //method to set motor speed
        public void setSpeed(double speed) {
            motor.set(speed);
        }

        //method to stop motor
        public void stop() {
            setSpeed(0);
        }

        //method to get encoder position
        public double getPosition() {
            return motor.getEncoder().getPosition();
        }

    }

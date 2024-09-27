package frc.robot.subsystems;

import javax.management.ConstructorParameters;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

public class Motor extends SubsystemBase {
    CANSparkMax motor;
    DCMotor dcMotor = DCMotor.getNEO(1);
    SingleJointedArmSim armSim = new SingleJointedArmSim(
        dcMotor, 
        1.0, 
        11, 
        2.0, 
        Double.POSITIVE_INFINITY, 
        Double.NEGATIVE_INFINITY, 
        false, 
        0
        );
    Mechanism2d mechanism2d = new Mechanism2d(100, 100);
    MechanismLigament2d mechanismLigament2d = new MechanismLigament2d("MechanismSigmaMend2d", 30, 0);

    //Constructor
    public Motor() {
        motor = new CANSparkMax(11, MotorType.kBrushless);
        motor.getEncoder().setPosition(0);
        mechanism2d.getRoot("get the root please", 50, 50).append(mechanismLigament2d);
        SmartDashboard.putData("Sim Display", mechanism2d); 
    }

    public void setMotorSpeed(double speed) {
        if (RobotBase.isSimulation()) {
            motor.set(speed * Constants.ROBOT_VOLTAGE);
        }
    }

    public void stopMotor() {
        if(RobotBase.isSimulation()) {
            setMotorSpeed(0);
        }
    }

    public double getMotorPosition() {
        if(RobotBase.isSimulation()) {
            return motor.getEncoder().getPosition() * 360;
        }
        return 0;
    }

    public void periodic() {
        mechanismLigament2d.setAngle(getMotorPosition());
        if (RobotBase.isSimulation()) {
            System.out.println(mechanismLigament2d.getAngle());
            setMotorSpeed(0.005);
        }
    }

}

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.hal.simulation.ConstBufferCallback;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

public class Ting extends SubsystemBase {
    CANSparkMax motor;
    CANSparkMax moto2;
    SingleJointedArmSim arm;
    public Mechanism2d ting1;
    MechanismLigament2d ligma;
    public Ting(int motorId){
        motor = new CANSparkMax(motorId, MotorType.kBrushless);
        motor.getEncoder().setPosition(0);
        arm = new SingleJointedArmSim(DCMotor.getFalcon500(1) , 1 , .1, .05, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false, 0);
        ting1 = new Mechanism2d(100, 100);
        ligma = new MechanismLigament2d(getName(), 35, 0);
        ting1.getRoot("pivot", 50, 50).append(ligma);


    }

    public void periodic(){
        setMotor(.05);
        ligma.setAngle(getPos());
    }

    public void setMotor(double speed){
        if (RobotBase.isSimulation()){
            arm.setInputVoltage(speed * Constants.ROBOT_VOLTAGE);
        }else{
            motor.set(speed);
        }
    }

    public void stop(){
        setMotor(0);

    }

    public double getPos(){
        if (RobotBase.isReal()){
            return Units.rotationsToDegrees(motor.getEncoder().getPosition());
        }else{
            return Units.radiansToDegrees(arm.getAngleRads());
        }
    }




}

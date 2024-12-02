package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

public class Ting extends SubsystemBase {
    TalonFX motor;
    SingleJointedArmSim arm;
    Mechanism2d ting1;
    MechanismLigament2d ligma;
    PIDController piddy;

    public Ting(int motorId){
        motor = new TalonFX(motorId);
        motor.setPosition(0);
        arm = new SingleJointedArmSim(DCMotor.getFalcon500(1) , 1 , .1, .05, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false, 0);
        ting1 = new Mechanism2d(100, 100);
        ligma = new MechanismLigament2d(getName(), 35, 0);
        ting1.getRoot("pivot", 50, 50).append(ligma);
        piddy = new PIDController(.0003, 0, 0);
        piddy.disableContinuousInput();
        piddy.setTolerance(.01, .01);


    }

    public Mechanism2d getMechanism2d(){
        return ting1;
    }

    public void periodic(){
        arm.update(Constants.LOOP_TIME);
        ligma.setAngle(getPos());
        setMotor(piddy.calculate(getPos()));
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
            return Units.rotationsToDegrees(motor.getPosition().getValue());
        }else{
            return Units.radiansToDegrees(arm.getAngleRads());
        }
    }

    public void setTarget(double angle){
        piddy.reset();
        piddy.setSetpoint(angle);
    }

    public boolean atSetpoint(){
        return piddy.atSetpoint();
    }

    public PIDController getPID(){
        return piddy;
    }


}

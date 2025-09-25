package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.Constants;

public class MySubsystem extends SubsystemBase{
    private CANSparkMax motor;
    final private int motorId = 100;
    private static PIDController pid;
    private final SingleJointedArmSim wheel_sim;
    private static Mechanism2d mech_sim;
    private MechanismLigament2d ligament;

    public MySubsystem(){
        final int motorId=-1;
        motor = new CANSparkMax(motorId,MotorType.kBrushless);

        motor.getEncoder().setPosition(0);

        wheel_sim = new SingleJointedArmSim(DCMotor.getFalcon500(1), 1,0.1,.05,Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false, 0);
        mech_sim = new Mechanism2d(100, 100);
        ligament = new MechanismLigament2d("Wheel sim", 20, 0);

        mech_sim.getRoot("pivot", 50, 50).append(ligament);

        double kP = 1, kI = 0, kD = 0;
        pid = new PIDController(kP, kI, kD);
        pid.setTolerance(0.001);
    }

    public void setMotorSpeed(double speed){
        if(Robot.isSimulation()){
            wheel_sim.setInputVoltage(speed * Constants.ROBOT_VOLTAGE);  
        }
        else{
            motor.set(speed);
        }
    }

    public void stopMotor(){
        setMotorSpeed(0);
    }

    public double getEncoderPosition(){
        if(Robot.isSimulation()){
            return Units.radiansToDegrees(wheel_sim.getAngleRads());
        }
        else{
            return motor.getEncoder().getPosition();
        }    
    }

    public void periodic(){
        setMotorSpeed(pid.calculate(getEncoderPosition(), pid.getSetpoint()));
        ligament.setAngle(getEncoderPosition());
    }

    public void spinTo(double radian_setpoint){
        pid.reset();
        pid.setSetpoint(radian_setpoint / (2 * Math.PI)); //to rotations
    }
    
    public static Mechanism2d getMech2d(){
        return mech_sim;
    }

    public boolean atSetpoint(){
        return pid.atSetpoint();
    }

    public static PIDController getPID(){
        return pid;
    }
}

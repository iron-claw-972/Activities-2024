package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.Constants;

public class MySubsystem extends SubsystemBase{
    private CANSparkMax motor;
    final private int motorId = 100;
    PIDController pid;
    private final SingleJointedArmSim wheel_sim = new SingleJointedArmSim(DCMotor.getFalcon500(1), 1,0.1,.05,Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false, 0);

    public MySubsystem(){
        final int motorId=-1;
        motor = new CANSparkMax(motorId,MotorType.kBrushless);

        motor.getEncoder().setPosition(0);

        double kP = 0.1, kI = 0, kD = 0;
        pid = new PIDController(kP, kI, kD);
        pid.setTolerance(0.1);
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
    }

    public void spinTo(double radian_setpoint){
        pid.reset();
        pid.setSetpoint(radian_setpoint / (2 * Math.PI)); //to rotations
      }
    
}

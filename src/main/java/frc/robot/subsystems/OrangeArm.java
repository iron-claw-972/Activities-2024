package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class OrangeArm extends SubsystemBase {
    CANSparkMax motor;
    SingleJointedArmSim armSim;
    DCMotor dcMotor;
    Mechanism2d mechanism2d;
    MechanismLigament2d ligament2d;
    public PIDController pidController = new PIDController(0.02, 0, 0.005);

    public OrangeArm() {
        motor = new CANSparkMax(0, MotorType.kBrushless);
        motor.getEncoder().setPosition(0);
        dcMotor = DCMotor.getFalcon500(1);
        armSim = new SingleJointedArmSim(dcMotor, 1, 0.1, 0.05,
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false, 0);
        mechanism2d = new Mechanism2d(100, 100);
        ligament2d = new MechanismLigament2d("orange ligament", 25, 0);
        mechanism2d.getRoot("pivot", 50, 50).append(ligament2d);
        pidController.setTolerance(0.1);
    }

    public void setMotor(double speed) {
        if (Robot.isReal()) {
            // old code here
            motor.set(speed);
        } else {
            // new sim stuff here
            armSim.setInput(speed * Constants.ROBOT_VOLTAGE);
        }
    }

    public void motorStop() {
        setMotor(0);
    }

    public double encoderPosition() {
        if (Robot.isReal()) {
            return motor.getEncoder().getPosition() * 360;
        } else {
            return Units.radiansToDegrees(armSim.getAngleRads());
        }
    }

    public Mechanism2d getMechanism2d() {
        return mechanism2d;
    }

    @Override
    public void periodic() {
        // setMotor(0.05);
        armSim.update(Constants.LOOP_TIME);
        ligament2d.setAngle(encoderPosition());
        setMotor(pidController.calculate(encoderPosition()));
        


    }

    

    public boolean atSetpoint() {
        return pidController.atSetpoint();
    }

    public void spinTo(double setpoint) {
        pidController.reset();
        pidController.setSetpoint(setpoint);
    }

    public PIDController getPID() {
        return pidController;
    }

   
}

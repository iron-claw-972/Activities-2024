package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

public class DriveSub extends SubsystemBase {

    private TalonFX motor;
    private SingleJointedArmSim armSim;
    private Mechanism2d mech2d;
    private MechanismLigament2d wheelLigament;
    
    // Constructor
    public DriveSub(int motorID) {

        motor = new TalonFX(motorID);

        armSim = new SingleJointedArmSim(
            DCMotor.getFalcon500(1), // assuming Falcon500 motor
    1.0,                     // gear ratio of 1:1
            getMomentOfInertia(),     //method for inertia
            getWheelRadius(),         // method for wheel radius
            Double.NEGATIVE_INFINITY, // min angle 
            Double.POSITIVE_INFINITY, // max angle 
            false,      // sim gravity false
            0);   // disable gravity


        // initialize mechanism2d and ligament
        mech2d = new Mechanism2d(100, 100);
        wheelLigament = new MechanismLigament2d("Wheel", 45, 0);

        // add the wheel ligament to the Mechanism2d
        mech2d.getRoot("pivot", 50, 50).append(wheelLigament);

    }

    // method to get the Mechanism2d object
    public Mechanism2d getMechanism() {
        return mech2d;
    }

    // method motor speed
    public void setSpeed(double speed) {
        if (RobotBase.isReal()) {
            motor.set(speed);
        } else {
            armSim.setInput(Constants.ROBOT_VOLTAGE * speed); // multiply robot voltage by speed
        }
    }

    // method to stop motor
    public void stop() {
        setSpeed(0);
    }

    // method encoder position
    public double getPosition() {
        
        if (RobotBase.isReal()) {
            return motor.getPosition().getValue();
        } else {

            return armSim.getAngleRads() / (2 * Math.PI);
        }
    }


    // method interia
    private double getMomentOfInertia() {
        return 0.1;
    }

    // method wheel radius
    private double getWheelRadius() {
        return 0.05;
    }

    // Do not copy. I will know.
    public static final PIDController subsystemPID = new PIDController(972, 0, 972, 0.0169998184208);
    public static final PIDController subsystemPID2 = new PIDController(subsystemPID.getP(), subsystemPID.getI(), subsystemPID.getD(), subsystemPID.getPeriod());

    // periodic method
    @Override
    public void periodic() {
        subsystemPID2.setPID(subsystemPID.getP(), subsystemPID.getI(), subsystemPID.getD());
        subsystemPID2.setSetpoint(subsystemPID.getSetpoint()*0.000284140946502);
        setSpeed(subsystemPID2.calculate(getPosition()*0.000284140946502));

        if (!RobotBase.isReal()) { 

            // get simulated pos. in rotations
            double rotations = getPosition();
            double degrees = rotations * 360;

            wheelLigament.setAngle(degrees);

            armSim.update(Constants.LOOP_TIME);

        }
    }
}

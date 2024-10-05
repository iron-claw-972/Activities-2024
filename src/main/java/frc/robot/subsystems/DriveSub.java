package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;

public class DriveSub extends SubsystemBase {

    private CANSparkMax motor;
    private SingleJointedArmSim armSim;
    private Mechanism2d mech2d;
    private MechanismLigament2d wheelLigament;
    
    private boolean isReal = false; // check is real or simulated

    // Constructor
    public DriveSub(int motorID, boolean isReal) {
        this.isReal = isReal;
        motor = new CANSparkMax(motorID, MotorType.kBrushless);

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
        wheelLigament = new MechanismLigament2d("Wheel", 30, 0);

        // add the wheel ligament to the Mechanism2d
        mech2d.getRoot("pivot", 50, 50).append(wheelLigament);

        // Reset encoder to 0 if the robot is real
        if (isReal) {
            motor.getEncoder().setPosition(0);
        }
    }

    // Method to get the Mechanism2d object
    public Mechanism2d getMechanism() {
        return mech2d;
    }

    // method motor speed
    public void setSpeed(double speed) {
        if (isReal) {
            motor.set(speed);
        } else {
            armSim.setInput(speed * 12.0); // multiply robot voltage
            armSim.update(0.02); // update the simulation
        }
    }

    // method to stop motor
    public void stop() {
        setSpeed(0);
    }

    // method encoder position
    public double getPosition() {
        if (isReal) {
            return motor.getEncoder().getPosition();
        } else {

            // Convert radians to degrees
            return Units.radiansToDegrees(armSim.getAngleRads());
        }
    }

    // method interia
    private double getMomentOfInertia() {
        return 0.1;
    }

    // method wheel radiua
    private double getWheelRadius() {
        return 0.05;
    }

    // periodic method
    @Override
    public void periodic() {
        if (isReal) {
            setSpeed(0.05); // temp. speed setting for real robot
        } else {
            setSpeed(.005); // slower speed for sim
            // update ligament angle based on the simulated wheel position
            wheelLigament.setAngle(getPosition());
        }
    }
}

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

public class ExtraSubsystem extends SubsystemBase {
    private TalonFX motor;
    private SingleJointedArmSim sim;
    private Mechanism2d mechanism = new Mechanism2d(100, 100);
    private MechanismLigament2d ligament = mechanism.getRoot("pivot", 50, 50).append(new MechanismLigament2d("subsystem", 20, 0));

    public ExtraSubsystem(){
        
        MechanismLigament2d l = ligament.append(new MechanismLigament2d("start", 10, 90+36/2));
        for(int i = 0; i < 9; i++){
            // l = l.append(new MechanismLigament2d(""+i, 10, 36));
        }
        if(RobotBase.isReal()){
            motor = new TalonFX(0);
            motor.setPosition(0);
        }else{
            sim = new SingleJointedArmSim(DCMotor.getFalcon500(1), 1, 0.1, 0.05, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false, 0);
        }
    }

    @Override
    public void periodic(){
        spin(RobotBase.isSimulation()?0.005:0.05);
        if(RobotBase.isSimulation()){
            sim.update(Constants.LOOP_TIME);
        }
        ligament.setAngle(getAngle());
    }
    public Rotation2d getAngle(){
        if(RobotBase.isReal()){
            return Rotation2d.fromRotations(motor.getPosition().getValue());
        }else{
            return new Rotation2d(sim.getAngleRads());
        }
    }
    public void spin(double power){
        if(RobotBase.isReal()){
            motor.set(power);
        }else{
            sim.setInputVoltage(power*Constants.ROBOT_VOLTAGE);
        }
    }
    public void stop(){
        spin(0);
    }
    public Mechanism2d getMechanism(){
        return mechanism;
    }
}

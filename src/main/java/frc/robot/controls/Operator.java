// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.controls;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.constants.Constants;
import lib.controllers.GameController;

public class Operator {

    private final GameController controller = new GameController(Constants.OPERATOR_JOY);

    public Operator() {
    }

    public void configureControls() {
    }

    public Trigger getRightTrigger(){
        return new Trigger(controller.RIGHT_TRIGGER_BUTTON);
    }
    public Trigger getLeftTrigger(){
        return new Trigger(controller.LEFT_TRIGGER_BUTTON);
    }
}

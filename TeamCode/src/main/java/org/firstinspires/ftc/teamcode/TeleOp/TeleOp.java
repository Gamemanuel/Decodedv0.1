package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.config.robotCommands;
import org.firstinspires.ftc.teamcode.libraryUtils.ButtonCombo;

public abstract class TeleOp extends robotCommands {
    ButtonCombo LiftIsManual;
    public TeleOp(Alliance alliance) {super(alliance);}

    public void init() {
        LiftIsManual = new ButtonCombo(gamepad2.back, gamepad2.dpad_left);
    }

    public void loop() {
        LiftIsManual.check();
        telemetry.addData("Manual?", LiftIsManual.get());
    }
}

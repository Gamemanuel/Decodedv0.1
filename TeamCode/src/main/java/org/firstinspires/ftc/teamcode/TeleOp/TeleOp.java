package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.config.robotCommands;
import org.firstinspires.ftc.teamcode.libraryUtils.GamepadEx.ButtonEx;
import org.firstinspires.ftc.teamcode.libraryUtils.Toggle;

public abstract class TeleOp extends robotCommands {
    ButtonEx liftManualCheck;
    Toggle liftIsManual;
    public TeleOp(Alliance alliance) {super(alliance);}

    public void init() {
        liftManualCheck = new ButtonEx(gamepad2.back && gamepad2.dpad_left);
    }

    public void loop() {
        liftIsManual = new Toggle(liftManualCheck.wasJustPressed());

        telemetry.addData("liftIsManual",liftIsManual);
    }
}

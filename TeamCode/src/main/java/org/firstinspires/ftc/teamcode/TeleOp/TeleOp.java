package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.config.robotCommands;
import org.firstinspires.ftc.teamcode.libraryUtils.GamepadEx.ButtonEx;
import org.firstinspires.ftc.teamcode.libraryUtils.Toggle;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public abstract class TeleOp extends robotCommands {
    ButtonEx liftManualCheck;
    Toggle liftIsManual;
    Drivetrain drivetrain;
    Intake intake;

    public TeleOp(Alliance alliance) {super(alliance);}

    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        liftManualCheck = new ButtonEx(gamepad2.back && gamepad2.dpad_left);
    }

    public void loop() {
        liftIsManual = new Toggle(liftManualCheck.wasJustPressed());
        drivetrain.Drive(gamepad1.left_stick_y,gamepad1.right_stick_x);
        intake.front.setPower(-gamepad2.left_stick_y);

        telemetry.addData("liftIsManual",liftIsManual);
    }
}

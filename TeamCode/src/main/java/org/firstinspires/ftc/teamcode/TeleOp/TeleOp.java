package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.config.robotCommands;
import org.firstinspires.ftc.teamcode.libraryUtils.GamepadEx.ButtonEx;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public abstract class TeleOp extends robotCommands {
    ButtonEx liftManualCheck;
    boolean liftIsManual = false;
    Drivetrain drivetrain;
    Intake intake;
    TurretSubsystem turretSubsystem;

    public TeleOp(Alliance alliance) {super(alliance);}

    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        turretSubsystem = new TurretSubsystem(hardwareMap);
    }

    public void loop() {
        // Manual lift functions
        liftManualCheck = new ButtonEx(gamepad2.left_bumper && gamepad2.right_bumper);
        if (liftManualCheck.wasJustPressed()) {
            liftIsManual = !liftIsManual;
        }

        // Drivetrain functions
        drivetrain.Drive(gamepad1.left_stick_y,gamepad1.right_stick_x);

        // Intake subsystem
        intake.front.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        intake.floop.setPosition(-gamepad2.left_stick_y * 0.75);


        // telemetry data for the robot
        telemetry.addData("liftIsManual",liftIsManual);
        telemetry.addData("liftmanualcheck", liftManualCheck.wasJustPressed());
        telemetry.update();

        turretSubsystem.periodic();

        if (gamepad2.right_bumper) {
            turretSubsystem.setTurretPower(-1);
        }
        if (gamepad2.left_bumper) {
            turretSubsystem.setTurretPower(1);
        }
        if (!gamepad2.left_bumper && !gamepad2.right_bumper) {
            turretSubsystem.setTurretPower(0);
        }


    }
}

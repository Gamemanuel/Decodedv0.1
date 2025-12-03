package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.commands.turret.TurretAutoLLCMD;
import org.firstinspires.ftc.teamcode.config.robotCommands;
import org.firstinspires.ftc.teamcode.libraryUtils.GamepadEx.ButtonEx;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public abstract class TeleOp extends robotCommands {
    ButtonEx liftManualCheck;
    boolean liftIsManual = false;
    Drivetrain drivetrain;
    Intake intake;
    TurretSubsystem turretSubsystem;
    LLSubsystem llSubsystem;
    TurretAutoLLCMD turretAutoLLCMD;
    private final Alliance alliance;

    public TeleOp(Alliance alliance) {
        super(alliance);
        this.alliance = alliance;
    }

    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        turretSubsystem = new TurretSubsystem(hardwareMap, alliance);
        llSubsystem = new LLSubsystem(hardwareMap, alliance);
        turretAutoLLCMD = new TurretAutoLLCMD(turretSubsystem, llSubsystem);
    }

    public void loop() {
        llSubsystem.periodic();
        // Manual lift functions
        liftManualCheck = new ButtonEx(gamepad2.left_bumper && gamepad2.right_bumper);
        if (liftManualCheck.wasJustPressed()) {
            liftIsManual = !liftIsManual;
        }

        // Drivetrain functions
        drivetrain.Drive(gamepad1.left_stick_y, gamepad1.right_stick_x);

        // Intake subsystem
        intake.front.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        intake.floop.setPosition(-gamepad2.left_stick_y * 0.75);

        // This code is the turret tracking code
            // this is a manuel override for the
            if (gamepad2.left_bumper || gamepad2.right_bumper) {
                double turntablePower = 1;
                if (gamepad2.right_bumper) {
                    turntablePower = -turntablePower;
                }
                turretSubsystem.setTurretPower(turntablePower);
                // If the bumpers on Madelyn's controller are not pressed then let the limelight handle it

            } else {
                // run the auto tracking code here.
                turretSubsystem.setTurretPower(gamepad2.left_stick_x);
                turretAutoLLCMD.execute();
            }

        // End of the auto tracking code segment

        // telemetry data for the robot
        telemetry.addData("liftIsManual", liftIsManual);
        telemetry.addData("liftManualCheck", liftManualCheck.wasJustPressed());
        telemetry.update();
    }
}
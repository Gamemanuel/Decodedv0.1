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
    LLSubsystem ll;
    TurretAutoLLCMD turretAuto;
    private final Alliance alliance;
    double[] turretTel;

    public TeleOp(Alliance alliance) {
        super(alliance);
        this.alliance = alliance;
    }

    public void init() {
        turretTel = new double[] {0, 0};
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        turretSubsystem = new TurretSubsystem(hardwareMap, alliance);
        ll = new LLSubsystem(hardwareMap, alliance);
        turretAuto = new TurretAutoLLCMD(turretSubsystem, ll);
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
//        telemetry.addData("liftIsManual",liftIsManual);
//        telemetry.addData("liftmanualcheck", liftManualCheck.wasJustPressed());
//        telemetry.update();

        turretSubsystem.periodic();

        if (gamepad2.right_bumper) {
            turretSubsystem.setPower(-1);
        }
        if (gamepad2.left_bumper) {
            turretSubsystem.setPower(1);
        }
        if (!gamepad2.left_bumper && !gamepad2.right_bumper) {
            turretTel = turretAuto.faceGoal(5, alliance);
        }
        telemetry.addData("tx", turretTel[0]);
        telemetry.addData("speed", turretTel[1]);
        try {
            telemetry.addData("speed",Math.min(1, Math.max(ll.getAllianceTX() / 10, 0.25)));
        } catch (NullPointerException npe) {
            telemetry.addData("speed","null");
        }
        telemetry.update();
    }
}
package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Alliance;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

public abstract class TeleOp extends OpMode {

    // REPLACE ALL SUBSYSTEMS WITH ONE ROBOT OBJECT
    Robot robot;
    private final Alliance alliance;

    public TeleOp(Alliance alliance) {
        this.alliance = alliance;
    }

    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // CLEAN INITIALIZATION
        robot = new Robot(hardwareMap, alliance);
    }

    public void loop() {
        // Run periodic logic defined in Robot class
        robot.runPeriodic();

        // 1. DRIVETRAIN (Access via robot.drivetrain)
        robot.drivetrain.Drive(gamepad1.left_stick_y, gamepad1.right_stick_x);

        // 2. INTAKE
        robot.intake.front.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        robot.intake.floop.setPosition(-gamepad2.left_stick_y * 0.75);

        // 3. TURRET LOGIC
        if (gamepad2.right_bumper) {
            robot.turretSubsystem.setPower(-0.5);
        } else if (gamepad2.left_bumper) {
            robot.turretSubsystem.setPower(0.5);
        } else {
            robot.turretAuto.faceAprilTag(1.5, alliance);
        }

        if (gamepad2.right_stick_y != 0) {
            robot.shooter.shooter.setPower(gamepad2.right_stick_y);
        } else {
            // This is already called in robot.runPeriodic(), so you might not need it here
            // unless you want explicit control.
            robot.shooterAutoCmd.execute();
        }

        // TELEMETRY
        telemetry.addData("Shooter Target", robot.shooter.getTargetVelocity());
        telemetry.addData("Shooter Actual", robot.shooter.shooter.getVelocity());
        telemetry.update();
    }
}